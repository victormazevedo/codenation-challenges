package br.com.movile.order.service;

import static br.com.movile.order.model.OrderStatus.CANCELLED;
import static br.com.movile.order.model.OrderStatus.OPENED;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import br.com.movile.delivery.model.dto.DeliveryForecast;
import br.com.movile.delivery.serivce.DeliveryForecastService;
import br.com.movile.delivery.serivce.DeliveryService;
import br.com.movile.exception.model.NoMotoboyAvailableException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.stereotype.Service;

import br.com.movile.customer.model.Customer;
import br.com.movile.customer.repository.CustomerRepository;
import br.com.movile.item.model.Item;
import br.com.movile.item.repository.ItemRepository;
import br.com.movile.order.model.Order;
import br.com.movile.order.model.OrderStatus;
import br.com.movile.order.repository.OrderRepository;
import br.com.movile.restaurant.repository.RestaurantRepository;

@Service
public class OrderService {

	private final OrderRepository orderRepository;

	private final RestaurantRepository restaurantRepository;

	private final CustomerRepository customerRepository;

	private final ItemRepository itemRepository;

	private final MongoOperations mongoOperations;

	private final DeliveryForecastService deliveryForecastService;

	private boolean close;


    @Autowired
    private DeliveryService deliveryService;

    public OrderService(OrderRepository orderRepository, RestaurantRepository restaurantRepository,
						CustomerRepository customerRepository, ItemRepository itemRepository, MongoOperations mongoOperations, DeliveryForecastService deliveryForecastService) {
		this.orderRepository = orderRepository;
		this.restaurantRepository = restaurantRepository;
		this.customerRepository = customerRepository;
		this.itemRepository = itemRepository;
		this.mongoOperations = mongoOperations;
		this.deliveryForecastService = deliveryForecastService;
	}

	public DeliveryForecast save(Order order) throws NoMotoboyAvailableException {
		Optional customer = customerRepository.findById(order.getCustomer().getId());
		Optional restaurant = restaurantRepository.findById(order.getRestaurant().getId());

		List<String> ids = order.getItems().stream().map(Item::getId).collect(Collectors.toList());
		Iterable<Item> findAllById = itemRepository.findAllById(ids);

		if (findAllById == null || StreamSupport.stream(findAllById.spliterator(), false).count() != ids.size()) {
			throw new NoSuchElementException("Item not found or invalid!");
		}

		if (!customer.isPresent()) {
			throw new NoSuchElementException("User not found or invalid!");
		}

		if (!restaurant.isPresent()) {
			throw new NoSuchElementException("Restaurant not found or invalid!");
		}

		order.setStatus(OPENED);
        order = orderRepository.save(order);

		return deliveryForecastService.calculateForecast(order);
	}

	public Order getOrder(String orderId) {
		try {
			new ObjectId(orderId);
		} catch (IllegalArgumentException ile) {
			throw new IllegalArgumentException("Illegal ObjectId!", ile);
		}
		return orderRepository.findById(orderId)
				.orElseThrow(() -> new NoSuchElementException("Order not found!"));
	}

	public void delete(String orderId) throws NoMotoboyAvailableException {

		Optional<Order> order = orderRepository.findById(orderId);

		if (!order.isPresent()) {
			throw new IllegalArgumentException("Order not found!");
		}

		if (order.get().getStatus() == CANCELLED) {
			throw new IllegalArgumentException("Order already cancelled!");
		}

		deliveryService.removeOrder(orderId);
	}

	public Page<Order> getOrders(Pageable pageable) {
		return orderRepository.findAll(pageable);
	}

	public void changeStatus(String orderId, OrderStatus status) throws NoMotoboyAvailableException {

    	if(!OrderStatus.FINISHED.equals(status)){
    		throw new IllegalArgumentException("Só é permitido mudar o status para finalizado");
		}

		Order order = getOrder(orderId);
		order.setStatus(status);
		orderRepository.save(order);

		deliveryService.addOrderToDelivery(order);
	}

	public boolean closeEnough(Order order1, Order order2, double distanciaMaxima) {

		close = false;

		Point point1 = new Point(order1.getCustomer().getLocation());
		NearQuery maxDistance = NearQuery.near(point1).inKilometers().maxDistance(distanciaMaxima);

		GeoResults<Customer> geoNear = mongoOperations.geoNear(maxDistance, Customer.class);

		geoNear.forEach(x -> {
			String id = x.getContent().getId();
			if (id.equalsIgnoreCase(order2.getCustomer().getId())) {
				close = true;
			}
		});

		return close;
	}
}

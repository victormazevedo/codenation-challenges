package br.com.movile.order.service;

import br.com.movile.customer.repository.CustomerRepository;
import br.com.movile.delivery.model.dto.DeliveryForecast;
import br.com.movile.delivery.serivce.DeliveryService;
import br.com.movile.exception.model.NoMotoboyAvailableException;
import br.com.movile.item.model.Item;
import br.com.movile.item.repository.ItemRepository;
import br.com.movile.order.model.Order;
import br.com.movile.order.model.OrderStatus;
import br.com.movile.order.repository.OrderRepository;
import br.com.movile.restaurant.repository.RestaurantRepository;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static br.com.movile.order.model.OrderStatus.CANCELLED;
import static br.com.movile.order.model.OrderStatus.OPENED;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final RestaurantRepository restaurantRepository;

    private final CustomerRepository customerRepository;

    private final ItemRepository itemRepository;

    private final DeliveryService deliveryService;

    public OrderService(OrderRepository orderRepository, RestaurantRepository restaurantRepository, CustomerRepository customerRepository, ItemRepository itemRepository, DeliveryService deliveryService) {
        this.orderRepository = orderRepository;
        this.restaurantRepository = restaurantRepository;
        this.customerRepository = customerRepository;
        this.itemRepository = itemRepository;
        this.deliveryService = deliveryService;
    }

    public DeliveryForecast save(Order order) throws NoMotoboyAvailableException {
        Optional customer = customerRepository.findById(order.getCustomer().getId());
        Optional restaurant = restaurantRepository.findById(order.getRestaurant().getId());

        List<String> ids = order.getItems().stream().map(Item::getId).collect(Collectors.toList());
        Iterable<Item> findAllById = itemRepository.findAllById(ids);

        if (findAllById == null || StreamSupport.stream(findAllById.spliterator(), false).count() != ids.size()) {
            throw new NoSuchElementException("Item(s) inválido(s) ou não encontrado(s)!");
        }

        if (!customer.isPresent()) {
            throw new NoSuchElementException("Usuário inválido e/ou não encontrado!");
        }

        if (!restaurant.isPresent()) {
            throw new NoSuchElementException("Restaurante inválido e/ou não encontrado!");
        }

        order.setStatus(OPENED);
        order = orderRepository.save(order);

        return deliveryService.addOrderToDelivery(order);
    }

    public Order getOrder(String orderId) {
        try {
            new ObjectId(orderId);
        } catch (IllegalArgumentException ile) {
            throw new IllegalArgumentException("ObjectId fora do padrão!", ile);
        }
        return orderRepository.findById(orderId).orElseThrow(() ->
                new NoSuchElementException("Pedido não encontrado!"));
    }

    public void delete(String orderId) {

        Optional<Order> order = orderRepository.findById(orderId);

        if (!order.isPresent()) {
            throw new IllegalArgumentException("Pedido não encontrado!");
        }

        if (order.get().getStatus() == CANCELLED) {
            throw new IllegalArgumentException("Pedido já cancelado!");
        }

        order.get().setStatus(CANCELLED);

        orderRepository.save(order.get());
    }

    public Page<Order> getOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    public void changeStatus(String orderId, OrderStatus status) throws NoMotoboyAvailableException {
        Order order = getOrder(orderId);
        order.setStatus(status);

        save(order);
    }
}

package br.com.movile.order.service;

import br.com.movile.customer.repository.CustomerRepository;
import br.com.movile.item.model.Item;
import br.com.movile.item.repository.ItemRepository;
import br.com.movile.order.model.Order;
import br.com.movile.order.repository.OrderRepository;
import br.com.movile.restaurant.repository.RestaurantRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final RestaurantRepository restaurantRepository;

    private final CustomerRepository customerRepository;

    private final ItemRepository itemRepository;

    public OrderService(OrderRepository orderRepository, RestaurantRepository restaurantRepository, CustomerRepository customerRepository, ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.restaurantRepository = restaurantRepository;
        this.customerRepository = customerRepository;
        this.itemRepository = itemRepository;
    }

    public Order save(Order order) {
        Optional customer = customerRepository.findById(order.getCustomer().getId());
        Optional restaurant = restaurantRepository.findById(order.getRestaurant().getId());

        List<String> ids = order.getItems().stream().map(Item::getId).collect(Collectors.toList());
        Iterable<Item> findAllById = itemRepository.findAllById(ids);

        if (findAllById == null || StreamSupport.stream(findAllById.spliterator(), false).count() != ids.size()) {
            throw new NoSuchElementException("Item(s) inválido(s) ou não encontrado(s)!");
        }

        if (!customer.isPresent() || order.getCustomer().getId().equals("null")) {
            throw new NoSuchElementException("Usuário inválido e/ou não encontrado!");
        }

        if (!restaurant.isPresent() || order.getRestaurant().getId().equals("null")) {
            throw new NoSuchElementException("Restaurante inválido e/ou não encontrado!");
        }
        return orderRepository.save(order);
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
}

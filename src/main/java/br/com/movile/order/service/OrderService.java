package br.com.movile.order.service;

import br.com.movile.customer.repository.CustomerRepository;
import br.com.movile.item.model.Item;
import br.com.movile.item.repository.ItemRepository;
import br.com.movile.order.model.Order;
import br.com.movile.order.repository.OrderRepository;
import br.com.movile.restaurant.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

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

        for (Item item : order.getItems()) {
            if (!itemRepository.findById(item.getId()).isPresent()) {
                throw new NoSuchElementException("Item de ID: " + item.getId() + " e nome: " + item.getDescription() + " não encontrado!");
            }
        }

        if (!customer.isPresent() || order.getCustomer().getId().equals("null")) {
            throw new NoSuchElementException("Restaurante inválido e/ou não encontrado!");
        }

        if (!restaurant.isPresent() || order.getRestaurant().getId().equals("null")) {
            throw new NoSuchElementException("Restaurante inválido e/ou não encontrado!");
        }
        return orderRepository.save(order);
    }
}

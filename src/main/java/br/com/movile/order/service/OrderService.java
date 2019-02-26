package br.com.movile.order.service;

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

    public OrderService(OrderRepository orderRepository, RestaurantRepository restaurantRepository) {
        this.orderRepository = orderRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public Order save(Order order) {
        Optional restaurant = restaurantRepository.findById(order.getRestaurant().getId());

        if (!restaurant.isPresent() || order.getRestaurant().getId().equals("null")) {
            throw new NoSuchElementException("Restaurante inválido e/ou não encontrado!");
        }

        return orderRepository.save(order);
    }
}

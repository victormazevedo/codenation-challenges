package br.com.movile.order.controller;

import br.com.movile.delivery.model.dto.DeliveryForecast;
import br.com.movile.exception.model.NoMotoboyAvailableException;
import br.com.movile.order.model.Order;
import br.com.movile.order.service.OrderService;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public DeliveryForecast save(@RequestBody Order order) throws NoMotoboyAvailableException {
        return orderService.save(order);
    }

    @GetMapping("/{orderId}")
    public Order getOrder(@PathVariable String orderId) {
        return orderService.getOrder(orderId);
    }

    @DeleteMapping("/{orderId}")
    public void cancel(@PathVariable String orderId) {
        orderService.delete(orderId);
    }

    @GetMapping
    public Page<Order> getOrders(Pageable pageable) {
        return orderService.getOrders(pageable);
    }
}

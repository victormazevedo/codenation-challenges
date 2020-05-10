package br.com.movile.order.controller;

import br.com.movile.delivery.model.dto.DeliveryForecast;
import br.com.movile.exception.model.CustomerTooFarException;
import br.com.movile.order.model.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import br.com.movile.exception.model.NoMotoboyAvailableException;
import br.com.movile.order.model.Order;
import br.com.movile.order.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public DeliveryForecast save(@RequestBody Order order) throws NoMotoboyAvailableException, CustomerTooFarException {
        return orderService.save(order);
    }

    @GetMapping("/{orderId}")
    public Order getOrder(@PathVariable String orderId) {
        return orderService.getOrder(orderId);
    }

    @DeleteMapping("/{orderId}")
    public void cancel(@PathVariable String orderId) throws NoMotoboyAvailableException {
        orderService.delete(orderId);
    }

    @GetMapping
    public Page<Order> getOrders(Pageable pageable) {
        return orderService.getOrders(pageable);
    }

    @PatchMapping("{id}/status")
    public void changeStatus(@PathVariable("id") String id,
                       @RequestParam("value") OrderStatus status) throws NoMotoboyAvailableException {
        orderService.changeStatus(id, status);
    }
}

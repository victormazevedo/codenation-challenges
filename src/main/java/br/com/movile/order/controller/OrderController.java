package br.com.movile.order.controller;

import br.com.movile.order.model.Order;
import br.com.movile.order.service.OrderService;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @ResponseBody
    @PostMapping
    public ResponseEntity<Order> save(@RequestBody Order order) {
        order.setId(ObjectId.get());
        return ResponseEntity.ok(orderService.save(order));
    }
}

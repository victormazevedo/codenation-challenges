package br.com.movile.delivery.service;

import br.com.movile.delivery.model.Delivery;
import br.com.movile.delivery.repository.DeliveryRepository;
import br.com.movile.delivery.serivce.DeliveryService;
import br.com.movile.exception.model.NoMotoboyAvailableException;
import br.com.movile.order.model.Order;
import br.com.movile.order.model.OrderStatus;
import br.com.movile.order.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class DeliveryServiceTest {

    @Autowired
    private DeliveryService deliveryService;
    @MockBean
    private OrderService orderService;
    @MockBean
    private DeliveryRepository deliveryRepository;

    @Test
    void shouldRemoveOrderFromDelivery() throws NoMotoboyAvailableException {

        Order order = new Order();
        order.setId("orderId");
        Delivery delivery = new Delivery();
        delivery.setId("id");
        delivery.addOrder(order);

        when(deliveryRepository.findById(any()))
                .thenReturn(Optional.of(delivery));

        deliveryService.removeOrder("id", "orderId");

        ArgumentCaptor<Delivery> deliveryCaptor = ArgumentCaptor.forClass(Delivery.class);
        verify(deliveryRepository, times(1))
                .save(deliveryCaptor.capture());

        ArgumentCaptor<String> orderIdCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<OrderStatus> orderStatusCaptor = ArgumentCaptor.forClass(OrderStatus.class);
        verify(orderService, times(1))
                .changeStatus(orderIdCaptor.capture(), orderStatusCaptor.capture());

        assertEquals("id", deliveryCaptor.getValue().getId());
        assertEquals("orderId", orderIdCaptor.getValue());
        assertEquals(OrderStatus.CANCELLED, orderStatusCaptor.getValue());
    }

    @Test
    void shouldNotFindDelivery() throws NoMotoboyAvailableException {

        when(deliveryRepository.findById(any()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () ->
                deliveryService.removeOrder("id", "orderId"));

    }
}

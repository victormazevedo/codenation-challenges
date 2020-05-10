package br.com.movile.delivery.model;

import br.com.movile.exception.model.CannotAddMoreOrderException;
import br.com.movile.motoboy.model.Motoboy;
import br.com.movile.order.model.Order;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Document(collection = "delivery")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Delivery {

    @Id
    @EqualsAndHashCode.Include
    @Setter
    private String id;
    private List<Order> orders;
    @Setter
    private Motoboy motoboy;
    private LocalDateTime windowBegin;
    @Setter
    private DeliveryStatus status;

    public Delivery() {
        orders = new ArrayList<>();
        windowBegin = LocalDateTime.now();
        status = DeliveryStatus.OPENED;
    }

    public void addOrder(Order order) throws CannotAddMoreOrderException {
        if (orders.size() < 5) {
            orders.add(order);
        } else {
            throw new CannotAddMoreOrderException();
        }

        if (orders.size() == 5) {
            status = DeliveryStatus.CLOSED;
        }
    }

    @JsonIgnore
    public boolean isComplete() {
        return orders.size() == 5;
    }

    public void removeOrderById(String orderId) {
        orders = orders
                .stream()
                .filter(order -> !order.getId().equals(orderId))
                .collect(Collectors.toList());

        if (orders.size() == 0) {
            status = DeliveryStatus.FINISHED;
        }
    }

    public List<Order> getOrders() {
        return Collections.unmodifiableList(orders);
    }
}

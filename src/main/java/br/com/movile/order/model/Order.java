package br.com.movile.order.model;

import br.com.movile.customer.model.Customer;
import br.com.movile.item.model.Item;
import br.com.movile.restaurant.model.Restaurant;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "order")
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Order {

    @Id
    @Setter
    @EqualsAndHashCode.Include
    private String id;

    private Customer customer;
    private LocalDateTime date;
    private Restaurant restaurant;
    private List<Item> items;
    @Setter
    private OrderStatus status;

    public Order() {
    }

    public Order(String id, Customer customer, LocalDateTime date, Restaurant restaurant, List<Item> items, OrderStatus status) {
        this.id = id;
        this.customer = customer;
        this.date = date;
        this.restaurant = restaurant;
        this.items = items;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", date=" + date +
                ", restaurant=" + restaurant +
                ", items=" + items +
                ", status=" + status +
                '}';
    }
}

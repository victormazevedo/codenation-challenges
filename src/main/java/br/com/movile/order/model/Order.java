package br.com.movile.order.model;

import java.time.LocalDateTime;
import java.util.List;

import br.com.movile.customer.model.Customer;
import br.com.movile.item.model.Item;
import br.com.movile.restaurant.model.Restaurant;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;

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

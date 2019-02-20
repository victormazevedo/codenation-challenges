package br.com.movile.order.model;

import java.time.LocalDateTime;
import java.util.List;

import br.com.movile.customer.model.Customer;
import br.com.movile.item.model.Item;
import br.com.movile.restaurant.model.Restaurant;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;

@Document(collection = "order")
@Getter
public class Order {

	@Id
	private long id;
	private List<Item> items;
	private Customer customer;
	private Restaurant restaurant;
	private LocalDateTime date;

	@Override
	public String toString() {
		return "Order{" +
				"id=" + id +
				", items=" + items +
				", customer=" + customer +
				", restaurant=" + restaurant +
				", date=" + date +
				'}';
	}
}

package br.com.movile.delivery.model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import br.com.movile.exception.model.CannotAddMoreOrderException;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.movile.motoboy.model.Motoboy;
import br.com.movile.order.model.Order;
import lombok.Getter;

@Getter
@Document(collection = "delivery")
public class Delivery {

	@Id
	private String id;
	private List<Order> orders;
	private Motoboy motoboy;
	private LocalDateTime windowBegin;
	@Setter
	private boolean closed;

	public Delivery(){
		orders = new ArrayList<>();
		windowBegin = LocalDateTime.now();
		closed = false;
	}

	public void addOrder(Order order) throws CannotAddMoreOrderException {
		if(orders.size() < 5){
			orders.add(order);
		}

		throw new CannotAddMoreOrderException();
	}
}

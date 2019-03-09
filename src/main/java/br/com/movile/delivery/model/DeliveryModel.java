package br.com.movile.delivery.model;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.movile.motoboy.model.Motoboy;
import br.com.movile.order.model.Order;
import lombok.Getter;

@Getter
@Document(collection = "delivery")
public class DeliveryModel {

	@Id
	private String id;
	private List<Order> orders;
	private Motoboy motoboy;
	private LocalTime timeInitialTime;	
}

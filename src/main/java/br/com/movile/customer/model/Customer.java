package br.com.movile.customer.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@Document(collection = "customer")
@Data
public class Customer {

	@Id
	private String id;
	private double longitude;
	private double latitude;

	public Customer(String id, double longitude, double latitude) {
		super();
		this.id = id;
		this.longitude = longitude;
		this.latitude = latitude;
	}

}

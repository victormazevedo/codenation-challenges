package br.com.movile.customer.model;

import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;


@Getter
@Document(collection = "customer")
public class Customer {

	@Id
	private String id;
	private double longitude;
	private double latitude;

	public String getId() {
		return id;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public Customer(String id, double longitude, double latitude) {
		this.id = id;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	@Override
	public String toString() {
		return "Customer{" +
				"id='" + id + '\'' +
				", longitude=" + longitude +
				", latitude=" + latitude +
				'}';
	}
}

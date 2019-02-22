package br.com.movile.restaurant.model;

import br.com.movile.item.model.Item;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;

import java.util.List;

@Getter
@Document(collection = "restaurant")
public class Restaurant {

	@Id
	private String id;
	private String name;
	private String addressCity;
	private double longitude;
	private double latitude;
	private String dishDescription;


	public Restaurant(String id, String name, String addressCity, double longitude, double latitude, String dishDescription) {
		this.id = id;
		this.name = name;
		this.addressCity = addressCity;
		this.longitude = longitude;
		this.latitude = latitude;
		this.dishDescription = dishDescription;
	}

	public Restaurant() {};

	@Override
	public String toString() {
		return "Restaurant{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", addressCity='" + addressCity + '\'' +
				", longitude='" + longitude + '\'' +
				", latitude='" + latitude + '\'' +
				", dishDescription='" + dishDescription + '\'' +
				'}';
	}
}

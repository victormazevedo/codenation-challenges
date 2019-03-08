package br.com.movile.restaurant.model;

import br.com.movile.item.model.Item;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Document(collection = "restaurant")
public class Restaurant implements Serializable {

	@Id
    @Getter
    @Setter
	private String id;

	@Getter
    @Setter
	private String name;

    @Getter
    @Setter
	private String addressCity;

    @Getter
    @Setter
	private double longitude;

    @Getter
    @Setter
	private double latitude;

    @Getter
    @Setter
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

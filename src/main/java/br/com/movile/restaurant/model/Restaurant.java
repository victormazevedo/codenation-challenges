package br.com.movile.restaurant.model;

import br.com.movile.item.model.Item;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Document(collection = "restaurant")
public class Restaurant implements Serializable {

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getDishDescription() {
		return dishDescription;
	}

	public void setDishDescription(String dishDescription) {
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

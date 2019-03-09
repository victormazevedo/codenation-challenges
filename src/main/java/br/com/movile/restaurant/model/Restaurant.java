package br.com.movile.restaurant.model;

import br.com.movile.item.model.Item;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Document(collection = "restaurant")
public class Restaurant implements Serializable {

	public Restaurant() {
	};

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
	private GeoJsonPoint location;

	@Getter
	@Setter
	private String dishDescription;

	public Restaurant(String id, String name, String addressCity, GeoJsonPoint location, String dishDescription) {
		this.id = id;
		this.name = name;
		this.addressCity = addressCity;
		this.location = location;
		this.dishDescription = dishDescription;
	}

	@Override
	public String toString() {
		return "Restaurant [id=" + id 
				+ ", name=" + name 
				+ ", addressCity=" + addressCity 
				+ ", location=" + location
				+ ", dishDescription=" + dishDescription 
				+ "]";
	}

}

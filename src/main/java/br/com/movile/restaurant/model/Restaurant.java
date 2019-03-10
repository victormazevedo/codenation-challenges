package br.com.movile.restaurant.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "restaurant")
public class Restaurant implements Serializable {

	private static final long serialVersionUID = 9174284964172259833L;

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
	@GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
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

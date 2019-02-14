package br.com.movile.motoboy.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "motoboy")
public class MotoBoy {

	public MotoBoy(long id, double longitude, double latitude) {
		this.id = id;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	@Id
	private long id;
	private double longitude;
	private double latitude;
	
	@Override
	public String toString() {
		return "O MotoBoy possui o id : " + id + ", com longitude : " + longitude + ", e latitude : " + latitude;
	}

	public long getId() {
		return id;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getLatitude() {
		return latitude;
	}
	
	
	
}

package br.com.movile.motoboy.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;

@Document(collection = "motoboy")
@Getter
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
}

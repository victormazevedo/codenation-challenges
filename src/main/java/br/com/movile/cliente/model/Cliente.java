package br.com.movile.cliente.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;


@Document(collection = "cliente")
@Getter
public class Cliente {

	
	
	public Cliente(long id, double longitude, double latitude) {
		this.id = id;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	@Id
	private long id;
	
	private double longitude;
	private double latitude;	
}

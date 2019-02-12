package br.com.movile.cliente.model;

import org.springframework.data.annotation.Id;


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
	

	
	
	@Override
	public String toString() {
		return "O Cliente possui o id : " + id + ", com longitude : " + longitude + ", e latitude : " + latitude;
	}
}

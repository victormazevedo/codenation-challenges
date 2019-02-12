package br.com.movile.motoboy.model;

public class MotoBoy {

	public MotoBoy(long id, double longitude, double latitude) {
		super();
		this.id = id;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	private long id;
	private double longitude;
	private double latitude;
	
	@Override
	public String toString() {
		return "O MotoBoy possui o id : " + id + ", com longitude : " + longitude + ", e latitude : " + latitude;
	}
	
}

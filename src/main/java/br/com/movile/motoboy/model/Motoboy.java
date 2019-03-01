package br.com.movile.motoboy.model;

import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;

@Getter
@Document(collection = "motoboy")
public class Motoboy {

	@Id
    @Getter
    @Setter
	private String id;

	@Getter
    @Setter
	private double longitude;

    @Getter
    @Setter
	private double latitude;

    @Getter
    @Setter
    private boolean busy;


    public Motoboy(String id, double longitude, double latitude, boolean busy) {
		this.id = id;
		this.longitude = longitude;
		this.latitude = latitude;
		this.busy = busy;
	}
	public Motoboy (){}

	@Override
	public String toString() {
		return "Motoboy{" +
				"id='" + id + '\'' +
				", longitude=" + longitude +
				", latitude=" + latitude +
				'}';
	}
}

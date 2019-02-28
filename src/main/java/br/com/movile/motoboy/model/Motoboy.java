package br.com.movile.motoboy.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;

@Getter
@Document(collection = "motoboy")
public class Motoboy {

	@Id
	private String id;
	private double longitude;
	private double latitude;
    private boolean busy;

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

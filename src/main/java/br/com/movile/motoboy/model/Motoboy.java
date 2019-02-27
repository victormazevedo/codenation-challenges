package br.com.movile.motoboy.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;

@Getter
@Document(collection = "motoboy")
public class MotoBoy {

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

    public MotoBoy (){};

	public MotoBoy(String id, double longitude, double latitude) {
		this.id = id;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	@Override
	public String toString() {
		return "Motoboy{" +
				"id='" + id + '\'' +
				", longitude=" + longitude +
				", latitude=" + latitude +
				'}';
	}
}

package br.com.movile.motoboy.model;

import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
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
	private GeoJsonPoint location;

	@Getter
	@Setter
	private boolean busy;

	public Motoboy(String id, GeoJsonPoint location, boolean busy) {
		this.id = id;
		this.location = location;
		this.busy = busy;
	}

	@Override
	public String toString() {
		return "Motoboy [id=" + id + ", location=" + location + ", busy=" + busy + "]";
	}

}

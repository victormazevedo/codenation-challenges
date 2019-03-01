package br.com.movile.item.model;

import org.bson.types.Decimal128;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "item")
public class Item {

	@Id
	private String id;
	private String description;
	private String restaurant;
	private String restaurantId;
	private String classification;
	private Decimal128 unitPrice;
	private String addressCity;

	public Item(String id, String description, String restaurant, String restaurantId, String classification,
			Decimal128 unitPrice, String addressCity) {
		this.id = id;
		this.description = description;
		this.restaurant = restaurant;
		this.restaurantId = restaurantId;
		this.classification = classification;
		this.unitPrice = unitPrice;
		this.addressCity = addressCity;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}

	public String getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public Decimal128 getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Decimal128 unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	@Override
	public String toString() {
		return "Item{" + "id='" + id + '\'' + ", description='" + description + '\'' + ", restaurant='" + restaurant
				+ '\'' + ", restaurantId='" + restaurantId + '\'' + ", classification='" + classification + '\''
				+ ", unitPrice=" + unitPrice + ", addressCity='" + addressCity + '\'' + '}';
	}

}

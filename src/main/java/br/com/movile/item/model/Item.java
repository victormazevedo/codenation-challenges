package br.com.movile.item.model;

import java.math.BigDecimal;

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
	private BigDecimal unitPrice;
	private String addressCity;

	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public String getRestaurant() {
		return restaurant;
	}

	public String getRestaurantId() {
		return restaurantId;
	}

	public String getClassification() {
		return classification;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public Item() {
		super();
	}

	public Item(ItemBuilder builder) {
		this.id = builder.id;
		this.description = builder.description;
		this.restaurant = builder.restaurant;
		this.restaurantId = builder.restaurantId;
		this.classification = builder.classification;
		this.unitPrice = builder.unitPrice;
		this.addressCity = builder.addressCity;
	}

	public static class ItemBuilder {

		private String id;
		private String description;
		private String restaurant;
		private String restaurantId;
		private String classification;
		private BigDecimal unitPrice;
		private String addressCity;

		public ItemBuilder setId(String id) {
			this.id = id;
			return this;
		}

		public ItemBuilder setDescription(String description) {
			this.description = description;
			return this;
		}

		public ItemBuilder setRestaurant(String restaurant) {
			this.restaurant = restaurant;
			return this;
		}

		public ItemBuilder setRestaurantId(String restaurantId) {
			this.restaurantId = restaurantId;
			return this;
		}

		public ItemBuilder setClassification(String classification) {
			this.classification = classification;
			return this;
		}

		public ItemBuilder setUnitPrice(BigDecimal unitPrice) {
			this.unitPrice = unitPrice;
			return this;
		}

		public ItemBuilder setAddressCity(String addressCity) {
			this.addressCity = addressCity;
			return this;
		}

		public Item builder() {
			return new Item(this);
		}

	}

	@Override
	public String toString() {
		return "Item{" + "id='" + id + '\'' + ", description='" + description + '\'' + ", restaurant='" + restaurant
				+ '\'' + ", restaurantId='" + restaurantId + '\'' + ", classification='" + classification + '\''
				+ ", unitPrice=" + unitPrice + ", addressCity='" + addressCity + '\'' + '}';
	}

}

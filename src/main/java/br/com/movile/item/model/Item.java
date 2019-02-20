package br.com.movile.item.model;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Getter
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

    public Item(String id, String description, String restaurant, String restaurantId,
                String classification, BigDecimal unitPrice, String addressCity) {
        this.id = id;
        this.description = description;
        this.restaurant = restaurant;
        this.restaurantId = restaurantId;
        this.classification = classification;
        this.unitPrice = unitPrice;
        this.addressCity = addressCity;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", restaurant='" + restaurant + '\'' +
                ", restaurantId='" + restaurantId + '\'' +
                ", classification='" + classification + '\'' +
                ", unitPrice=" + unitPrice +
                ", addressCity='" + addressCity + '\'' +
                '}';
    }
}

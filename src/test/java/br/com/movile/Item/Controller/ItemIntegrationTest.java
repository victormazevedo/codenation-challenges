package br.com.movile.Item.Controller;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.movile.item.model.Item;
import br.com.movile.item.repository.ItemRepository;
import io.restassured.RestAssured;
import io.restassured.mapper.TypeRef;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ItemIntegrationTest {

	@LocalServerPort
	private int port;

	@Autowired
	private ItemRepository itemRepository;

	@BeforeEach
	void setUp() {
		RestAssured.port = port;
		itemRepository.deleteAll();
		Item.ItemBuilder itemBuilder = new Item.ItemBuilder();
		BigDecimal bigDecimal = new BigDecimal("4.5");
		Item item = itemBuilder.setId("1").setDescription("Chocolate").setClassification("Doce")
				.setUnitPrice(bigDecimal).setAddressCity("PORTO ALEGRE").setRestaurant("NUMERO UM")
				.setRestaurantId("1234").builder();
		itemRepository.save(item);
	}

	@Test
	void shouldFindAllItems() {

		/*
		 * List<Item> item =
		 * given().accept("application/json").when().get("mapfood/items").then().extract
		 * () .as(new TypeRef<List<Item>>() { });
		 * 
		 * Assertions.assertEquals(1, item.size()); Assertions.assertAll(() ->
		 * Assertions.assertEquals("1", item.get(0).getId()), () ->
		 * Assertions.assertEquals("Chocolate", item.get(0).getDescription()), () ->
		 * Assertions.assertEquals("Doce", item.get(0).getClassification()), () ->
		 * Assertions.assertEquals("4.5", item.get(0).getUnitPrice()), () ->
		 * Assertions.assertEquals("PORTO ALEGRE", item.get(0).getAddressCity()), () ->
		 * Assertions.assertEquals("NUMERO UM", item.get(0).getRestaurant()), () ->
		 * Assertions.assertEquals("1234", item.get(0).getRestaurantId())
		 * 
		 * );
		 */
	}

}

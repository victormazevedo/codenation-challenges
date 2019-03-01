package br.com.movile.Item.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.movile.item.repository.ItemRepository;
import io.restassured.RestAssured;

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
		//Item item = new Item("id", "Chocolate", "Casa da Tia", "123", "Doces", 3.3, "PROTO ALEGRE");
		
	//	Item item = new Item();
		
		//itemRepository.save(item);
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

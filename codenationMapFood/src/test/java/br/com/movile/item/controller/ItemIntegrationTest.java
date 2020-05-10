package br.com.movile.item.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.math.BigDecimal;
import java.util.List;

import org.apache.http.HttpStatus;
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

	private BigDecimal price = new BigDecimal(4);

	@BeforeEach
	void setUp() {
		RestAssured.port = port;
		itemRepository.deleteAll();
		Item item = new Item("1", "Chocolate", "Casa da Tia", "123", "Doces", price, "PROTO ALEGRE");
		itemRepository.save(item);
	}

	@Test
	void shouldFindAllItems() {

		List<Item> items = given().accept("application/json").when().get("mapfood/items").then().extract()
				.as(new TypeRef<List<Item>>() {
				});

		Assertions.assertEquals(1, items.size());
		Assertions.assertAll(() -> Assertions.assertEquals("1", items.get(0).getId()),
				() -> Assertions.assertEquals("Chocolate", items.get(0).getDescription()),
				() -> Assertions.assertEquals("Casa da Tia", items.get(0).getRestaurant()),
				() -> Assertions.assertEquals("123", items.get(0).getRestaurantId()),
				() -> Assertions.assertEquals("Doces", items.get(0).getClassification()),
				() -> Assertions.assertEquals(price, items.get(0).getUnitPrice()),
				() -> Assertions.assertEquals("PROTO ALEGRE", items.get(0).getAddressCity()));
	}

	@Test
	void shouldFindOneItem() {

		Item item = given().accept("application/json").when().get("mapfood/items/id/1").then().extract().as(Item.class);

		Assertions.assertNotNull(item);
		Assertions.assertAll(() -> Assertions.assertEquals("1", item.getId()),
				() -> Assertions.assertEquals("Chocolate", item.getDescription()),
				() -> Assertions.assertEquals("Casa da Tia", item.getRestaurant()),
				() -> Assertions.assertEquals("123", item.getRestaurantId()),
				() -> Assertions.assertEquals("Doces", item.getClassification()),
				() -> Assertions.assertEquals(price, item.getUnitPrice()),
				() -> Assertions.assertEquals("PROTO ALEGRE", item.getAddressCity()));
	}
	
	@Test
	void shouldFindItemsByDescription() {
		
		Item item = new Item("2", "Chocalho", "Casa da Tia", "123", "Bebe", price, "PROTO ALEGRE");
		itemRepository.save(item);

		List<Item> items = given().accept("application/json").when().get("mapfood/items/descriptions/Choco").then().extract()
				.as(new TypeRef<List<Item>>() {
				});

		Assertions.assertEquals(1, items.size());
	}
	
	@Test
	void shouldFindItemsByRestaurant() {
		
		Item item = new Item("2", "Chocalho", "Casa da Vó", "12", "Bebe", price, "PROTO ALEGRE");
		itemRepository.save(item);

		List<Item> items = given().accept("application/json").when().get("mapfood/items/restaurants/Vó").then().extract()
				.as(new TypeRef<List<Item>>() {
				});

		Assertions.assertEquals(1, items.size());
	}
	
	@Test
	void shouldFindItemByRestaruantId() {
		
		Item item = new Item("2", "Chocalho", "Casa da Vó", "12", "Bebe", price, "PROTO ALEGRE");
		itemRepository.save(item);

		List<Item> items = given().accept("application/json").when().get("mapfood/items/restaurant/123").then().extract()
				.as(new TypeRef<List<Item>>() {
				});

		Assertions.assertEquals(1, items.size());
		Assertions.assertAll(() -> Assertions.assertEquals("1", items.get(0).getId()),
				() -> Assertions.assertEquals("Chocolate", items.get(0).getDescription()),
				() -> Assertions.assertEquals("Casa da Tia", items.get(0).getRestaurant()),
				() -> Assertions.assertEquals("123", items.get(0).getRestaurantId()),
				() -> Assertions.assertEquals("Doces", items.get(0).getClassification()),
				() -> Assertions.assertEquals(price, items.get(0).getUnitPrice()),
				() -> Assertions.assertEquals("PROTO ALEGRE", items.get(0).getAddressCity()));
	}
	
	@Test
	void shouldReturnExceptionIfFindOneNotFindItem() {

		given().accept("application/json")
			.when().get("mapfood/items/id/2")
			.then().statusCode(HttpStatus.SC_BAD_REQUEST)
			.body("message", equalTo("Nenhum Item com o Id: 2 foi encontrado"));
	}
	
	@Test
    void shouldInsertNewItem (){
        given()
                .contentType("application/json")
                .body(new Item("2", "Chocolate", "Casa da Tia", "123", "Doces", price, "PROTO ALEGRE"))
                .when()
                .post("mapfood/items")
                .then()
                .statusCode(HttpStatus.SC_CREATED);

        Assertions.assertEquals(2, itemRepository.findAll().size());
    }
    @Test
    void shouldNotInsertThatAlreadyExists (){
        given()
                .contentType("application/json")
                .body(new Item("1", "Chocolate", "Casa da Tia", "123", "Doces", price, "PROTO ALEGRE"))
                .when()
                .post("mapfood/items")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("message", equalTo("Item já existe na base de dados"));
    }
    @Test
    void shouldUpdateItem (){
        Item itemToBeUpdated = new Item("1", "Chocolate Branco", "Casa da Tia", "123", "Doces", price, "PROTO ALEGRE");
        given()
                .contentType("application/json")
                .body(itemToBeUpdated)
                .when()
                .put("mapfood/items")
                .then()
                .statusCode(HttpStatus.SC_ACCEPTED);

        Assertions.assertEquals(itemToBeUpdated.toString(), itemRepository.findById("1").get().toString() );
    }
    @Test
    void shouldNotUpdateItemThatNotExists (){
        Item itemNotToBeUpdated = new  Item("3", "Chocolate", "Casa da Tia", "123", "Doces", price, "PROTO ALEGRE");
        given()
                .contentType("application/json")
                .body(itemNotToBeUpdated)
                .when()
                .put("mapfood/items")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("message", equalTo("Item inexistente"));

    }

    @Test
    void shouldDeleteItem (){
        given()
                .accept("application/json")
                .when()
                .delete("mapfood/items/1")
                .then()
                .statusCode(HttpStatus.SC_ACCEPTED);

        Assertions.assertEquals(0, itemRepository.findAll().size());
    }
    @Test
    void shouldNotDeleteItemThatNotExist (){
        given()
                .accept("application/json")
                .when()
                .delete("mapfood/items/3")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);

    }

}

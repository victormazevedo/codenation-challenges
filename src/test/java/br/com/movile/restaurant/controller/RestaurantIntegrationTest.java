package br.com.movile.restaurant.controller;


import br.com.movile.restaurant.model.Restaurant;
import br.com.movile.restaurant.repository.RestaurantRepository;
import io.restassured.RestAssured;
import io.restassured.mapper.TypeRef;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RestaurantIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        restaurantRepository.deleteAll();
        restaurantRepository.save(new Restaurant("1", "McDonalds", "Rua 123", new GeoJsonPoint(50.0, 50.0), "Lanches"));
    }

    @Test
    void shouldFindAllRestaurants() {

        List<Restaurant> restaurants = given()
                .accept("application/json")
                .when()
                .get("mapfood/restaurants")
                .then()
                .extract()
                .as(new TypeRef<List<Restaurant>>() {
                });

        Assertions.assertEquals(1, restaurants.size());
        Assertions.assertAll(
                () -> Assertions.assertEquals("1", restaurants.get(0).getId()),
                () -> Assertions.assertEquals("McDonalds", restaurants.get(0).getName()),
                () -> Assertions.assertEquals("Rua 123", restaurants.get(0).getAddressCity()),
                () -> Assertions.assertEquals(50.00d, restaurants.get(0).getLocation().getX()),
                () -> Assertions.assertEquals(50.00d, restaurants.get(0).getLocation().getY()),
                () -> Assertions.assertEquals("Lanches", restaurants.get(0).getDishDescription())
        );
    }

    @Test
    void shouldFindOneRestaurant() {

            given()
                .accept("application/json")
                .when()
                .get("mapfood/restaurants/1")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo("1"))
                .body("name", equalTo("McDonalds"))
                .body("addressCity", equalTo("Rua 123"))
                .body("dishDescription", equalTo("Lanches"));


//        Assertions.assertAll(
//                () -> Assertions.assertEquals("1", restaurant.getId()),
//                () -> Assertions.assertEquals("McDonalds", restaurant.getName()),
//                () -> Assertions.assertEquals("Rua 123", restaurant.getAddressCity()),
//                () -> Assertions.assertEquals(50.00d, restaurant.getLocation().getX()),
//                () -> Assertions.assertEquals(50.00d, restaurant.getLocation().getY()),
//                () -> Assertions.assertEquals("Lanches", restaurant.getDishDescription())
//        );
    }

    @Test
    void shouldReturnExceptionIfFindOneNotFindRestaurant() {
        given()
                .accept("application/json")
                .when()
                .get("mapfood/restaurants/2")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("message", equalTo("Nenhum restaurante foi encontrado"));
    }

    @Test
    void shouldInsertNewRestaurant() {
        given()
                .contentType("application/json")
                .body(new Restaurant("3", "BK", "Rua", new GeoJsonPoint(50.0, 50.0), "Sanduba"))
                .when()
                .post("mapfood/restaurants")
                .then()
                .statusCode(HttpStatus.CREATED.value());

        Assertions.assertEquals(2, restaurantRepository.findAll().size());
    }

    @Test
    void shouldNotInsertThatAlreadyExists() {
        given()
                .contentType("application/json")
                .body(new Restaurant("1", "BK", "Rua", new GeoJsonPoint(50.0, 50.0), "Sanduba"))
                .when()
                .post("mapfood/restaurants")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("message", equalTo("Restaurante já existe na base de dados"));
    }


    @Test
    void shouldUpdateRestaurant() {
        Restaurant restaurantToBeUpdated = new Restaurant("1", "BK", "Rua", new GeoJsonPoint(50.0, 50.0), "Sanduba");
        given()
                .contentType("application/json")
                .body(restaurantToBeUpdated)
                .when()
                .put("mapfood/restaurants/1")
                .then()
                .statusCode(HttpStatus.ACCEPTED.value());

        Assertions.assertEquals(restaurantToBeUpdated.toString(), restaurantRepository.findById("1").get().toString());
    }

    @Test
    void shouldNotUpdateRestaurantThatNotExists() {
        Restaurant restaurantNotToBeUpdated = new Restaurant("2", "BK", "Rua", new GeoJsonPoint(50.0, 50.0), "Sanduba");
        given()
                .contentType("application/json")
                .body(restaurantNotToBeUpdated)
                .when()
                .put("mapfood/restaurants/2")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("message", equalTo("Restaurante não encontrado para update"));

    }

    @Test
    void shouldDeleteRestaurant() {
        given()
                .accept("application/json")
                .when()
                .delete("mapfood/restaurants/1")
                .then()
                .statusCode(HttpStatus.ACCEPTED.value());

        Assertions.assertEquals(0, restaurantRepository.findAll().size());
    }

    @Test
    void shouldNotDeleteRestaurantThatNotExist() {
        given()
                .accept("application/json")
                .when()
                .delete("mapfood/restaurants/2")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());

    }
}
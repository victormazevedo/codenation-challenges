package br.com.movile.order.controller;

import br.com.movile.customer.model.Customer;
import br.com.movile.item.model.Item;
import br.com.movile.order.model.Order;
import br.com.movile.order.model.OrderStatus;
import br.com.movile.order.repository.OrderRepository;
import br.com.movile.restaurant.model.Restaurant;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;

import static io.restassured.RestAssured.given;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderIntegrationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        orderRepository.deleteAll();
        orderRepository.save(
                new Order("5c745f50fa88992b9dd5fd19",
                        new Customer("321", -51.228496, -30.03742831),
                        LocalDateTime.now(),
                        new Restaurant("123", "McDonalds", "Rua 123", new GeoJsonPoint(50.0, 50.0), "Lanches"),
                        Collections.singletonList(new Item("1", "Big Mac", "McDonalds", "123", "Lanches", new BigDecimal(20), "PORTO ALEGRE")),
                        OrderStatus.OPENED));
    }

    @Test
    void shouldInsertNewOrder() {
        given()
                .contentType("application/json")
                .body(new Order("2",
                        new Customer("321", -51.228496, -30.03742831),
                        LocalDateTime.now(),
                        new Restaurant("123", "McDonalds", "Rua 123", new GeoJsonPoint(50.0, 50.0), "Lanches"),
                        Collections.singletonList(new Item("1", "Big Mac", "McDonalds", "123", "Lanches", new BigDecimal(20), "PORTO ALEGRE")),
                        OrderStatus.OPENED))
                .when()
                .post("mapfood/orders")
                .then()
                .statusCode(HttpStatus.SC_CREATED);

        Assertions.assertEquals(2, orderRepository.findAll().size());
    }

    @Test
    void shouldFindOneItem() {

        Order order = given().accept("application/json").when().get("mapfood/orders/5c745f50fa88992b9dd5fd19").then().extract().as(Order.class);

        Assertions.assertNotNull(order);
        Assertions.assertAll(() -> Assertions.assertEquals("5c745f50fa88992b9dd5fd19", order.getId()),
                () -> Assertions.assertEquals("321", order.getCustomer().getId()),
                () -> Assertions.assertEquals(-51.228496, order.getCustomer().getLongitude()),
                () -> Assertions.assertEquals(-30.03742831, order.getCustomer().getLatitude()),
                () -> Assertions.assertEquals(LocalDateTime.now(), order.getDate()),
                () -> Assertions.assertEquals("123", order.getRestaurant().getId()),
                () -> Assertions.assertEquals("McDonalds", order.getRestaurant().getName()),
                () -> Assertions.assertEquals("Rua123", order.getRestaurant().getAddressCity()),
                () -> Assertions.assertEquals(50.0, order.getRestaurant().getLocation().getX()),
                () -> Assertions.assertEquals(50.0, order.getRestaurant().getLocation().getY()),
                () -> Assertions.assertEquals("Lanches", order.getRestaurant().getDishDescription()),
                () -> Assertions.assertEquals("1", order.getItems().get(0).getId()),
                () -> Assertions.assertEquals("Big Mac", order.getItems().get(0).getDescription()),
                () -> Assertions.assertEquals("McDonalds", order.getItems().get(0).getRestaurant()),
                () -> Assertions.assertEquals("123", order.getItems().get(0).getRestaurantId()),
                () -> Assertions.assertEquals("Lanches", order.getItems().get(0).getClassification()),
                () -> Assertions.assertEquals(new BigDecimal(20), order.getItems().get(0).getUnitPrice()),
                () -> Assertions.assertEquals("PORTO ALEGRE", order.getItems().get(0).getAddressCity()),
                () -> Assertions.assertEquals(OrderStatus.OPENED, order.getStatus()));
    }
}

package br.com.movile.order.controller;

import br.com.movile.customer.model.Customer;
import br.com.movile.customer.repository.CustomerRepository;
import br.com.movile.item.model.Item;
import br.com.movile.item.repository.ItemRepository;
import br.com.movile.order.model.Order;
import br.com.movile.order.model.OrderStatus;
import br.com.movile.order.repository.OrderRepository;
import br.com.movile.restaurant.model.Restaurant;
import br.com.movile.restaurant.repository.RestaurantRepository;
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
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderIntegrationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        Item item = new Item("1", "Big Mac", "McDonalds", "123", "Lanches", new BigDecimal(20), "PORTO ALEGRE");
        itemRepository.deleteAll();
        itemRepository.save(item);

        customerRepository.deleteAll();
        GeoJsonPoint point = new GeoJsonPoint(-51.228496, -30.03742831);
        Customer customer = new Customer("321", point);
        customerRepository.save(customer);


        restaurantRepository.deleteAll();
        restaurantRepository.save(new Restaurant("123", "McDonalds", "Rua 123", new GeoJsonPoint(50.0, 50.0), "Lanches"));

        orderRepository.deleteAll();
        orderRepository.save(
                new Order("5c745f50fa88992b9dd5fd19",
                        new Customer("321", new GeoJsonPoint(-51.228496, -30.03742831)),
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
                        new Customer("321", new GeoJsonPoint(  -51.228496, -30.03742831)),
                        LocalDateTime.now(),
                        new Restaurant("123", "McDonalds", "Rua 123", new GeoJsonPoint(-51.24717, -30.107482), "Lanches"),
                        Collections.singletonList(new Item("1", "Big Mac", "McDonalds", "123", "Lanches", new BigDecimal(20), "PORTO ALEGRE")),
                        OrderStatus.OPENED))
                .when()
                .post("mapfood/orders")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK);

        Assertions.assertEquals(2, orderRepository.findAll().size());
    }

    @Test
    void shouldThrowExceptionIfDistanceIsTooHigh() {
        given()
                .contentType("application/json")
                .body(new Order("2",
                        new Customer("321", new GeoJsonPoint( -51.228496, -30.03742831)),
                        LocalDateTime.now(),
                        new Restaurant("123", "McDonalds", "Rua 123", new GeoJsonPoint(50.0, 50.0), "Lanches"),
                        Collections.singletonList(new Item("1", "Big Mac", "McDonalds", "123", "Lanches", new BigDecimal(20), "PORTO ALEGRE")),
                        OrderStatus.OPENED))
                .when()
                .post("mapfood/orders")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("message", equalTo("Customer too far!"));

    }
    @Test
    void shouldFindOneItem() {
        Order orderToBeFound  = orderRepository.findById("5c745f50fa88992b9dd5fd19").get();
        Restaurant restaurantFromOrder = orderToBeFound.getRestaurant();
        Customer customerFromOrder = orderToBeFound.getCustomer();
        Item itemFromOrder = orderToBeFound.getItems().get(0);

        given().accept("application/json")
                .when()
                .get("mapfood/orders/5c745f50fa88992b9dd5fd19")
                .then()
                .log()
                .all()
                .statusCode(org.springframework.http.HttpStatus.OK.value())
                .body("id", equalTo(orderToBeFound.getId()))
                .body("restaurant.id" , equalTo(restaurantFromOrder.getId()))
                .body("customer.id" , equalTo(customerFromOrder.getId()))
                .body("status" , equalTo(orderToBeFound.getStatus().toString()))
                .body("items[0].id", equalTo(itemFromOrder.getId()));

    }
}

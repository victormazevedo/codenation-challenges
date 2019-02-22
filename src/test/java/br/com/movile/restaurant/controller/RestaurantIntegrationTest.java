package br.com.movile.restaurant.controller;


import br.com.movile.restaurant.model.Restaurant;
import br.com.movile.restaurant.repository.RestaurantRepository;


import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static io.restassured.RestAssured.given;



@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestaurantIntegrationTest {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @LocalServerPort
    int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }


    @Test
    public void getRestaurants(@Autowired MongoTemplate mongoTemplate)  {
        restaurantRepository.save(new Restaurant( "1", "McDonalds", "Rua 123", 50.00, 50.00, "Lanches"));

        given()
                .accept("application/json")
                .when()
                .get("mapfood/restaurant")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", Matchers.equalTo("1"))
                .body("name", Matchers.equalTo("McDonalds"))
                .body("addressCity", Matchers.equalTo("Rua 123"))
                .body("longitude", Matchers.equalTo(50.f))
                .body("latitude", Matchers.equalTo( 50.f))
                .body("dishDescription", Matchers.equalTo( "Lanches"));
    }

}
package br.com.movile.Customer.Controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.List;

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

import br.com.movile.customer.model.Customer;
import br.com.movile.customer.repository.CustomerRepository;
import io.restassured.RestAssured;
import io.restassured.mapper.TypeRef;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerIntegrationTest {

	@LocalServerPort
	private int port;

	@Autowired
	private CustomerRepository customerRepository;

	@BeforeEach
	void setUp() {
		RestAssured.port = port;
		customerRepository.deleteAll();
		GeoJsonPoint point = new GeoJsonPoint(1009.0, 1233.0);
		Customer customer = new Customer("1", point);
		customerRepository.save(customer);
	}

	@Test
	void shouldFindAllItems() {

		List<Customer> customers = given().accept("application/json").when().get("mapfood/customers").then().extract()
				.as(new TypeRef<List<Customer>>() {
				});

		Assertions.assertEquals(1, customers.size());
		Assertions.assertAll(() -> Assertions.assertEquals("1", customers.get(0).getId()),
				() -> Assertions.assertEquals(1009.0, customers.get(0).getLocation().getX()),
				() -> Assertions.assertEquals(1233.0, customers.get(0).getLocation().getY()));
	}

	@Test
	void shouldFindOneCustomer() {

		Customer customer = given().accept("application/json").when().get("mapfood/customers/1").then().extract()
				.as(Customer.class);

		Assertions.assertNotNull(customer);
		Assertions.assertAll(() -> Assertions.assertEquals("1", customer.getId()),
				() -> Assertions.assertEquals(1009.0, customer.getLocation().getX()),
				() -> Assertions.assertEquals(1233.0, customer.getLocation().getY()));
	}

	@Test
	void shouldReturnExceptionIfFindOneNotFindCustomer() {

		given().accept("application/json").when().get("mapfood/customers/2").then()
				.statusCode(HttpStatus.SC_BAD_REQUEST)
				.body("message", equalTo("Nenhum cliente com o Id: 2 foi encontrado"));
	}

	@Test
	void shouldInsertNewCustomer() {
		GeoJsonPoint point = new GeoJsonPoint(1009.0, 1233.0);
		given().contentType("application/json")
			.body(new Customer("2", point))
			.when().post("mapfood/customers")
			.then().statusCode(HttpStatus.SC_CREATED);

		Assertions.assertEquals(2, customerRepository.findAll().size());
	}
	
	@Test
    void shouldNotInsertThatAlreadyExists (){
		GeoJsonPoint point = new GeoJsonPoint(1009.0, 1233.0);
        given()
                .contentType("application/json")
                .body(new Customer("1", point ))
                .when()
                .post("mapfood/customers")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("message", equalTo("Cliente já existe na base de dados"));
    }
	
	  @Test
	    void shouldUpdateCustomer (){
		  	GeoJsonPoint point = new GeoJsonPoint(1009.0, 1233.0);
	        Customer customerToBeUpdated = new Customer("1", point );
	        given()
	                .contentType("application/json")
	                .body(customerToBeUpdated)
	                .when()
	                .put("mapfood/customers")
	                .then()
	                .statusCode(HttpStatus.SC_ACCEPTED);

	        Assertions.assertEquals(customerToBeUpdated.toString(), customerRepository.findById("1").get().toString() );
	    }
	  
	  @Test
	    void shouldNotUpdateCustomerThatNotExists (){
		  	GeoJsonPoint point = new GeoJsonPoint(1009.0, 1233.0);
	        Customer customerNotToBeUpdated = new  Customer("3", point );
	        given()
	                .contentType("application/json")
	                .body(customerNotToBeUpdated)
	                .when()
	                .put("mapfood/customers")
	                .then()
	                .statusCode(HttpStatus.SC_BAD_REQUEST)
	                .body("message", equalTo("Cliente inexistente"));

	    }
	  
	  @Test
	    void shouldDeleteCustomer(){
	        given()
	                .accept("application/json")
	                .when()
	                .delete("mapfood/customers/1")
	                .then()
	                .statusCode(HttpStatus.SC_ACCEPTED);

	        Assertions.assertEquals(0, customerRepository.findAll().size());
	    }
	  
	    @Test
	    void shouldNotDeleteCustomerThatNotExist (){
	        given()
	                .accept("application/json")
	                .when()
	                .delete("mapfood/customers/3")
	                .then()
	                .statusCode(HttpStatus.SC_BAD_REQUEST);
	    }
}

package br.com.movile.motoboy;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.movile.motoboy.model.Motoboy;
import br.com.movile.motoboy.repository.MotoboyRepository;
import io.restassured.RestAssured;
import io.restassured.mapper.TypeRef;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MotoboyIntegrationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private MotoboyRepository motoBoyRepository;
    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        motoBoyRepository.deleteAll();
        GeoJsonPoint localizacao = new GeoJsonPoint(new Point(50.0, 50.0));
        
        motoBoyRepository.save(new Motoboy("id", localizacao, false));
    }

    @Test
    void shouldFindOneMotoboy (){
       Motoboy motoboy =  given()
                .accept("application/json")
                .when()
                .get("mapfood/motoboys/id")
                .then()
                .extract()
                .as(Motoboy.class);

        Assertions.assertAll(
                () -> Assertions.assertEquals("id", motoboy.getId()),
                () -> Assertions.assertEquals(50.00, motoboy.getLocation().getX()),
                () -> Assertions.assertEquals(50.00, motoboy.getLocation().getY()),
                () -> Assertions.assertEquals(false, motoboy.isBusy())
        );
    }
    @Test
    void shouldNotFindMotoboy (){
        given()
                .accept("application/json")
                .when()
                .get("mapfood/motoboys/doesNotExist")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("message", equalTo("Motoboy nao encontrado"));
    }
    @Test
    void shouldFindAllMotoboy (){
        List<Motoboy> motoboys =  given()
                .accept("application/json")
                .when()
                .get("mapfood/motoboys")
                .then()
                .extract()
                .as(new TypeRef<List<Motoboy>>() {});

        Assertions.assertEquals(1,motoboys.size());
        Assertions.assertAll(
                () -> Assertions.assertEquals("id", motoboys.get(0).getId()),
                () -> Assertions.assertEquals(50.00, motoboys.get(0).getLocation().getX()),
                () -> Assertions.assertEquals(50.00, motoboys.get(0).getLocation().getY()),
                () -> Assertions.assertEquals(false, motoboys.get(0).isBusy())
        );
    }

    @Test
    void shouldInsertMotoBoy () {
    	GeoJsonPoint localizacao = new GeoJsonPoint(new Point(50.0, 50.0));
    	
        given()
                .contentType("application/json")
                .body(new Motoboy("id1", localizacao, false))
                .when()
                .post("/mapfood/motoboys")
                .then()
                .statusCode(HttpStatus.CREATED.value());

        Assertions.assertEquals(2, motoBoyRepository.findAll().size());

    }
    @Test
    void shouldNotInsertMotoboyThatAlreadyExists () {
    	GeoJsonPoint localizacao = new GeoJsonPoint(new Point(50.0, 50.0));
        given()
                .contentType("application/json")
                .body(new Motoboy("id", localizacao, false))
                .when()
                .post("/mapfood/motoboys")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("message", equalTo("Motoboy ja existe na base de dados"));

    }

    @Test
    void shouldUpdateMotoboy (){
    	GeoJsonPoint localizacao = new GeoJsonPoint(new Point(50.0, 50.0));
        Motoboy newStateOfMotoboy = new Motoboy("id", localizacao, true);
        given()
                .contentType("application/json")
                .body(newStateOfMotoboy)
                .when()
                .put("/mapfood/motoboys/id")
                .then()
                .statusCode(HttpStatus.ACCEPTED.value());

        Assertions.assertEquals(newStateOfMotoboy.toString() ,motoBoyRepository.findById("id").get().toString());
    }

    @Test
    void shouldNotUpdateMotoboyThatNotExists (){
    	GeoJsonPoint localizacao = new GeoJsonPoint(new Point(50.0, 50.0));
    	
        Motoboy newStateOfMotoboy = new Motoboy("id1",localizacao, true);
        given()
                .contentType("application/json")
                .body(newStateOfMotoboy)
                .when()
                .put("/mapfood/motoboys/id1")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("message", equalTo("Motoboy não encontrado para update"));
    }

    @Test
    void shouldDeleteMotoboy (){
        given()
                .accept("application/json")
                .when()
                .delete("mapfood/motoboys/id")
                .then()
                .statusCode(HttpStatus.ACCEPTED.value());

        Assertions.assertEquals(0, motoBoyRepository.findAll().size());
    }

    @Test
    void shouldNotDeleteMotoboyThatNotExists(){
        given()
                .accept("application/json")
                .when()
                .delete("mapfood/motoboys/id1")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("message", equalTo("Motoboy não encontrado para delete"));
    }

}

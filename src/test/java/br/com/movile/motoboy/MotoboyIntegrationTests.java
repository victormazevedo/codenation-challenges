package br.com.movile.motoboy;


import br.com.movile.motoboy.repository.MotoBoyRepository;
import br.com.movile.restaurant.model.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MotoboyIntegrationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private MotoBoyRepository motoBoyRepository;
    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        restaurantRepository.deleteAll();
        restaurantRepository.save(new Restaurant("1", "McDonalds", "Rua 123", 50.00, 50.00, "Lanches"));
    }
}

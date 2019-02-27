package br.com.movile.motoboy;


import br.com.movile.motoboy.model.Motoboy;
import br.com.movile.motoboy.repository.MotoboyRepository;
import br.com.movile.restaurant.model.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    private MotoboyRepository motoBoyRepository;
    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        motoBoyRepository.deleteAll();
        motoBoyRepository.save(new Motoboy("id", 50.00, 50.00));
    }




}

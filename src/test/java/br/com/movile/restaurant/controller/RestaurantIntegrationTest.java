package br.com.movile.restaurant.controller;

import br.com.movile.restaurant.model.Restaurant;
import br.com.movile.restaurant.repository.RestaurantRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestaurantIntegrationTest {

    @MockBean
    private RestaurantRepository restaurantRepository;

    @Autowired
    private TestRestTemplate restTemplate;

     @Test
    public void getRestaurants()  {

         //Response
         List<Restaurant> listOfRetaurants = new ArrayList<>();
         listOfRetaurants.add(new Restaurant("admin", "McDonalds", "rua 123" , 10.35 , 11,"Muito bom lanche"));

         //given
         given(restaurantRepository.findAll()).willReturn(listOfRetaurants);

         //execute
         ResponseEntity<Restaurant[]> listOfallRestaurantsResponse = restTemplate.getForEntity("/restaurants", Restaurant[].class);

         //assert
        assertAll(
                () -> assertEquals(HttpStatus.OK, listOfallRestaurantsResponse.getStatusCode()),
                () -> assertEquals(Arrays.asList(listOfallRestaurantsResponse.getBody()).size() , 1)
            );
    }

}
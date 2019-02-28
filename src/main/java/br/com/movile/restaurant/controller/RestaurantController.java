package br.com.movile.restaurant.controller;

import br.com.movile.restaurant.model.Restaurant;
import br.com.movile.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }


    @GetMapping
    public List<Restaurant> findAll() {
        return restaurantService.getRestaurants();
    }

    @GetMapping("/{id}")
    public Restaurant findById(@PathVariable String id) {
        return restaurantService.findById(id);
    }

}

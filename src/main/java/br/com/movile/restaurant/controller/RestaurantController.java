package br.com.movile.restaurant.controller;

import br.com.movile.exception.model.ElementAlreadyExistException;
import br.com.movile.restaurant.model.Restaurant;
import br.com.movile.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    //Retrieval
    @GetMapping
    public List<Restaurant> findAll() {
        return restaurantService.getRestaurants();
    }
    //Retrieval
    @GetMapping("/{id}")
    public Restaurant findById(@PathVariable String id) {
        return restaurantService.findById(id);
    }

    //Create
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void insert(@RequestBody Restaurant restaurant ) throws ElementAlreadyExistException {
            restaurantService.insert(restaurant);
    }

    //Update

    //Delete

}

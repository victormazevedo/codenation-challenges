package br.com.movile.restaurant.service;

import br.com.movile.exception.model.ElementAlreadyExistException;
import br.com.movile.restaurant.model.Restaurant;
import br.com.movile.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant findById(String id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Nenhum restaurante foi encontrado"));
    }

    public void insert (Restaurant restaurant) throws ElementAlreadyExistException {
        try{
            findById(restaurant.getId());
            throw new ElementAlreadyExistException("Restaurante já existe na base de dados");

        }catch (NoSuchElementException e ){
            restaurantRepository.insert(restaurant);
        }
    }

    public void save(Restaurant restaurant) {
        restaurantRepository.findById(restaurant.getId())
                .orElseThrow(() -> new NoSuchElementException("Restaurante não encontrado para update"));

        restaurantRepository.save(restaurant);
    }
}

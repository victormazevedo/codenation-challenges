package br.com.movile.loader.controller;

import br.com.movile.customer.repository.CustomerRepository;
import br.com.movile.item.repository.ItemRepository;
import br.com.movile.loader.service.LoaderService;
import br.com.movile.motoboy.repository.MotoboyRepository;
import br.com.movile.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("load")
public class LoaderController {

    @Autowired
    private MotoboyRepository motoboyRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private ItemRepository itemRepository;


    @GetMapping
    public String load() {
        LoaderService loaderService = new LoaderService();
        loaderService.load();

        loaderService.getMotoboy().stream().forEach(x -> motoboyRepository.save(x));
        loaderService.getCustomers().stream().forEach(x -> customerRepository.save(x));
        loaderService.getRestaurants().stream().forEach(x -> restaurantRepository.save(x));
        loaderService.getItems().stream().forEach(x -> itemRepository.save(x));

        return "Carga Completa !";
    }

}

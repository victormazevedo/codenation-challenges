package br.com.movile.feeler;

import br.com.movile.customer.repository.CustomerRepository;
import br.com.movile.item.repository.ItemRepository;
import br.com.movile.motoboy.repository.MotoboyRepository;
import br.com.movile.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("feeler")
public class GeneralFeeler {

    @Autowired
    private MotoboyRepository motoboyRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private ItemRepository itemRepository;


    @GetMapping
    public String doFeel() {
        Feeler feeler = new Feeler();
        feeler.generalFeel();

        feeler.getMotoboy().stream().forEach(x -> motoboyRepository.save(x));
        feeler.getCustomers().stream().forEach(x -> customerRepository.save(x));
        feeler.getRestaurants().stream().forEach(x -> restaurantRepository.save(x));
        feeler.getItems().stream().forEach(x -> itemRepository.save(x));

        return "Carga Completa !";
    }

}

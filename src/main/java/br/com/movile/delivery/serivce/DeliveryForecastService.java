package br.com.movile.delivery.serivce;

import br.com.movile.delivery.model.Delivery;
import br.com.movile.delivery.model.dto.DeliveryForecast;
import org.springframework.stereotype.Service;

@Service
public class DeliveryForecastService {

    public DeliveryForecast calculateForecast(){
        return new DeliveryForecast();
    }
}

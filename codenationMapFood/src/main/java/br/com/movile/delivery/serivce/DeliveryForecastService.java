package br.com.movile.delivery.serivce;

import br.com.movile.delivery.model.dto.DeliveryForecast;
import br.com.movile.exception.model.CustomerTooFarException;
import br.com.movile.order.model.Order;
import br.com.movile.util.DistanceCalculator;
import org.springframework.stereotype.Service;

@Service
public class DeliveryForecastService {

    public DeliveryForecast calculateForecast(Order order) throws CustomerTooFarException {

        Double distanceBetweenCustomerAndRestaurant = DistanceCalculator.distance(
                order.getCustomer().getLocation().getX(),
                order.getCustomer().getLocation().getY(),
                order.getRestaurant().getLocation().getX(),
                order.getRestaurant().getLocation().getY(),
                "K");

        if (distanceBetweenCustomerAndRestaurant > 25) {
            throw new CustomerTooFarException("Customer too far!");
        }
        DeliveryForecast time = new DeliveryForecast();
        time.calculate(distanceBetweenCustomerAndRestaurant);

        return time;
    }
}

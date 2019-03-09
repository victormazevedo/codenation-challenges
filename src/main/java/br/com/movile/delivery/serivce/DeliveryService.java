package br.com.movile.delivery.serivce;

import br.com.movile.delivery.model.Delivery;
import br.com.movile.delivery.model.dto.DeliveryForecast;
import br.com.movile.delivery.repository.DeliveryRepository;
import br.com.movile.exception.model.CannotAddMoreOrderException;
import br.com.movile.order.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    @Value("${delivery.window.duration}")
    private Integer windowDuration;

    @Autowired
    public DeliveryService(
            DeliveryRepository deliveryRepository
    ) {
        this.deliveryRepository = deliveryRepository;
    }

    @Scheduled(fixedRate = 5000)
    private void closeExpiredDeliveries() {
        deliveryRepository
                .findAllToClose(false, LocalDateTime.now().minusMinutes(windowDuration))
                .forEach(this::closeDelivery);
    }

    private void closeDelivery(Delivery delivery) {
        delivery.setClosed(true);
        deliveryRepository.save(delivery);

        // dispara alguma coisa para definir o motoboy
    }

    public DeliveryForecast addOrderToDelivery(Order order) {
        Delivery delivery = findNearDeliveryInWindowTime(order);

        try {
            delivery.addOrder(order);
        } catch (CannotAddMoreOrderException e) {
            delivery = new Delivery();
            delivery.addOrder(order);
        } finally {
            deliveryRepository.save(delivery);
        }

        return new DeliveryForecast();
    }

    private Delivery findNearDeliveryInWindowTime(Order order) {
//        List<Delivery> deliveries = deliveryRepository
//                .findAllNearInWindowTime(order.getCustomer().getLocation());

//        Delivery delivery;
//        if (deliveries.size() > 0) {
//            delivery = deliveries.get(0);
//        } else {
//            delivery = new Delivery();
//        }

        return new Delivery();
    }
}

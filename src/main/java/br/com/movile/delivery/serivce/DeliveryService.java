package br.com.movile.delivery.serivce;

import br.com.movile.delivery.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    @Autowired
    public DeliveryService(
        DeliveryRepository deliveryRepository
    ){

        this.deliveryRepository = deliveryRepository;
    }

    @Scheduled(fixedRate = 5000)
    private void closeExpiredDeliveries() {

        // buscar deliveries que está aberta, com tempo maior do que o da janela


    }
}

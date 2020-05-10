package br.com.movile.delivery;

import br.com.movile.delivery.model.Delivery;
import br.com.movile.delivery.model.DeliveryStatus;
import br.com.movile.delivery.serivce.DeliveryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }


    @PatchMapping("{id}/status")
    public void changeStatus(@PathVariable("id") String id,
                             @RequestParam("value") DeliveryStatus status) {
        deliveryService.changeStatus(id, status);
    }

    @GetMapping
    public List<Delivery> findByStatus(
            @RequestParam(
                    value = "status",
                    defaultValue = "OPENED") DeliveryStatus status){
        return deliveryService.findByStatus(status);
    }
}

package br.com.movile.delivery.serivce;

import br.com.movile.delivery.model.Delivery;
import br.com.movile.delivery.model.DeliveryStatus;
import br.com.movile.delivery.repository.DeliveryRepository;
import br.com.movile.exception.model.CannotAddMoreOrderException;
import br.com.movile.exception.model.NoMotoboyAvailableException;
import br.com.movile.motoboy.model.Motoboy;
import br.com.movile.motoboy.service.MotoboyService;
import br.com.movile.order.model.Order;
import br.com.movile.order.model.OrderStatus;
import br.com.movile.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final MotoboyService motoboyService;

    @Autowired
    private OrderService orderService;
    private final MongoOperations mongoOperations;
    @Value("${delivery.window-duration}")
    private int windowDuration;
    @Value(("${delivery.max-distance}"))
    private double maxDistance;

    @Autowired
    public DeliveryService(
            DeliveryRepository deliveryRepository,
            MotoboyService motoboyService, MongoOperations mongoOperations) {
        this.deliveryRepository = deliveryRepository;
        this.motoboyService = motoboyService;
        this.mongoOperations = mongoOperations;
    }

    @Scheduled(fixedRate = 30000)
    private void closeExpiredDeliveries() throws NoMotoboyAvailableException {
        List<Delivery> deliveries = deliveryRepository
                .findAllOpened(DeliveryStatus.OPENED, LocalDateTime.now().minusMinutes(windowDuration));
        for (Delivery delivery : deliveries) {
            closeDeliveryAndAddMotoboy(delivery);
        }
    }

    private void closeDeliveryAndAddMotoboy(Delivery delivery) throws NoMotoboyAvailableException {
        Motoboy motoboy = findMotoboyForDelivery(delivery);

        delivery.setStatus(DeliveryStatus.CLOSED);
        delivery.setMotoboy(motoboy);

        deliveryRepository.save(delivery);
    }

    private Motoboy findMotoboyForDelivery(Delivery delivery) throws NoMotoboyAvailableException {
        Motoboy motoboy = motoboyService
                .searchBetterMotoboyForDelivery(delivery.getOrders().get(0).getRestaurant(), maxDistance);
        motoboy.setBusy(true);

        return motoboy;
    }

    public void addOrderToDelivery(Order order) throws NoMotoboyAvailableException {
        Delivery delivery = findNearDeliveryInWindowTime(order);

        try {
            delivery.addOrder(order);

            if (delivery.isComplete()) {
                closeDeliveryAndAddMotoboy(delivery);
            }
        } catch (CannotAddMoreOrderException e) {
            delivery = new Delivery();
            delivery.addOrder(order);
        } finally {
            deliveryRepository.save(delivery);
        }
    }

    private Delivery findNearDeliveryInWindowTime(Order order) {
        Optional<Delivery> delivery = deliveryRepository
                .findBetterDeliveryForOrder(DeliveryStatus.OPENED, order.getRestaurant().getId(),
                        LocalDateTime.now().minusMinutes(windowDuration));
        return delivery.orElse(new Delivery());
    }

    public void removeOrder(String orderId) throws NoMotoboyAvailableException {
        Delivery delivery = deliveryRepository.findByOrderId(orderId)
                .orElseThrow(() -> new NoSuchElementException("Delivery not found."));

        delivery.removeOrderById(orderId);
        deliveryRepository.save(delivery);

        orderService.changeStatus(orderId, OrderStatus.CANCELLED);
    }

    public void changeStatus(String id, DeliveryStatus status) {
        if (!DeliveryStatus.FINISHED.equals(status)) {
            throw new IllegalArgumentException("Só é permitido mudar o status para finalizado");
        }

        deliveryRepository.findById(id).ifPresent(delivery -> {
            delivery.setStatus(DeliveryStatus.FINISHED);

            deliveryRepository.save(delivery);

            Motoboy motoboy = delivery.getMotoboy();
            motoboy.setBusy(false);
            motoboyService.save(motoboy);
        });
    }

    public List<Delivery> findByStatus(DeliveryStatus status) {
        return deliveryRepository.findByStatus(status);
    }
}

package br.com.movile.delivery.serivce;

import br.com.movile.delivery.model.Delivery;
import br.com.movile.delivery.model.DeliveryStatus;
import br.com.movile.delivery.model.dto.DeliveryForecast;
import br.com.movile.delivery.repository.DeliveryRepository;
import br.com.movile.exception.model.CannotAddMoreOrderException;
import br.com.movile.exception.model.NoMotoboyAvailableException;
import br.com.movile.motoboy.model.Motoboy;
import br.com.movile.motoboy.service.MotoboyService;
import br.com.movile.order.model.Order;
import br.com.movile.order.model.OrderStatus;
import br.com.movile.order.service.OrderService;
import br.com.movile.restaurant.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final MotoboyService motoboyService;
    private final DeliveryForecastService deliveryForecastService;
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
            MotoboyService motoboyService, DeliveryForecastService deliveryForecastService, MongoOperations mongoOperations) {
        this.deliveryRepository = deliveryRepository;
        this.motoboyService = motoboyService;
        this.deliveryForecastService = deliveryForecastService;
        this.mongoOperations = mongoOperations;
    }

    @Scheduled(fixedRate = 30000)
    private void closeExpiredDeliveries() throws NoMotoboyAvailableException {
        List<Delivery> deliveries = deliveryRepository
                .findAllOpened(DeliveryStatus.OPENED, LocalDateTime.now().minusMinutes(windowDuration));
        for (Delivery delivery : deliveries) {
            makeDeliveryReady(delivery);
        }
    }

    private void makeDeliveryReady(Delivery delivery) throws NoMotoboyAvailableException {
        Motoboy motoboy = motoboyService
                .searchBetterMotoboyForDelivery(delivery.getOrders().get(0).getRestaurant(), maxDistance);

        delivery.setStatus(DeliveryStatus.READY);
        delivery.setMotoboy(motoboy);

        deliveryRepository.save(delivery);
    }

    public DeliveryForecast addOrderToDelivery(Order order) throws NoMotoboyAvailableException {
        Delivery delivery = findNearDeliveryInWindowTime(order);

        try {
            delivery.addOrder(order);

            if(delivery.isComplete()) {
                makeDeliveryReady(delivery);
            }
        } catch (CannotAddMoreOrderException e) {
            delivery = new Delivery();
            delivery.addOrder(order);
        } finally {
            deliveryRepository.save(delivery);
        }

        return deliveryForecastService.calculateForecast(delivery);
    }

    private Delivery findNearDeliveryInWindowTime(Order order) {
        /* TODO realizar a busca de Deliveries em que a Order possa entrar
        * Levar em consideração a distância entre os clientes de cada Order
        * Se o Status é OPENED, se é o mesmo restaurante e a Janela
        * */
        return new Delivery();
    }

//    public Motoboy findDeliveryWithNearOrder(Order order, Double distance) throws NoMotoboyAvailableException {
//
//        NearQuery nearQuery = generateNearQuery(order, distance);
//        GeoResults<Motoboy> geoNear = mongoOperations.geoNear(maxDistance, C.class);
//        if(geoNear.getContent().isEmpty()) {
//            throw new NoMotoboyAvailableException("No motoboy found nearby!");
//        } else
//            return geoNear.getContent().get(0).getContent();
//    }
//
//    private NearQuery generateNearQuery(Order order, Double distance) {
//        Point point = new Point(order.getCustomer().getLocation().getX(), order.getCustomer().getLocation().getY());
//        NearQuery nearQuery = NearQuery.near(point).inKilometers().maxDistance(distance, Metrics.KILOMETERS);
//        return nearQuery;
//    }


    public void removeOrder(String id, String orderId) throws NoMotoboyAvailableException {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Delivery not found."));

        delivery.removeOrderById(orderId);
        deliveryRepository.save(delivery);

        orderService.changeStatus(orderId, OrderStatus.CANCELLED);
    }
}

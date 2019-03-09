package br.com.movile.delivery.repository;

import br.com.movile.delivery.model.Delivery;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface DeliveryRepository extends MongoRepository<Delivery, String> {

    @Query("{ 'closed': ?0, 'windowBegin' : { $lt : ?1 } }")
    List<Delivery> findAllToClose(Boolean closed, LocalDateTime minimalTime);

//    List<Delivery> findAllNearInWindowTime( GeoJsonPoint location);
}

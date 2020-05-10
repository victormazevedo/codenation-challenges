package challenge;

import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RestaurantMongoRepository extends MongoRepository<RestaurantMongo, String> {
    List<RestaurantMongo> findAllByLocationIsWithinOrderByNameAsc(GeoJsonPolygon geometry);
}

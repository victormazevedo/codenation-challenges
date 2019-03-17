package challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	private final RedisOperations<String, NeighborhoodRedis> redisOperations;

	private final NeighborhoodMongoRepository neighborhoodMongoRepository;

	private final RestaurantMongoRepository restaurantMongoRepository;

	@Autowired
	public RestaurantServiceImpl(RedisOperations<String, NeighborhoodRedis> redisOperations, NeighborhoodMongoRepository neighborhoodMongoRepository, RestaurantMongoRepository restaurantMongoRepository) {
		this.redisOperations = redisOperations;
		this.neighborhoodMongoRepository = neighborhoodMongoRepository;
		this.restaurantMongoRepository = restaurantMongoRepository;
	}

	@Override
	public NeighborhoodRedis findInNeighborhood(double x, double y) {
		NeighborhoodMongo neighborhoodMongo = findNeighborhood(x, y);

		String redisKey = "neighborhood:" + neighborhoodMongo.getId();
		NeighborhoodRedis neighborhoodRedis = redisOperations.opsForValue().get(redisKey);

		if (neighborhoodRedis == null) {

			neighborhoodRedis = NeighborhoodRedis.neighborhoodMongoToNeighborhoodRedis(neighborhoodMongo);

			neighborhoodRedis.setRestaurants(getRestaurantsInNeighborhood(neighborhoodMongo));

			redisOperations.opsForValue().set(redisKey, neighborhoodRedis);
		}

		return neighborhoodRedis;
	}

	private NeighborhoodMongo findNeighborhood(double x, double y) {
		GeoJsonPoint point = new GeoJsonPoint(new Point(x, y));
		return neighborhoodMongoRepository.findByGeometryIntersects(point);
	}

	private List<RestaurantRedis> getRestaurantsInNeighborhood(NeighborhoodMongo neighborhoodMongo) {
		return restaurantMongoRepository
				.findAllByLocationIsWithinOrderByNameAsc(neighborhoodMongo.getGeometry())
				.stream()
				.sequential()
				.map(RestaurantRedis::restaurantMongoToRestaurantRedis)
				.collect(Collectors.toList());
	}

}

package challenge;

import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Classe para mapear o bairro no Redis
 *
 */
public class NeighborhoodRedis {

    @Id
    private String id;

    private String name;

    private List<RestaurantRedis> restaurants;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RestaurantRedis> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<RestaurantRedis> restaurants) {
        this.restaurants = restaurants;
    }

    public static NeighborhoodRedis from(NeighborhoodMongo neighborhoodMongo) {
        final NeighborhoodRedis neighborhoodRedis = new NeighborhoodRedis();
        neighborhoodRedis.setId(neighborhoodMongo.getId());
        neighborhoodRedis.setName(neighborhoodMongo.getName());

        return neighborhoodRedis;
    }
}

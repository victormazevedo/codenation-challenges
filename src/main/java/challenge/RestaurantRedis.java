package challenge;

import org.springframework.data.annotation.Id;

/**
 * Classe para mapear o restaurante no Redis
 *
 */
public class RestaurantRedis {

    @Id
    private String id;

    private String name;

    private Double x;

    private Double y;

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

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public static RestaurantRedis getInstance(RestaurantMongo restaurantMongo) {

        final RestaurantRedis restaurantRedis = new RestaurantRedis();
        restaurantRedis.setId(restaurantMongo.getId());
        restaurantRedis.setName(restaurantMongo.getName());
        restaurantRedis.setX(restaurantMongo.getLocation().getX());
        restaurantRedis.setY(restaurantMongo.getLocation().getY());

        return restaurantRedis;
    }
}

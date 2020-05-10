package challenge;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Classe para mapear o bairro no MongoDB
 *
 */
@Document(collection = "neighborhood")
public class NeighborhoodMongo {

    @Id
    private String id;

    private GeoJsonPolygon geometry;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GeoJsonPolygon getGeometry() {
        return geometry;
    }

    public void setGeometry(GeoJsonPolygon geometry) {
        this.geometry = geometry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

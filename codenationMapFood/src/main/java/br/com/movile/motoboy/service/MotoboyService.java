package br.com.movile.motoboy.service;


import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.stereotype.Service;

import br.com.movile.exception.model.ElementAlreadyExistException;
import br.com.movile.exception.model.NoMotoboyAvailableException;
import br.com.movile.motoboy.model.Motoboy;
import br.com.movile.motoboy.repository.MotoboyRepository;
import br.com.movile.restaurant.model.Restaurant;

@Service
public class MotoboyService {


    private  final MotoboyRepository motoboyRepository;
    
	private final MongoOperations mongoOperations;
    
    @Autowired
    public MotoboyService(MotoboyRepository motoboyRepository, MongoOperations mongoOperations) {
        this.motoboyRepository = motoboyRepository;
        this.mongoOperations = mongoOperations;
    }

    public Motoboy findById(String id) {
        return motoboyRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Motoboy nao encontrado"));
    }

    public List<Motoboy> findAll(){
        return motoboyRepository.findAll();
    }

    public void insert(Motoboy motoboy) throws Exception {
        try{
            Motoboy checkIfMotoBoyExists =  findById(motoboy.getId());
            throw new ElementAlreadyExistException("Motoboy ja existe na base de dados");

        }catch (NoSuchElementException e ){
            motoboyRepository.insert(motoboy);
        }
    }

    public void save(Motoboy motoboy) {
        motoboyRepository.findById(motoboy.getId())
                .orElseThrow(() -> new NoSuchElementException("Motoboy não encontrado para update"));
        motoboyRepository.save(motoboy);

    }

    public void delete(String id) {
        motoboyRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Motoboy não encontrado para delete"));
        motoboyRepository.deleteById(id);
    }
    
    
    public Motoboy searchBetterMotoboyForDelivery(Restaurant restaurant, Double distance) throws NoMotoboyAvailableException {

		NearQuery maxDistance = generateQueryForSearch(restaurant, distance);
		GeoResults<Motoboy> geoNear = mongoOperations.geoNear(maxDistance, Motoboy.class);
		if(geoNear.getContent().isEmpty()) {
			throw new NoMotoboyAvailableException("No motoboy found nearby!");
		} else
			return geoNear.getContent().get(0).getContent();
	}

	private NearQuery generateQueryForSearch(Restaurant restaurant, Double distance) {
		Point point = new Point(restaurant.getLocation().getX(), restaurant.getLocation().getY());
		NearQuery maxDistance = NearQuery.near(point).inKilometers().maxDistance(distance, Metrics.KILOMETERS);
		return maxDistance;
	}
    
}

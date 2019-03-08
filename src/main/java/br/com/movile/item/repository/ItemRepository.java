package br.com.movile.item.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.movile.item.model.Item;

@Repository
public interface ItemRepository extends MongoRepository<Item, String> {

	List<Item> findByDescriptionLike(String description);

	List<Item> findByRestaurantLike(String restaurant);
	
	List<Item> findByRestaurantId(String restaurantId);

}

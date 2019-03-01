package br.com.movile.item.repository;


import java.util.List;

import org.bson.types.Decimal128;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.movile.item.model.Item;

@Repository
public interface ItemRepository extends MongoRepository<Item, String> {
	
	List<Item> findByUnitPriceLessThan(Decimal128 limitPrice);

}

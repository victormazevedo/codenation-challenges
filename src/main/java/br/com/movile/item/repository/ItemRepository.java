package br.com.movile.item.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.movile.item.model.Item;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.function.Predicate;

@Repository
public interface ItemRepository extends MongoRepository<Item, String> {

    //List<Item> findAllUnitPriceLessThan(String limitPrice);
}

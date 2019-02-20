package br.com.movile.item.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.movile.item.model.Item;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends MongoRepository<Item, String> {

}

package br.com.movile.item.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.movile.item.model.Item;

@Repository
public interface ItemRepository extends MongoRepository<Item, String> {

}

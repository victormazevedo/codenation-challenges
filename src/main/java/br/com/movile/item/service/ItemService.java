package br.com.movile.item.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import br.com.movile.item.model.Item;
import br.com.movile.item.repository.ItemRepository;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;
    
    @Autowired
    private MongoTemplate mongoTemplate;
    

    public List<Item> findAll(){

        return itemRepository.findAll();
    }


    public List<Item> findAllLimitPrice(BigDecimal limitPrice){
    	List<Item> listItemLimitPrice = mongoTemplate.find(Query.query(Criteria.where("unitPrice").lt(limitPrice)), Item.class);
        return listItemLimitPrice;
    }


    public Item findById(String id) throws Exception {
        return itemRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Nenhum Item com o Id: "+ id + " foi encontrado"));
    }

    public Item inset(Item item){
        return itemRepository.insert(item);
    }

    public Item update(Item item){
        if(!itemRepository.existsById(item.getId())) {
            throw new NoSuchElementException("Item inexistente");
        }
        return itemRepository.save(item);
    }

    public void delete(Item item){
    	if(!itemRepository.existsById(item.getId()))
    		throw new NoSuchElementException("Item não encontrado");
        itemRepository.delete(item);
    }


}

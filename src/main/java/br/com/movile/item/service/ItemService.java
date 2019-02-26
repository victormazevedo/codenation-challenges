package br.com.movile.item.service;

import br.com.movile.item.model.Item;
import br.com.movile.item.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> findAll(){

        return itemRepository.findAll();
    }

/*
    public List<Item> findAllWithLimitPrice(String limitPrice){

        return itemRepository.findAllUnitPriceLessThan(limitPrice);
    }
*/

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


}

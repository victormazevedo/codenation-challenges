package br.com.movile.item.controller;

import java.math.BigDecimal;
import java.util.List;

import org.bson.types.Decimal128;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.movile.item.model.Item;
import br.com.movile.item.service.ItemService;

@RestController
@RequestMapping("items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> findAll(){

        return itemService.findAll();
    }

    @GetMapping("{id}")
    public Item findById(@PathVariable("id") String id) throws Exception {
        return itemService.findById(id);
    }

    //TODO: Verificar Item preco, nao funciona somente com Decimal128, sera necessario uma conversao
    @GetMapping("/price/{limitPrice}")
    public List<Item> findAllLimitPrice(@PathVariable Decimal128 limitPrice){
        return itemService.findAllLimitPrice(limitPrice);
    }


    @PostMapping
    public ResponseEntity<Item> insert(@RequestBody Item item){
        itemService.inset(item);
        return ResponseEntity.status(201).build();
    }

    @PutMapping
    public  ResponseEntity<Item> update(@RequestBody Item item) {
        itemService.update(item);
        return ResponseEntity.status(202).build();
    }
    
    @DeleteMapping
    public  ResponseEntity<Item> delete(@RequestBody Item item) {
        itemService.delete(item);
        return ResponseEntity.ok().build();
    }

}

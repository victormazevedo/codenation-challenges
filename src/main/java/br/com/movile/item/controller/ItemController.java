package br.com.movile.item.controller;

import br.com.movile.item.model.Item;
import br.com.movile.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

/*

    @GetMapping("{limitPrice}")
    public List<Item> findAllWihtLimitPrice(@PathVariable String limitPrice){
        return itemService.findAll();
    }
*/

    @PostMapping()
    public ResponseEntity<Item> insert(@RequestBody Item item){
        itemService.inset(item);
        return ResponseEntity.status(201).build();
    }

    @PutMapping()
    public  ResponseEntity<Item> update(@RequestBody Item customer) {
        itemService.update(customer);
        return ResponseEntity.status(202).build();
    }

}

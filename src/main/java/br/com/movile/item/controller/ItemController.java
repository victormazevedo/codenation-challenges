package br.com.movile.item.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.movile.item.model.Item;
import br.com.movile.item.service.ItemService;

@RestController
@RequestMapping("items")
public class ItemController {

	private ItemService itemService;

	@Autowired
	public ItemController(ItemService itemService) {
		this.itemService = itemService;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Item> findAll() {
		return itemService.findAll();
	}

	@GetMapping("/id/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Item findById(@PathVariable("id") String id){
		return itemService.findById(id);
	}

	@GetMapping("/descriptions/{description}")
	@ResponseStatus(HttpStatus.OK)
	public List<Item> findByDescription(@PathVariable("description") String description) {
		return itemService.findByDescriptionLike(description);
	}
	
	@GetMapping("/restaurants/{restaurant}")
	@ResponseStatus(HttpStatus.OK)
	public List<Item> findByRestaurant(@PathVariable("restaurant") String restaurant) {
		return itemService.findByRestaurantLike(restaurant);
	}

	
	@GetMapping("/restaurant/{restaurantId}")
	@ResponseStatus(HttpStatus.OK)
	public List<Item> findByRestaurantId(@PathVariable("restaurantId") String restaurantId) {
		return itemService.findByRestaurantId(restaurantId);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Item insert(@Valid @RequestBody Item item){
		return itemService.inset(item);
	}

	@PutMapping
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Item update(@Valid @RequestBody Item item) {
		return itemService.update(item);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void delete(@PathVariable String id) {
		itemService.delete(id);
	}

}

package br.com.movile.item.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.bson.types.Decimal128;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.movile.item.model.Item;
import br.com.movile.item.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;

	public List<Item> findAll() {

		return itemRepository.findAll();
	}

	public List<Item> findByDescriptionLike(String description) {
		if (itemRepository.findByDescriptionLike(description).isEmpty())
			throw new NoSuchElementException("Nenhum item encontrato");
		return itemRepository.findByDescriptionLike(description);
	}

	public List<Item> findByRestaurantLike(String restaurant) {
		if (itemRepository.findByRestaurantLike(restaurant).isEmpty())
			throw new NoSuchElementException("Nenhum item encontrato");
		return itemRepository.findByRestaurantLike(restaurant);
	}
	
	public List<Item> findByRestaurantId(String restaurantId) {
		if (itemRepository.findByRestaurantId(restaurantId).isEmpty())
			throw new NoSuchElementException("Nenhum item encontrato");
		return itemRepository.findByRestaurantId(restaurantId);
	}

	public List<Item> findAllLimitPrice(Decimal128 limitPrice) {
		return itemRepository.findByUnitPriceLessThan(limitPrice);
	}

	public Item findById(String id) throws Exception {
		return itemRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Nenhum Item com o Id: " + id + " foi encontrado"));
	}

	public Item inset(Item item) {
		return itemRepository.insert(item);
	}

	public Item update(Item item) {
		if (!itemRepository.existsById(item.getId())) {
			throw new NoSuchElementException("Item inexistente");
		}
		return itemRepository.save(item);
	}

	public void delete(Item item) {
		if (!itemRepository.existsById(item.getId()))
			throw new NoSuchElementException("Item não encontrado");
		itemRepository.delete(item);
	}

}

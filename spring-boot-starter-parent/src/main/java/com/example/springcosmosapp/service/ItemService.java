package com.example.springcosmosapp.service;

import com.example.springcosmosapp.model.Item;
import com.example.springcosmosapp.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Iterable<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(String id) {
        return itemRepository.findById(id).orElse(null);
    }

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public Item updateItem(String id, Item item) {
        Item existing = itemRepository.findById(id).orElse(null);
        if (existing == null) return null;
        existing.setCategory(item.getCategory());
        existing.setName(item.getName());
        existing.setPrice(item.getPrice());
        return itemRepository.save(existing);
    }

    public boolean deleteItem(String id) {
        Item existing = itemRepository.findById(id).orElse(null);
        if (existing != null) {
            itemRepository.delete(existing);
            return true;
        }
        return false;
    }
}

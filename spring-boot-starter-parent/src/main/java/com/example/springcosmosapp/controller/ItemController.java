package com.example.springcosmosapp.controller;

import com.example.springcosmosapp.model.Item;
import com.example.springcosmosapp.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Item>> getAll() {
        return ResponseEntity.ok(itemService.getAllItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getById(@PathVariable String id) {
        Item item = itemService.getItemById(id);
        return (item == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(item);
    }

    @PostMapping
    public ResponseEntity<Item> create(@RequestBody Item item) {
        return ResponseEntity.ok(itemService.createItem(item));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> update(@PathVariable String id, @RequestBody Item item) {
        Item updated = itemService.updateItem(id, item);
        return (updated == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        return itemService.deleteItem(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

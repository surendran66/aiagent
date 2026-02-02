package com.example.springcosmosapp.service;

import com.example.springcosmosapp.model.Item;
import com.example.springcosmosapp.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*;

class ItemServiceTest {
    private ItemRepository itemRepository;
    private ItemService itemService;

    @BeforeEach
    void setup() {
        itemRepository = Mockito.mock(ItemRepository.class);
        itemService = new ItemService(itemRepository);
    }

    @Test
    void testCreateAndGetItem() {
        Item item = new Item("cat1", "name1", 1.1);
        Mockito.when(itemRepository.save(item)).thenReturn(item);
        assertThat(itemService.createItem(item)).isEqualTo(item);
    }

    @Test
    void testGetAllItems() {
        Iterable<Item> items = java.util.List.of(new Item("cat1","name1",1.1));
        Mockito.when(itemRepository.findAll()).thenReturn(items);
        assertThat(itemService.getAllItems()).containsExactlyElementsOf((java.util.List<Item>)items);
    }

    @Test
    void testUpdateItem() {
        Item old = new Item("cat1","oldname",2.3);
        old.setId("123");
        Item updated = new Item("cat2","newname",9.9);
        Mockito.when(itemRepository.findById("123")).thenReturn(Optional.of(old));
        Mockito.when(itemRepository.save(Mockito.any(Item.class))).thenReturn(updated);
        assertThat(itemService.updateItem("123", updated).getName()).isEqualTo("newname");
    }

    @Test
    void testDeleteItemFound() {
        Item item = new Item("cat1","name1",1.1);
        item.setId("abc");
        Mockito.when(itemRepository.findById("abc")).thenReturn(Optional.of(item));
        Mockito.doNothing().when(itemRepository).delete(item);
        assertThat(itemService.deleteItem("abc")).isTrue();
    }

    @Test
    void testDeleteItemNotFound() {
        Mockito.when(itemRepository.findById("xyz")).thenReturn(Optional.empty());
        assertThat(itemService.deleteItem("xyz")).isFalse();
    }
}

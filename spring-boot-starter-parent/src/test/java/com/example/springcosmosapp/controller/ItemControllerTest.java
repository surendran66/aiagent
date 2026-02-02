package com.example.springcosmosapp.controller;

import com.example.springcosmosapp.model.Item;
import com.example.springcosmosapp.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

class ItemControllerTest {
    private ItemService itemService;
    private ItemController itemController;

    @BeforeEach
    void setup() {
        itemService = Mockito.mock(ItemService.class);
        itemController = new ItemController(itemService);
    }

    @Test
    void testGetAll() {
        List<Item> items = List.of(new Item("cat","n",2.0));
        Mockito.when(itemService.getAllItems()).thenReturn(items);
        assertThat(itemController.getAll().getBody()).containsExactlyElementsOf(items);
    }

    @Test
    void testGetByIdFound() {
        Item item = new Item("cat","n",3.0);
        Mockito.when(itemService.getItemById("id1")).thenReturn(item);
        assertThat(itemController.getById("id1").getBody()).isEqualTo(item);
    }

    @Test
    void testGetByIdNotFound() {
        Mockito.when(itemService.getItemById("no")).thenReturn(null);
        assertThat(itemController.getById("no").getStatusCodeValue()).isEqualTo(404);
    }

    @Test
    void testCreate() {
        Item item = new Item("cat","n",1.0);
        Mockito.when(itemService.createItem(item)).thenReturn(item);
        assertThat(itemController.create(item).getBody()).isEqualTo(item);
    }

    @Test
    void testUpdateFound() {
        Item item = new Item("cat","n",2.2);
        Mockito.when(itemService.updateItem("id",item)).thenReturn(item);
        assertThat(itemController.update("id",item).getBody()).isEqualTo(item);
    }

    @Test
    void testUpdateNotFound() {
        Mockito.when(itemService.updateItem("id",Mockito.any())).thenReturn(null);
        assertThat(itemController.update("id",new Item()).getStatusCodeValue()).isEqualTo(404);
    }

    @Test
    void testDeleteFound() {
        Mockito.when(itemService.deleteItem("id")).thenReturn(true);
        assertThat(itemController.delete("id").getStatusCodeValue()).isEqualTo(204);
    }

    @Test
    void testDeleteNotFound() {
        Mockito.when(itemService.deleteItem("id")).thenReturn(false);
        assertThat(itemController.delete("id").getStatusCodeValue()).isEqualTo(404);
    }
}

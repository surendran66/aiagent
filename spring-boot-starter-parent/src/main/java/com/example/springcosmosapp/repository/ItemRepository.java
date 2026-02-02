package com.example.springcosmosapp.repository;

import com.example.springcosmosapp.model.Item;
import com.azure.spring.data.cosmos.repository.CosmosRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CosmosRepository<Item, String> {
}

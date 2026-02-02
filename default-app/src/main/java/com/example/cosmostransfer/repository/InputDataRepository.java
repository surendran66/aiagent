package com.example.cosmostransfer.repository;

import com.example.cosmostransfer.model.DataItem;
import com.azure.spring.data.cosmos.repository.CosmosRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InputDataRepository extends CosmosRepository<DataItem, String> {
}

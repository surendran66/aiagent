package com.example.cosmostransfer.repository;

import com.example.cosmostransfer.model.OutputDataItem;
import com.azure.spring.data.cosmos.repository.CosmosRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutputDataRepository extends CosmosRepository<OutputDataItem, String> {
}

package com.example.datatojsontransformation.repository;

import com.example.datatojsontransformation.model.OutputRecord;
import com.azure.spring.data.cosmos.repository.CosmosRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutputDataRepository extends CosmosRepository<OutputRecord, String> {
}

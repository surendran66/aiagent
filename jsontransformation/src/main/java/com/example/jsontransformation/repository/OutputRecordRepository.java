package com.example.jsontransformation.repository;

import com.example.jsontransformation.model.OutputRecord;
import com.azure.spring.data.cosmos.repository.CosmosRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutputRecordRepository extends CosmosRepository<OutputRecord, String> {
}

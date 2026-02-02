package com.example.jsontransform.repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.example.jsontransform.model.OutputRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface OutputRecordRepository extends CosmosRepository<OutputRecord, String> {
}

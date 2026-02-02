package com.example.jsontransform.repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.example.jsontransform.model.InputRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface InputRecordRepository extends CosmosRepository<InputRecord, String> {
}

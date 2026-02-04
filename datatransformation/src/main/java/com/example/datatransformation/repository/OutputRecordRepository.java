package com.example.datatransformation.repository;

import com.example.datatransformation.model.Outputdata;
import com.azure.spring.data.cosmos.repository.CosmosRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutputRecordRepository extends CosmosRepository<Outputdata, String> {
}

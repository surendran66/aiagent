package com.example.cosmostransfer.repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.example.cosmostransfer.model.OutputRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface OutputRecordRepository extends CosmosRepository<OutputRecord, String> {
}

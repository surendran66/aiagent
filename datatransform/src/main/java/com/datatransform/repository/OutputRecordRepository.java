package com.datatransform.repository;

import com.datatransform.model.OutputRecord;
import com.azure.spring.data.cosmos.repository.CosmosRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutputRecordRepository extends CosmosRepository<OutputRecord, String> {
}

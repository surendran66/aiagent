package com.surendran.jsontojsontaransformsurendran.repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.surendran.jsontojsontaransformsurendran.model.OutputRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface OutputRecordRepository extends CosmosRepository<OutputRecord, String> {
}

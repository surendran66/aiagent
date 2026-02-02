package com.surendran.jsontojsontaransformsurendran.repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.surendran.jsontojsontaransformsurendran.model.InputRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface InputRecordRepository extends CosmosRepository<InputRecord, String> {
}

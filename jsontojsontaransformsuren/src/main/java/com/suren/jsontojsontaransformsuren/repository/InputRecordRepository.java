package com.suren.jsontojsontaransformsuren.repository;

import com.suren.jsontojsontaransformsuren.model.InputRecord;
import com.azure.spring.data.cosmos.repository.CosmosRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InputRecordRepository extends CosmosRepository<InputRecord, String> {
}

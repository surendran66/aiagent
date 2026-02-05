package com.optum.datatojsontransformationoptum.repository;

import com.optum.datatojsontransformationoptum.model.InputRecord;
import com.azure.spring.data.cosmos.repository.CosmosRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InputDataRepository extends CosmosRepository<InputRecord, String> {
}

package com.example.datatransformation.repository;

import com.example.datatransformation.model.Inputdata;
import com.azure.spring.data.cosmos.repository.CosmosRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InputDataRepository extends CosmosRepository<Inputdata, String> {
}

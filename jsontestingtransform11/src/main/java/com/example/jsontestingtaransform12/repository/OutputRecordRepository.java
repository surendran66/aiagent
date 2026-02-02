package com.example.jsontestingtaransform12.repository;

import com.example.jsontestingtaransform12.model.OutputRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutputRecordRepository extends CrudRepository<OutputRecord, Long> {
}

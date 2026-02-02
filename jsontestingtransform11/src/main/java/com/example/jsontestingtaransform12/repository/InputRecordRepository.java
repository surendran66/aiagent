package com.example.jsontestingtaransform12.repository;

import com.example.jsontestingtaransform12.model.InputRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InputRecordRepository extends CrudRepository<InputRecord, Long> {
}

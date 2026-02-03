package com.suri.repository;

import com.suri.model.InputRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InputRecordRepository extends CrudRepository<InputRecord, Long> {
}

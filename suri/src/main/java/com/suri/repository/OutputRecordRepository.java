package com.suri.repository;

import com.suri.model.OutputRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutputRecordRepository extends CrudRepository<OutputRecord, Long> {
}

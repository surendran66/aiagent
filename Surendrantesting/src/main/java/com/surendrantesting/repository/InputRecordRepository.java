package com.surendrantesting.repository;
import com.surendrantesting.model.InputRecord;
import org.springframework.data.jpa.repository.JpaRepository;
public interface InputRecordRepository extends JpaRepository<InputRecord, Long> {
}

package com.example.testingworkflow.repository;

import com.example.testingworkflow.model.InputRecord;
import java.util.List;

public interface InputRecordRepository {
    List<InputRecord> findAll();
}

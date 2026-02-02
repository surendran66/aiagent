package com.secondautomation.repository;

import com.secondautomation.model.InputRecord;

public interface InputRecordRepository {
    Iterable<InputRecord> findAll();
}
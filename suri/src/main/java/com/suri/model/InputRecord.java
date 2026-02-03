package com.suri.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class InputRecord {
    @Id
    private Long id;
    private String system;
    private String status;
    private String receivedTime;
    private String completedTime;
    private String user;
}

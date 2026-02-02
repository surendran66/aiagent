package com.example.jsontestingtaransform12.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class InputRecord {
    @Id
    private Long id;
    private String receivedTime;
    private String completedTime;
    private String system;
    private String status;
    private String user;
}

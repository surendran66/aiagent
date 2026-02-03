package com.surendrantesting.model;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class InputRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String system;
    private String status;
    private String receivedTime;
    private String completedTime;
    private String user;
}

package com.surendrantesting.model;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class OutputRecord {
    @Id
    private Long id;
    private Long recordId;
    private String service;
    @Embedded
    private Sla sla;
    private String owner;
    private String receivedTime;
    private String completedTime;
    private long durationMinutes;
    private boolean breached;
    @Embeddable
    @Data
    public static class Sla {
        private String state;
    }
}

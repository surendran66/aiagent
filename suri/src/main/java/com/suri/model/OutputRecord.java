package com.suri.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
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
    private String receivedTime;
    private String completedTime;
    private long durationMinutes;
    private boolean breached;
    private String owner;

    @Data
    public static class Sla {
        private String state;
    }
}

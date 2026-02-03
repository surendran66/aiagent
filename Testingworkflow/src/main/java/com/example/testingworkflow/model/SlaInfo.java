package com.example.testingworkflow.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class SlaInfo {
    private String state;
}

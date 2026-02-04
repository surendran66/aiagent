package com.example.jsontransformation.model;

import com.azure.spring.data.cosmos.core.mapping.Container;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;

@Container(containerName = "InputRecord")
public class InputRecord {
    @Id
    private String id;
    private String name;
    private String type;
    private String status;
    private String priority;
    private String sla;

    public InputRecord() {}

    @PersistenceConstructor
    public InputRecord(String id, String name, String type, String status, String priority, String sla) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.status = status;
        this.priority = priority;
        this.sla = sla;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getPriority() {
        return priority;
    }
    public void setPriority(String priority) {
        this.priority = priority;
    }
    public String getSla() {
        return sla;
    }
    public void setSla(String sla) {
        this.sla = sla;
    }
}

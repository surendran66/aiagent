package com.example.jsontransformation.model;

import com.azure.spring.data.cosmos.core.mapping.Container;
import org.springframework.data.annotation.Id;

@Container(containerName = "OutputRecord")
public class OutputRecord {
    @Id
    private String id;
    private String name;
    private String type;
    private String status;
    private String priority;
    private Sla sla;

    public OutputRecord() {}

    public OutputRecord(String id, String name, String type, String status, String priority, Sla sla) {
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
    public Sla getSla() {
        return sla;
    }
    public void setSla(Sla sla) {
        this.sla = sla;
    }

    public static class Sla {
        private String state;

        public Sla() {}
        public Sla(String state) {
            this.state = state;
        }
        public String getState() {
            return state;
        }
        public void setState(String state) {
            this.state = state;
        }
    }
}

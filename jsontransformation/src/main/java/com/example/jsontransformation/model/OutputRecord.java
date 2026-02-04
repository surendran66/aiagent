package com.example.jsontransformation.model;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import org.springframework.data.annotation.Id;

@Container(containerName = "outputRecords")
public class OutputRecord {
    @Id
    private String id;

    @PartitionKey
    private String data;

    private Sla sla;

    public OutputRecord() {}

    public OutputRecord(String id, String data, Sla sla) {
        this.id = id;
        this.data = data;
        this.sla = sla;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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

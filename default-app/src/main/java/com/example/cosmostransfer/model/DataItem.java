package com.example.cosmostransfer.model;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import org.springframework.data.annotation.Id;

@Container(containerName = "inputdata")
public class DataItem {

    @Id
    private String id;
    @PartitionKey
    private String partitionKey;
    private String data;

    public DataItem() {}

    public DataItem(String id, String partitionKey, String data) {
        this.id = id;
        this.partitionKey = partitionKey;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPartitionKey() {
        return partitionKey;
    }

    public void setPartitionKey(String partitionKey) {
        this.partitionKey = partitionKey;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

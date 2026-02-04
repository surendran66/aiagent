package com.example.datatransformation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.azure.spring.data.cosmos.repository.config.EnableCosmosRepositories;

@SpringBootApplication
@EnableCosmosRepositories(basePackages = "com.example.datatransformation.repository")
public class DatatransformationApplication {
    public static void main(String[] args) {
        SpringApplication.run(DatatransformationApplication.class, args);
    }
}

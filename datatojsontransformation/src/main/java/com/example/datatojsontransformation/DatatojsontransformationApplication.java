package com.example.datatojsontransformation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.azure.spring.data.cosmos.repository.config.EnableCosmosRepositories;

@SpringBootApplication
@EnableCosmosRepositories(basePackages = "com.example.datatojsontransformation.repository")
public class DatatojsontransformationApplication {
    public static void main(String[] args) {
        SpringApplication.run(DatatojsontransformationApplication.class, args);
    }
}

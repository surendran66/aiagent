package com.optum.datatojsontransformationoptum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.azure.spring.data.cosmos.repository.config.EnableCosmosRepositories;

@SpringBootApplication
@EnableCosmosRepositories
public class DatatojsontransformationoptumApplication {
    public static void main(String[] args) {
        SpringApplication.run(DatatojsontransformationoptumApplication.class, args);
    }
}

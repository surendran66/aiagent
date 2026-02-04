package com.example.jsontransformation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import com.azure.spring.data.cosmos.repository.config.EnableCosmosRepositories;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableCosmosRepositories(basePackages = "com.example.jsontransformation.repository")
public class JsonTransformationApplication {
    public static void main(String[] args) {
        SpringApplication.run(JsonTransformationApplication.class, args);
    }
}

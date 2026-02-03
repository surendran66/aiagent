package com.example.cosmostransfer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CosmostransferApplication {
    public static void main(String[] args) {
        SpringApplication.run(CosmostransferApplication.class, args);
    }

    @GetMapping("/")
    public String home() {
        return "Cosmos Transfer App is up and running!";
    }
}

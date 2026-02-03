package com.example.jsontestingtransform11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Jsontestingtransform11Application {
    public static void main(String[] args) {
        SpringApplication.run(Jsontestingtransform11Application.class, args);
    }

    @GetMapping("/")
    public String home() {
        return "Hello, Spring Boot 3.2.4!";
    }
}

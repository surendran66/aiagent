package com.example.helloworld.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloWorldControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void helloEndpointReturnsHelloWorld() {
        ResponseEntity<String> response = restTemplate.getForEntity("/hello", String.class);
        assertEquals("Hello, World!", response.getBody());
    }
}

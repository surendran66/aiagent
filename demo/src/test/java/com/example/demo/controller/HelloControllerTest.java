package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void hello_returnsHelloWorld() {
        ResponseEntity<Map> response = restTemplate.getForEntity("/api/hello", Map.class);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().containsKey("message"));
        assertEquals("Hello, World!", response.getBody().get("message"));
    }
}

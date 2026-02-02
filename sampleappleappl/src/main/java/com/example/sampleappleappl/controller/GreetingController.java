package com.example.sampleappleappl.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class GreetingController {

    @GetMapping("/")
    public Map<String, String> greet() {
        return Map.of("message", "hi How are you");
    }
}

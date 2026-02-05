package com.example.hellousero.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloUserOController {
    @GetMapping("/{name}")
    public String sayHello(@PathVariable String name) {
        return "hello " + name;
    }
}

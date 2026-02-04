package com.example.hellouser.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloUserController {
    public HelloUserController() {}

    @GetMapping("/{name}")
    public String sayHello(@PathVariable String name) {
        return "hello " + name;
    }
}

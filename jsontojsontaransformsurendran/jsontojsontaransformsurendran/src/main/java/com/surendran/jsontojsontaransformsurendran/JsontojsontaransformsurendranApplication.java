package com.surendran.jsontojsontaransformsurendran;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JsontojsontaransformsurendranApplication {
    public static void main(String[] args) {
        SpringApplication.run(JsontojsontaransformsurendranApplication.class, args);
    }

    @Bean
    CommandLineRunner runner() {
        return args -> {
            System.out.println("hello World !");
        };
    }
}

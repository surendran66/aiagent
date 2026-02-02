package com.example.demo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class HelloWorldApplicationTests {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void helloWorldEndpoint_ReturnsHelloWorld() {
        String response = restTemplate.getForObject("/", String.class);
        assertThat(response).isEqualTo("Hello, World!");
    }
}

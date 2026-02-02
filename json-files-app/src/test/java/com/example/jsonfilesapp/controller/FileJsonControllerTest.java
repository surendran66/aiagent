package com.example.jsonfilesapp.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class FileJsonControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private static final Path sampleDir = Paths.get("sample");

    @BeforeAll
    static void setupSampleFiles() throws Exception {
        Files.createDirectories(sampleDir);
        Files.writeString(sampleDir.resolve("a.txt"), "Hello A", StandardCharsets.UTF_8);
        Files.createDirectories(sampleDir.resolve("sub"));
        Files.writeString(sampleDir.resolve("sub/b.json"), "{\"b\":true}", StandardCharsets.UTF_8);
    }
    @AfterAll
    static void cleanup() throws Exception {
        Files.walk(sampleDir)
                .sorted(Comparator.reverseOrder())
                .forEach(path -> path.toFile().delete());
    }

    @Test
    void shouldReturnJsonMapOfPathsToContent() throws Exception {
        mockMvc.perform(get("/json-files"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.['a.txt']").value("Hello A"))
                .andExpect(jsonPath("$.['sub/b.json']").value("{\"b\":true}"));
    }
}

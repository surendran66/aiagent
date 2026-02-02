package com.example.jsonfilesapp.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;
import java.io.IOException;

@RestController
public class FileJsonController {
    private static final String BASE_DIR = "sample";

    @GetMapping(path = "/json-files", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> getJsonFiles() throws IOException {
        Map<String, String> result = new LinkedHashMap<>();
        Path root = Paths.get(BASE_DIR);
        if (!Files.exists(root) || !Files.isDirectory(root)) {
            return result;
        }
        try (Stream<Path> paths = Files.walk(root)) {
            List<Path> files = paths
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
            for (Path file : files) {
                String relPath = root.relativize(file).toString().replace("\\", "/");
                String content = Files.readString(file);
                result.put(relPath, content);
            }
        }
        return result;
    }
}

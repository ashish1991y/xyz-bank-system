package com.xyzbank.eob.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

@RestController("/")
public class SwaggerDocLoader {
    @GetMapping("/doc")
    public Object api23() throws IOException {
        try {
            Resource resource = new ClassPathResource("swagger.yml");
            List<String> lines = Files.readAllLines(resource.getFile().toPath(), StandardCharsets.UTF_8);
            String yamlContent = String.join("\n", lines);

            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            Object yamlObject = mapper.readValue(yamlContent, Object.class);

            return yamlObject;
        } catch (Exception e) {
            return null;
        }
    }
}

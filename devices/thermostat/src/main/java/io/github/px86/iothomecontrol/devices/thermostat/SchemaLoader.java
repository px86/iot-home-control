package io.github.px86.iothomecontrol.devices.thermostat;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class SchemaLoader {

  private ResourceLoader resourceLoader;
  private ObjectMapper objectMapper;

  @Value("classpath:schema.json")
  private String schemaFile;

  @Autowired
  public SchemaLoader(ResourceLoader resourceLoader, ObjectMapper objectMapper) {
    this.resourceLoader = resourceLoader;
    this.objectMapper = objectMapper;
  }

  public String loadSchema() throws IOException {
    try (InputStream inputStream = resourceLoader.getResource(schemaFile).getInputStream()) {
      String jsonSchema = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
      return objectMapper.writeValueAsString(objectMapper.readTree(jsonSchema));
    }
  }
}

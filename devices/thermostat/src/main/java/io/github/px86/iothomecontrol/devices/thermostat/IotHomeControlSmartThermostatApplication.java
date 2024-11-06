package io.github.px86.iothomecontrol.devices.thermostat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IotHomeControlSmartThermostatApplication {
  public static void main(String[] args) {
    SpringApplication.run(IotHomeControlSmartThermostatApplication.class, args);
  }

  @Bean
  public ObjectMapper objectMapper() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    return mapper;
  }
}

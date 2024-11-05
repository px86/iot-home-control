package io.github.px86.iothomecontrol.devices.thermostat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IotHomeControlSmartThermostatApplication {
  public static void main(String[] args) {
    SpringApplication.run(IotHomeControlSmartThermostatApplication.class, args);
  }
}

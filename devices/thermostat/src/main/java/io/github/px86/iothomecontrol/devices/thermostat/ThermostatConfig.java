package io.github.px86.iothomecontrol.devices.thermostat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThermostatConfig {

  @Bean
  public Thermostat thermostat() {
    Thermostat thermostat = new Thermostat();
    thermostat.setDeviceId("3ce20934-a99b-4ebb-9ffc-d41223f898ce");
    thermostat.setDeviceName("Smart Thermostat");
    thermostat.setCurrentTemperature(22.0);
    thermostat.setTargetTemperature(18.0);
    return thermostat;
  }
}

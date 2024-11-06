package io.github.px86.iothomecontrol.devices.thermostat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class StatePublisher {

  private PublisherService publisherService;
  private final Thermostat thermostat;
  private ObjectMapper objectMapper;

  private static Logger log = LoggerFactory.getLogger(StatePublisher.class);

  @Autowired
  public StatePublisher(
      PublisherService publisherService, Thermostat thermostat, ObjectMapper objectMapper) {
    this.publisherService = publisherService;
    this.thermostat = thermostat;
    this.objectMapper = objectMapper;
  }

  @Scheduled(fixedRate = 5_000)
  public void publishState() {
    try {
      this.publisherService.sendMessage(
          "home/devices/state", objectMapper.writeValueAsString(thermostat));
    } catch (MqttException mqttException) {
      log.error(
          "exception occured while publishing thermostat state: {}", mqttException.getMessage());
      mqttException.printStackTrace();
    } catch (JsonProcessingException jsonProcessingException) {
      log.error(
          "exception occured while JSON serealizing thermostat state: {}",
          jsonProcessingException.getMessage());
      jsonProcessingException.printStackTrace();
    }
  }
}

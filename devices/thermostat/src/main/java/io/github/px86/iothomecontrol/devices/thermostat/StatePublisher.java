package io.github.px86.iothomecontrol.devices.thermostat;

import org.eclipse.paho.mqttv5.common.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class StatePublisher {

  private PublisherService publisherService;

  @Autowired
  public StatePublisher(PublisherService publisherService) {
    this.publisherService = publisherService;
  }

  @Scheduled(fixedRate = 5_000)
  public void publishState() throws MqttException {
    this.publisherService.sendMessage(
        "home/devices/state", "TODO: publish correct state for thermostat!");
  }
}

package io.github.px86.iothomecontrol.devices.thermostat;

import org.eclipse.paho.mqttv5.common.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DeviceSchemaPublisher {

  private PublisherService publisherService;

  @Autowired
  public DeviceSchemaPublisher(PublisherService publisherService) {
    this.publisherService = publisherService;
  }

  @Scheduled(fixedRate = 10_000)
  public void publishDeviceSchema() throws MqttException {
    this.publisherService.sendMessage(
        "home/devices/schema", "TODO: publish correct schema for thermostat!");
  }
}

package io.github.px86.iothomecontrol.devices.thermostat;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DeviceSchemaPublisher {

  private PublisherService publisherService;
  private SchemaLoader schemaLoader;
  private String schema;

  private static Logger log = LoggerFactory.getLogger(DeviceSchemaPublisher.class);

  @Autowired
  public DeviceSchemaPublisher(PublisherService publisherService, SchemaLoader schemaLoader) {
    this.publisherService = publisherService;
    this.schemaLoader = schemaLoader;
  }

  @PostConstruct
  private void loadSchema() throws IOException {
    this.schema = this.schemaLoader.loadSchema();
    log.info("successfully read schema for thermostat: {}", this.schema);
  }

  @Scheduled(fixedRate = 10_000)
  public void publishDeviceSchema() throws MqttException {
    this.publisherService.sendMessage("home/devices/schema", this.schema);
  }
}

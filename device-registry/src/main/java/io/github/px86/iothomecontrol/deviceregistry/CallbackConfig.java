package io.github.px86.iothomecontrol.deviceregistry;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import org.eclipse.paho.mqttv5.client.IMqttToken;
import org.eclipse.paho.mqttv5.client.MqttCallback;
import org.eclipse.paho.mqttv5.client.MqttDisconnectResponse;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.eclipse.paho.mqttv5.common.packet.MqttProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CallbackConfig {

  private final RegistryService registryService;
  private final ObjectMapper objectMapper;

  private static final Logger log = LoggerFactory.getLogger(CallbackConfig.class);

  @Autowired
  public CallbackConfig(RegistryService registryService, ObjectMapper objectMapper) {
    this.registryService = registryService;
    this.objectMapper = objectMapper;
  }

  @Bean
  public MqttCallback mqttCallback() {
    return new MqttCallback() {
      @Override
      public void messageArrived(String topic, MqttMessage message) {
        log.info("message received for topic: {}", topic);
        if (topic.toLowerCase().endsWith("/state")) {
          try {
            String jsonString = new String(message.getPayload(), StandardCharsets.UTF_8);
            JsonNode node = objectMapper.readTree(jsonString);
            String deviceId = node.get("device_id").asText();
            log.info("key: {}, value: {}", deviceId, jsonString);
            registryService.set(deviceId, jsonString);
            log.info("get: {}", registryService.get(deviceId));
          } catch (Exception ex) {
            log.error(
                "exception occured while parsing/registrying device state: {}", ex.getMessage());
            ex.printStackTrace();
          }
        }
      }

      @Override
      public void disconnected(MqttDisconnectResponse response) {
        System.out.println("disconnected: " + response.toString());
      }

      @Override
      public void mqttErrorOccurred(MqttException exception) {
        System.out.println("mqttErrorOccured!");
        exception.printStackTrace();
      }

      @Override
      public void deliveryComplete(IMqttToken token) {}

      @Override
      public void connectComplete(boolean reconnect, String serverURI) {
        System.out.println("connectComplete: serverURI=" + serverURI);
      }

      @Override
      public void authPacketArrived(int reasonCode, MqttProperties properties) {}
    };
  }
}

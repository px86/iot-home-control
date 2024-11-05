package io.github.px86.iothomecontrol.devices.thermostat;

import org.eclipse.paho.mqttv5.client.IMqttToken;
import org.eclipse.paho.mqttv5.client.MqttCallback;
import org.eclipse.paho.mqttv5.client.MqttDisconnectResponse;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.eclipse.paho.mqttv5.common.packet.MqttProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CallbackConfig {

  @Bean
  public MqttCallback mqttCallback() {
    return new MqttCallback() {
      @Override
      public void messageArrived(String topic, MqttMessage message) {
        System.out.println(
            "Received message from topic " + topic + ": " + new String(message.getPayload()));
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

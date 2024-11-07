package io.github.px86.iothomecontrol.deviceregistry;

import org.eclipse.paho.mqttv5.client.IMqttToken;
import org.eclipse.paho.mqttv5.client.MqttAsyncClient;
import org.eclipse.paho.mqttv5.client.MqttConnectionOptions;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublisherService {
  private MqttAsyncClient client;
  private MqttConnectionOptions mqttConnectionOptions;

  @Autowired
  public PublisherService(MqttAsyncClient client, MqttConnectionOptions mqttConnectionOptions) {
    this.client = client;
    this.mqttConnectionOptions = mqttConnectionOptions;
  }

  public void sendMessage(String topic, String payload, int qos) throws MqttException {
    MqttMessage message = new MqttMessage(payload.getBytes());
    message.setQos(qos);
    if (!client.isConnected()) {
      client.connect(mqttConnectionOptions).waitForCompletion();
    }
    IMqttToken token = client.publish(topic, message);
    token.waitForCompletion();
  }

  public void sendMessage(String topic, String payload) throws MqttException {
    sendMessage(topic, payload, 2);
  }
}

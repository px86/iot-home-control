package io.github.px86.iothomecontrol.devices.thermostat;

import jakarta.annotation.PostConstruct;
import org.eclipse.paho.mqttv5.client.MqttAsyncClient;
import org.eclipse.paho.mqttv5.client.MqttCallback;
import org.eclipse.paho.mqttv5.client.MqttConnectionOptions;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Subscriber {

  private MqttAsyncClient mqttClient;
  private MqttCallback mqttCallback;
  private MqttConnectionOptions mqttConnectionOptions;

  @Autowired
  public Subscriber(
      MqttAsyncClient mqttClient,
      MqttCallback mqttCallback,
      MqttConnectionOptions mqttConnectionOptions) {
    this.mqttClient = mqttClient;
    this.mqttCallback = mqttCallback;
    this.mqttConnectionOptions = mqttConnectionOptions;
  }

  @PostConstruct
  void setCallbackAndSubscribe() throws MqttException {
    if (!mqttClient.isConnected()) {
      mqttClient.connect(mqttConnectionOptions).waitForCompletion();
    }
    mqttClient.setCallback(mqttCallback);
    mqttClient.subscribe("home/devices/state", 2);
    mqttClient.subscribe("home/devices/schema", 2);
  }
}

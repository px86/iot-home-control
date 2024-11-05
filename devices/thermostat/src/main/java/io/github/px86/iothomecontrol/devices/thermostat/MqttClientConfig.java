package io.github.px86.iothomecontrol.devices.thermostat;

import org.eclipse.paho.mqttv5.client.IMqttToken;
import org.eclipse.paho.mqttv5.client.MqttAsyncClient;
import org.eclipse.paho.mqttv5.client.MqttConnectionOptions;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqttClientConfig {

  @Value("${mqtt.broker.url}")
  private String brokerUrl;

  @Value("${mqtt.client.id}")
  private String clientId;

  @Bean
  public MqttConnectionOptions mqttConnectionOptions() {
    MqttConnectionOptions connectionOptions = new MqttConnectionOptions();
    connectionOptions.setServerURIs(new String[] {brokerUrl});
    connectionOptions.setCleanStart(true);
    connectionOptions.setAutomaticReconnect(true);
    connectionOptions.setKeepAliveInterval(60);

    return connectionOptions;
  }

  @Bean
  public MqttAsyncClient mqttAsyncClient() throws MqttException {
    MqttAsyncClient client = new MqttAsyncClient(brokerUrl, clientId);
    IMqttToken token = client.connect(mqttConnectionOptions());
    token.waitForCompletion();
    return client;
  }
}

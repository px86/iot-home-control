package io.github.px86.iothomecontrol.devices.thermostat;

import org.eclipse.paho.mqttv5.client.IMqttToken;
import org.eclipse.paho.mqttv5.client.MqttAsyncClient;
import org.eclipse.paho.mqttv5.client.MqttConnectionOptions;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqttClientConfig {

  @Value("${mqtt.broker.url}")
  private String brokerUrl;

  @Value("${mqtt.client.id}")
  private String clientId;

  private static Logger log = LoggerFactory.getLogger(MqttClientConfig.class);

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

    addShutdownHook(client);

    return client;
  }

  private void addShutdownHook(final MqttAsyncClient client) {
    Runtime.getRuntime()
        .addShutdownHook(
            new Thread(
                () -> {
                  if (client.isConnected()) {
                    try {
                      log.info("disconnecting the client...");
                      client.disconnect().waitForCompletion();
                      log.info("client disconnected.");
                    } catch (MqttException exception) {
                      log.error(
                          "exception occured while trying to disconnect the client: {}",
                          exception.getMessage());
                    }
                  }
                }));
  }
}

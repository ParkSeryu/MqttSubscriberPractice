package com.jhpark.mqttsubscriberpractice.config;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.core.MessageProducer;

@Configuration
public class MqttConfig {

    @Value("tcp://localhost:1883")
    private String brokerUrl;

    @Value("subscriber")
    private String clientId;

    @Value("living/+/temperature")
    private String topic;

    @Bean
    public MqttConnectOptions mqttConnectOptions() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(new String[]{brokerUrl});
        options.setCleanSession(true);
        return options;
    }


    @Bean
    public MqttClient mqttClient() {
        try {
            MqttClient mqttClient = new MqttClient(brokerUrl, clientId, new MemoryPersistence());

            mqttClient.connect(mqttConnectOptions());
            mqttClient.subscribe(topic);
            return mqttClient;
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }
}
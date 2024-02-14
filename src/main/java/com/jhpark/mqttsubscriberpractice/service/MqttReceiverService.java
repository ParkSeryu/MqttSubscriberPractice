package com.jhpark.mqttsubscriberpractice.service;

import com.jhpark.mqttsubscriberpractice.entity.TestEntity;
import com.jhpark.mqttsubscriberpractice.repository.MqttMongoRepository;
import jakarta.annotation.PostConstruct;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Component;

@Component
public class MqttReceiverService implements MqttCallback {

    private final MqttClient mqttClient;
    private final MqttMongoRepository mqttMongoRepository;

    public MqttReceiverService(MqttClient mqttClient, MqttMongoRepository mqttMongoRepository) {
        this.mqttClient = mqttClient;
        this.mqttMongoRepository = mqttMongoRepository;
    }

    @PostConstruct
    public void init() {
        mqttClient.setCallback(this);
    }

    @Override
    public void connectionLost(Throwable cause) {
        // 연결이 끊어졌을 때 처리 로직
        System.out.println("연결이 끊어졌을 때 처리 로직");
        System.out.println(cause.toString());
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // 메시지가 도착했을 때 처리 로직
        TestEntity entity = new TestEntity();
        entity.setTopic(topic);
        entity.setMessage(message.toString());
        mqttMongoRepository.save(entity);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        // 메시지 전송이 완료되었을 때 처리 로직
        System.out.println("메시지 전송이 완료되었을 때 처리 로직");
    }
}
package com.jhpark.mqttsubscriberpractice.service;

import com.jhpark.mqttsubscriberpractice.entity.TestEntity;
import com.jhpark.mqttsubscriberpractice.repository.MqttMongoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MqttReceiverServiceTest {

    private String Id;

    @Autowired
    MqttReceiverService mqttReceiverService;

    @Autowired
    MqttMongoRepository mqttMongoRepository;

    @AfterEach
    public void tearDown() {
        mqttMongoRepository.deleteById(Id);
    }

    @Test
    void messageArrivedTest() {
        //given
        TestEntity test = new TestEntity();
        test.setTopic("ccc");
        test.setMessage("eee");

        //when
        TestEntity t = mqttMongoRepository.save(test);

        TestEntity valid = mqttMongoRepository.findById(t.getId()).orElseThrow(IllegalArgumentException::new);

        Id = valid.getId();
        //then
        assertThat(t.getMessage()).isEqualTo(valid.getMessage());
    }
}
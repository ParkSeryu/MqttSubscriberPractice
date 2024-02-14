package com.jhpark.mqttsubscriberpractice.repository;

import com.jhpark.mqttsubscriberpractice.entity.TestEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MqttMongoRepository extends MongoRepository<TestEntity, String> {


}

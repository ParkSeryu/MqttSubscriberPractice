package com.jhpark.mqttsubscriberpractice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "test")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestEntity {

    @Id
    @Generated
    private String id;
    private String topic;
    private String message;



}

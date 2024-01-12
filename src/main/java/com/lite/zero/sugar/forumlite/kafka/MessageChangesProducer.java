package com.lite.zero.sugar.forumlite.kafka;

import com.lite.zero.sugar.forumlite.entity.MyMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageChangesProducer {

    private KafkaTemplate<String, MyMessage> kafkaTemplate;

    @Autowired
    public MessageChangesProducer(KafkaTemplate<String, MyMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(MyMessage data) {

        Message<MyMessage> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, "Zlatan")
                .build();

        kafkaTemplate.send(message);
    }
}

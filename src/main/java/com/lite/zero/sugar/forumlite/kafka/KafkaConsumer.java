package com.lite.zero.sugar.forumlite.kafka;

import com.lite.zero.sugar.forumlite.entity.MyMessage;
import com.lite.zero.sugar.forumlite.repository.MyMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private MyMessageRepository myMessageRepository;

    @Autowired
    public KafkaConsumer(MyMessageRepository myMessageRepository) {
        this.myMessageRepository = myMessageRepository;
    }

    @KafkaListener(topics = "Zlatan", groupId = "myGroup")
    //public void consume(MyMessage message){ закоменчен код для JSON сериалайзера
    public void consume(String message){
        //System.out.println("Consumed message: " + message.getMessage());
        System.out.println("Consumed message: " + message);
        MyMessage msg = new MyMessage();
        msg.setMessage(message);
        myMessageRepository.save(msg);
    }
}

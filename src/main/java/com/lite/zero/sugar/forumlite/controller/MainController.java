package com.lite.zero.sugar.forumlite.controller;

import com.lite.zero.sugar.forumlite.entity.MyMessage;
import com.lite.zero.sugar.forumlite.kafka.KafkaStringProducer;
import com.lite.zero.sugar.forumlite.kafka.MessageChangesProducer;
import com.lite.zero.sugar.forumlite.repository.MyMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MainController {


    private MessageChangesProducer messageChangesProducer;
    private KafkaStringProducer kafkaStringProducer;
    private MyMessageRepository myMessageRepository;

    @Autowired
    public MainController(MessageChangesProducer messageChangesProducer, KafkaStringProducer kafkaStringProducer, MyMessageRepository myMessageRepository) {
        this.messageChangesProducer = messageChangesProducer;
        this.kafkaStringProducer = kafkaStringProducer;
        this.myMessageRepository = myMessageRepository;
    }

    @PostMapping("/test")
    //public ResponseEntity<String> publish(@RequestBody MyMessage message){
    public ResponseEntity<String> publish(@RequestBody String message){
        kafkaStringProducer.sendMessage(message);

        return ResponseEntity.ok("Message was published");
    }


    @GetMapping("/getMessage/{name}")
    public ResponseEntity<String> showMessage(@PathVariable("name") String name){

        MyMessage msg = myMessageRepository.findByMessage(name);
        return new ResponseEntity<>(msg.getId().toString(), HttpStatus.OK);
    }
}

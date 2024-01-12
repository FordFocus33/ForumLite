package com.lite.zero.sugar.forumlite.controller;

import com.lite.zero.sugar.forumlite.entity.MyMessage;
import com.lite.zero.sugar.forumlite.kafka.KafkaStringProducer;
import com.lite.zero.sugar.forumlite.repository.MyMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api")
public class FrontController {

    private KafkaStringProducer kafkaStringProducer;
    private MyMessageRepository myMessageRepository;

    @Autowired
    public FrontController(KafkaStringProducer kafkaStringProducer, MyMessageRepository myMessageRepository) {
        this.kafkaStringProducer = kafkaStringProducer;
        this.myMessageRepository = myMessageRepository;
    }

    @GetMapping("/forum")
    public String index(Model model){

        List<MyMessage> messages = myMessageRepository.findAll();
        model.addAttribute("messages", messages);

        return "index";
    }

    @PostMapping("/forum/send")
    public String sendMessage(@ModelAttribute("message") String message){
        MyMessage msg = new MyMessage();
        msg.setMessage(message);

        kafkaStringProducer.sendMessage(msg.getMessage());
//        myMessageRepository.save(msg);

        return "redirect:/api/forum";
    }
}

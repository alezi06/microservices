package com.example.helloservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public HelloController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("/hello")
    public String showHello() {
        kafkaTemplate.send("hello", "1");
        return "Добрый день";
    }
}

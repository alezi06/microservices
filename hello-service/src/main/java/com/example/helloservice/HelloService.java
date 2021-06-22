package com.example.helloservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
    private final HelloRepository helloRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private Long counter;

    @Autowired
    public HelloService(KafkaTemplate<String, String> kafkaTemplate, HelloRepository helloRepository) {
        this.helloRepository = helloRepository;
        this.kafkaTemplate = kafkaTemplate;
        counter = helloRepository.count();
    }

    public void sendMessage() {
        counter++;
        helloRepository.save(new Hello(counter.toString()));
        kafkaTemplate.send("hello", counter.toString());
    }
}

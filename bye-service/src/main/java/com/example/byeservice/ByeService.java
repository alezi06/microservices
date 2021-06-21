package com.example.byeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ByeService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final HelloRepository helloRepository;

    @Autowired
    public ByeService(KafkaTemplate<String, String> kafkaTemplate, HelloRepository helloRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.helloRepository = helloRepository;
    }

    public void send(String msg) {
        kafkaTemplate.send("bye", msg);
    }

    @KafkaListener(topics = "hello")
    public void consume(String msg) {
        helloRepository.save(new Hello(msg));
    }

    public long getHelloCount() {
        return helloRepository.count();
    }
}

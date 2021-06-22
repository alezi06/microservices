package com.example.byeservice;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class ByeService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final Consumer<String, String> consumer;
    private String counter;

    @Autowired
    public ByeService(KafkaTemplate<String, String> kafkaTemplate, Consumer<String, String> consumer) {
        this.kafkaTemplate = kafkaTemplate;
        this.consumer = consumer;
    }

    public void send(String msg) {
        kafkaTemplate.send("bye", msg);
    }

    @KafkaListener(topics = "hello")
    public void consume(String msg) {
        counter = msg;
    }

    public String getCounter() {
        if (counter == null) {
            counter = lastCounterValue();
        }
        return counter;
    }

    public String lastCounterValue() {
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
        return String.valueOf(records.count());
    }
}

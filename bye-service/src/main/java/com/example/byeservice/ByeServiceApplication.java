package com.example.byeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ByeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ByeServiceApplication.class, args);
    }

}

package com.example.testrabbitmq;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class TestRabbitMqApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestRabbitMqApplication.class, args);
    }

}

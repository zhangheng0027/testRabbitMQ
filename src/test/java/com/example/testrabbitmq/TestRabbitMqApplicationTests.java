package com.example.testrabbitmq;

import jakarta.annotation.Resource;
import lombok.Getter;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestRabbitMqApplicationTests {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Test
    void test01() {
        send("{}");
        send("{}");
        send("{}");
        send("{}");
        send("{}");
    }

    @Test
    void test02() {
        send("{}");
        // age is int, this msg is error
        send("""
                {
                    "age":"test"
                }
                """);
        send("{}");
        send("{}");
        send("{}");
    }

    public void send(String s) {
        rabbitTemplate.convertAndSend("testExchange", "test", s, message -> {
            message.getMessageProperties().setContentType("application/json");
            return message;
        });
    }

}

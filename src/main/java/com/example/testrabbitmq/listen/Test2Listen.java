package com.example.testrabbitmq.listen;

import com.rabbitmq.client.Channel;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;


@Log4j2
@Component
public class Test2Listen {

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "test2Queue"),
                    exchange = @Exchange(value = "testExchange"),
                    key = "test2"
            ),
            concurrency = "1",
            exclusive = true,
            ackMode = "MANUAL",
            containerFactory = "batchListenerContainerFactory"
    )
    @SneakyThrows
    public void test(@Payload List<Message<EquipmentStatusChangeEvent>> event, Channel channel) {
    }

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "test2Queue"),
                    exchange = @Exchange(value = "testExchange"),
                    key = "test2"
            ),
            concurrency = "1",
            exclusive = true,
            ackMode = "MANUAL",
            containerFactory = "batchListenerContainerFactory"
    )
    @SneakyThrows
    public void test2(@Payload List<Message<EquipmentStatusChangeEvent>> event, Channel channel) {
    }



    @Data
    public static class EquipmentStatusChangeEvent {
        String data;
        Integer age;
    }


}

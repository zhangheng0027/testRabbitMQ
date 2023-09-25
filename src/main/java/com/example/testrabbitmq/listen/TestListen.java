package com.example.testrabbitmq.listen;

import com.rabbitmq.client.Channel;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Log4j2
@Component
public class TestListen {


    AtomicInteger ai = new AtomicInteger(0);

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "testQueue"),
                    exchange = @Exchange(value = "testExchange"),
                    key = "test"
            ),
            concurrency = "1",
            exclusive = true,
//            ackMode = "MANUAL",
            containerFactory = "batchListenerContainerFactory"
    )
    public void test(@Payload List<EquipmentStatusChangeEvent> event) {
        if (null == event)
            return;

        for (EquipmentStatusChangeEvent equipmentStatusChangeEvent : event) {
            log.info(" {} ==== test {} ====================", ai.getAndIncrement(), equipmentStatusChangeEvent);
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {
        }
    }

    @Data
    public static class EquipmentStatusChangeEvent {
        String data;
        Integer age;
    }

}

package com.example.testrabbitmq.conf;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MQConf {

    @Resource
    CachingConnectionFactory connectionFactory;

    @Bean(name = "batchListenerContainerFactory")
    public RabbitListenerContainerFactory<SimpleMessageListenerContainer> rabbitConfig1() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPrefetchCount(1);
        factory.setConcurrentConsumers(1);
        factory.setBatchListener(true);
        factory.setBatchSize(2);
        factory.setConsumerBatchEnabled(true);



        Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter(new ObjectMapper());
//        jackson2JsonMessageConverter.setJavaTypeMapper(new RabbitClassMapper());

        factory.setMessageConverter(jackson2JsonMessageConverter);

        return factory;
    }


}

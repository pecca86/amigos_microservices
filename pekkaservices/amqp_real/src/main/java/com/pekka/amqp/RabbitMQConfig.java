package com.pekka.amqp;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ is running on a docker container
 */

@Configuration
@AllArgsConstructor
public class RabbitMQConfig {

    // Connection factory (from Spring framework)
    private final ConnectionFactory connectionFactory;


    // For publishing messages to the queue
    // AmqpTemplate is an interface
    @Bean
    public AmqpTemplate amqpTemplate() {
        // Rabbit takes in a connection factory
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);

        // Allows us to send messages (objects) in JSON format
        rabbitTemplate.setMessageConverter(jacksonConverter());
        return rabbitTemplate;
    }

    // For instances listening on incoming messages (CLIENTS)
    @Bean
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jacksonConverter());
        return factory;
    }

    @Bean
    public MessageConverter jacksonConverter() {
        // Create a new message converter that converts objects into JSON
        // the Jackson2JsonMessageConverter takes in an object mapper
        MessageConverter messageConverter = new Jackson2JsonMessageConverter();
        return messageConverter;
    }


}

package com.cfh.examen3.config;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author Tyler Durden christian.fuentesh@elektra.com.mx
 * @proyect examen-e3
 */
@Configuration
public class RabbitConfig {
    private static Logger log = (Logger) LoggerFactory.getLogger(RabbitConfig.class.getName());

    public static String rabbitQueueName;
    public static String rabbitRouting;
    public static String rabbitExchange;

    public RabbitConfig(
            @Value("${rabbitmq.queue.name}") String rabbitQueueName,
            @Value("${rabbitmq.queue.routing}") String rabbitRouting,
            @Value("${rabbitmq.queue.exchange}") String rabbitExchange) {
        this.rabbitQueueName = rabbitQueueName;
        this.rabbitRouting = rabbitRouting;
        this.rabbitExchange = rabbitExchange;
    }

    @Bean
    public Queue queue() {
        log.info("Iniciando Queue "+RabbitConfig.rabbitQueueName);
        return new Queue(RabbitConfig.rabbitQueueName, true);
    }

    @Bean
    public DirectExchange exchange() {
        log.info("Iniciando Exchange "+RabbitConfig.rabbitExchange);
        return new DirectExchange(RabbitConfig.rabbitExchange, true, false);
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        log.info("Iniciando binging con routing "+RabbitConfig.rabbitRouting);
        return BindingBuilder.bind(queue).to(exchange).with(RabbitConfig.rabbitRouting);
    }
    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         Jackson2JsonMessageConverter jackson2JsonMessageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter);
        return rabbitTemplate;
    }
}

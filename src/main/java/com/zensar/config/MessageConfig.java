package com.zensar.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.Jackson2XmlMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {


    public static final String JSON_QUEUE = "json_order_queue";
    public static final String JSON_EXCHANGE = "json_exchange";
    public static final String JSON_ROUTING_KEY = "json_routingKey";




    public static final String XML_QUEUE = "xml_order_queue";
    public static final String XML_EXCHANGE = "xml_exchange";
    public static final String XML_ROUTING_KEY = "xml_routingKey";

    
    @Bean
    public Queue queueXml() {
        return new Queue(XML_QUEUE);
    }
    
    @Bean
    public Queue queueJson() {
        return new Queue(JSON_QUEUE);
    }
    
    @Bean
    public TopicExchange exchangeForXML() {
        return new TopicExchange(XML_EXCHANGE);
    }


    @Bean
    public Binding bindingXml(@Qualifier(value = "queueXml") Queue queue,@Qualifier("exchangeForXML") TopicExchange exchangeForXML) {
        return BindingBuilder.bind(queue).to(exchangeForXML).with(XML_ROUTING_KEY);
    }
    
    @Bean
    public MessageConverter converterXml() {
        return new Jackson2XmlMessageConverter();
    }
    
    @Bean
    public Jackson2XmlMessageConverter xmlMessageConverter() {
        return new Jackson2XmlMessageConverter();
    }
    
    @Bean
    public AmqpTemplate templateForXML(ConnectionFactory connectionFactory) {
    	
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converterXml());
        return rabbitTemplate;
    }
    
    @Bean
    public TopicExchange exchangeForJSON() {
        return new TopicExchange(JSON_EXCHANGE);
    }
    
    @Bean
    public Binding binding(@Qualifier(value = "queueJson")   Queue queue,@Qualifier("exchangeForJSON") TopicExchange exchangeForJSON) {
        return BindingBuilder.bind(queue).to(exchangeForJSON).with(JSON_ROUTING_KEY);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }
    
  
    @Bean
    public AmqpTemplate templateForJson(ConnectionFactory connectionFactory) {
    	
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
    
    
    

}

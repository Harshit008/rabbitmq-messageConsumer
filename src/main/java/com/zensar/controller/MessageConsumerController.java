package com.zensar.controller;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zensar.beans.JsonOrderBean;
import com.zensar.config.MessageConfig;

@RestController
@Component
public class MessageConsumerController {
	@GetMapping("/consumeMessage")
	public String testApi() {
		return "Welcome to MacysOrder-message-producer app!";
	}
	
	@RabbitListener(queues = MessageConfig.JSON_QUEUE, messageConverter = "converter")
    public void consumeJsonMessageFromQueue(JsonOrderBean order) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(order);
        System.out.println("Message recieved from queue : \n" + jsonStr);
    }
	
	@RabbitListener(bindings = @QueueBinding(
	        value = @Queue(value = "xml_order_queue"),
	        exchange = @Exchange(value = "xml_exchange"),
	        key = "xml_routingKey"), messageConverter = "converterXml")
    public void consumeXmlMessageFromQueue(JsonOrderBean order) {
		JSONObject json = new JSONObject(order);
		String xml = XML.toString(json);
        System.out.println("Message recieved from queue : \n" + xml);
    }
}

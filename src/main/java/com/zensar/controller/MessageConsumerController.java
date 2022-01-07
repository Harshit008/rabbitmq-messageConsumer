package com.zensar.controller;

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
	@GetMapping("/testApi")
	public String testApi() {
		return "Welcome to MacysOrder-message-producer app!";
	}
	
	@RabbitListener(queues = MessageConfig.QUEUE)
    public void consumeMessageFromQueue(JsonOrderBean order) throws JsonProcessingException {
//		ObjectMapper mapper = new ObjectMapper();
//	      String jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(order);
        System.out.println("Message recieved from queue : " + order);
    }
}

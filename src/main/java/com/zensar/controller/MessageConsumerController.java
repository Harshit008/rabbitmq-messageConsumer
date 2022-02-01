package com.zensar.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.beans.JsonOrderBean;
import com.zensar.beans.XmlFulfilmentOrderBean;
import com.zensar.services.OrderService;

@RequestMapping("/consumer")
@RestController
@Component
public class MessageConsumerController {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MessageConsumerController.class);

	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/testApi")
	public String testApi() {
		return "Welcome to MacysOrder-message-producer app!";
	}
	
	@GetMapping("/consumeJsonMessage")
	public ResponseEntity<List<JsonOrderBean>> consumeJsonMessageFromQueue(){
		
		List<JsonOrderBean> jsonMessages = orderService.getJsonMessages();
		return new ResponseEntity<List<JsonOrderBean>>(jsonMessages, HttpStatus.OK);
		
	}
	
	@GetMapping("/consumeXmlMessage")
	public ResponseEntity<List<XmlFulfilmentOrderBean>> consumeXmlMessageFromQueue(){
		
		List<XmlFulfilmentOrderBean> xmlMessages = orderService.getXmlMessages();
		return new ResponseEntity<List<XmlFulfilmentOrderBean>>(xmlMessages, HttpStatus.OK);
		
	}
	
//	@RabbitListener(queues = MessageConfig.JSON_QUEUE, messageConverter = "converter")
//    public void consumeJsonMessageFromQueue(JsonOrderBean order) throws JsonProcessingException {
//		ObjectMapper mapper = new ObjectMapper();
//		String jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(order);
//        logger.info("Message recieved from queue : \n" + jsonStr);
//        orderService.setAndSaveJsonOrderDomain(order);
//        
//    }
//	
//	@RabbitListener(bindings = @QueueBinding(
//	        value = @Queue(value = "xml_order_queue"),
//	        exchange = @Exchange(value = "xml_exchange"),
//	        key = "xml_routingKey"), messageConverter = "converterXml")
//    public void consumeXmlMessageFromQueue(JsonOrderBean order) {
//		JSONObject json = new JSONObject(order);
//		String xml = XML.toString(json);
//		logger.info("Message recieved from queue : \n" + xml);
//    }
}

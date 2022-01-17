package com.zensar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.zensar.beans.JsonOrderBean;
import com.zensar.beans.XmlFulfilmentOrderBean;
import com.zensar.config.MessageConfig;
import com.zensar.domain.JsonOrderDomain;
import com.zensar.domain.XmlFulfilmentOrderDomain;
import com.zensar.repo.JsonOrderDomainRepo;
import com.zensar.repo.XmlFulfilmentOrderRepo;
import com.zensar.services.OrderServiceImpl;
import com.zensar.services.OrderSetterHelper;

@SpringBootTest
class MacysOrderMessageConsumerServiceApplicationTests {
	@Mock
	private JsonOrderDomainRepo jsonRepo;
	
	@Mock
	private XmlFulfilmentOrderRepo xmlRepo;
	
	
	@Qualifier(value = "rabbitAdminForJson")
	@Mock
	private RabbitAdmin rabbitAdminForJson;
	
	@Qualifier(value = "rabbitAdminForXml")
	@Mock
	private RabbitAdmin rabbitAdminForXml;
	
	@Qualifier(value = "templateForJson")
	@Mock
	AmqpTemplate templateforJson;
	
	@Qualifier(value = "templateForXML")
	@Mock
	AmqpTemplate templateforXml;
	
	@Mock
	private OrderSetterHelper setter;
	
	private OrderServiceImpl service;
	
	@BeforeEach
	void setUp() {
		this.service= new OrderServiceImpl(jsonRepo, xmlRepo, rabbitAdminForJson, rabbitAdminForXml, templateforJson, templateforXml, setter);
	}
	
	@Test
	void testForGetJsonMessages() {
		List<JsonOrderBean> jsonMessages = service.getJsonMessages();
		for(JsonOrderBean json: jsonMessages) {
			JsonOrderDomain setJsonOrderDomain = setter.setJsonOrderDomain(json);
			JsonOrderDomain saveAndFlush = jsonRepo.saveAndFlush(setJsonOrderDomain);
			assertEquals(saveAndFlush, setJsonOrderDomain);
			
		}
	}
	
	@Test
	void testForGetXmlMessages() {
		List<XmlFulfilmentOrderBean> xmlMessages = service.getXmlMessages();
		for(XmlFulfilmentOrderBean xml: xmlMessages) {
			XmlFulfilmentOrderDomain setXmlOrderDomain = setter.setXmlOrderDomain(xml);
			XmlFulfilmentOrderDomain saveAndFlush = xmlRepo.saveAndFlush(setXmlOrderDomain);
			assertEquals(saveAndFlush, setXmlOrderDomain);
			
		}
	}
	
}

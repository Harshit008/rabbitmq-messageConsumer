package com.zensar.services;


import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.zensar.beans.JsonOrderBean;
import com.zensar.beans.XmlFulfilmentOrderBean;
import com.zensar.config.MessageConfig;
import com.zensar.controller.MessageConsumerController;
import com.zensar.domain.JsonOrderDomain;
import com.zensar.domain.XmlFulfilmentOrderDomain;
import com.zensar.repo.JsonOrderDomainRepo;
import com.zensar.repo.XmlFulfilmentOrderRepo;
@Service
public class OrderServiceImpl implements OrderService {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Autowired
	private JsonOrderDomainRepo jsonRepo;
	
	@Autowired
	private XmlFulfilmentOrderRepo xmlRepo;
	
	
	@Qualifier(value = "rabbitAdminForJson")
	@Autowired
	private RabbitAdmin rabbitAdminForJson;
	
	@Qualifier(value = "rabbitAdminForXml")
	@Autowired
	private RabbitAdmin rabbitAdminForXml;
	
	@Qualifier(value = "templateForJson")
	@Autowired
	AmqpTemplate templateforJson;
	
	@Qualifier(value = "templateForXML")
	@Autowired
	AmqpTemplate templateforXml;
	
	@Autowired
	private OrderSetterHelper setter;
	
	

	@Override
	public List<JsonOrderBean> getJsonMessages() {
		List<JsonOrderBean> jsonOrderBeanList= new ArrayList<JsonOrderBean>();
		Properties properties=rabbitAdminForJson.getQueueProperties(MessageConfig.JSON_QUEUE);
		logger.info("JSON Queue properties are"+properties.toString());
		int mesCount = (Integer)(properties!=null? properties.get(rabbitAdminForJson.QUEUE_MESSAGE_COUNT):0);
		for(int i=0;i<mesCount;i++) {
			JsonOrderBean jsonOrder = (JsonOrderBean) templateforJson.receiveAndConvert(MessageConfig.JSON_QUEUE); 
			logger.info("JsonOrder bean received from the queue: "+jsonOrder);
			ObjectMapper mapper = new ObjectMapper();
			String jsonStr=null;
			try {
				jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonOrder);
				logger.info("JSON domain received :"+jsonStr);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jsonOrderBeanList.add(jsonOrder);
			JsonOrderDomain jsonOrderDomain = setter.setJsonOrderDomain(jsonOrder);
			JsonOrderDomain res = jsonRepo.saveAndFlush(jsonOrderDomain);
			if(res!=null)
				logger.info("Json order saved to db!");
			else
				logger.info("Json order not saved to db!");
			
		}
		return jsonOrderBeanList;
	}

	@Override
	public List<XmlFulfilmentOrderBean> getXmlMessages() {

		List<XmlFulfilmentOrderBean> xmlOrderBeanList= new ArrayList<XmlFulfilmentOrderBean>();
		Properties properties=rabbitAdminForJson.getQueueProperties(MessageConfig.XML_QUEUE);
		logger.info("XML Queue properties are"+properties.toString());
		int mesCount = (Integer)(properties!=null? properties.get(rabbitAdminForXml.QUEUE_MESSAGE_COUNT):0);
		for(int i=0;i<mesCount;i++) {
			XmlFulfilmentOrderBean xmlOrder = (XmlFulfilmentOrderBean) templateforXml.receiveAndConvert(MessageConfig.XML_QUEUE); 
			JAXBContext jaxbContext;
			try {
				jaxbContext = JAXBContext.newInstance(XmlFulfilmentOrderBean.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		        StringWriter sw = new StringWriter();
		        jaxbMarshaller.marshal(xmlOrder, sw);
		        String xmlString = sw.toString();
		        logger.info("Xml message received: "+xmlString);
			} catch (JAXBException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			logger.info("XmlOrder bean received from the queue: "+xmlOrder);
			xmlOrderBeanList.add(xmlOrder);
			XmlFulfilmentOrderDomain xmlOrderDomain = setter.setXmlOrderDomain(xmlOrder);
			XmlFulfilmentOrderDomain res = xmlRepo.saveAndFlush(xmlOrderDomain);
			
			if(res!=null)
				logger.info("Xml order saved to db!");
			else
				logger.info("Xml order not saved to db!");
			}
		
		return xmlOrderBeanList;
	
	}

}

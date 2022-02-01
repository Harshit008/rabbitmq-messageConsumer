package com.zensar.services;


import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zensar.beans.JsonOrderBean;
import com.zensar.beans.XmlFulfilmentOrderBean;
import com.zensar.config.MessageConfig;
import com.zensar.domain.JsonOrderDomain;
import com.zensar.domain.XmlFulfilmentOrderDomain;
import com.zensar.exceptions.MacysRunTimeException;
import com.zensar.repo.JsonOrderDomainRepo;
import com.zensar.repo.XmlFulfilmentOrderRepo;
@Service
public class OrderServiceImpl implements OrderService {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	StringBuffer cause= new StringBuffer();
	
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
	
	

	@SuppressWarnings("static-access")
	@Override
	public List<JsonOrderBean> getJsonMessages() {
		List<JsonOrderBean> jsonOrderBeanList= new ArrayList<JsonOrderBean>();
		Properties properties=rabbitAdminForJson.getQueueProperties(MessageConfig.JSON_QUEUE);
		//logger.info("JSON Queue properties are"+properties.toString());
		int mesCount = (Integer)(properties!=null? properties.get(rabbitAdminForJson.QUEUE_MESSAGE_COUNT):0);
		try {
			
			for(int i=0;i<mesCount;i++) {
				JsonOrderBean jsonOrder = (JsonOrderBean) templateforJson.receiveAndConvert(MessageConfig.JSON_QUEUE); 
				logger.info("JsonOrder bean received from the queue: "+jsonOrder);
				ObjectMapper mapper = new ObjectMapper();
				String jsonStr=null;
				try {
					jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonOrder);
					logger.info("JSON domain received :"+jsonStr);
				} catch (JsonProcessingException e) {
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
		}catch (MacysRunTimeException e) {

			logger.error("--------------------EXCEPTIONAL EVENT LOG BEGINs-----------------------------------");
			logger.error("ERROR MESSAGE AS--" + e.getMessage() + " ERROR CAUSE :" + e.getCause());
			logger.error("Exception caught in getJsonMessages Method--");
			logger.error("--------------------EXCEPTIONAL EVENT LOG ENDS-----------------------------------");
			cause.append(", ").append(e.getMessage());

		} catch (Exception e) {

			logger.error("--------------------EXCEPTIONAL EVENT LOG BEGINs-----------------------------------");
			logger.error("ERROR MESSAGE AS--" + e.getMessage() + " ERROR CAUSE :" + e.getCause());
			logger.error("Exception caught in getJsonMessages Method--");
			logger.error("--------------------EXCEPTIONAL EVENT LOG ENDS-----------------------------------");
			cause.append(", ").append(e.getMessage());
			throw new MacysRunTimeException(e.getMessage());
		}
		
		return jsonOrderBeanList;
	}

	@SuppressWarnings("static-access")
	@Override
	public List<XmlFulfilmentOrderBean> getXmlMessages() {

		List<XmlFulfilmentOrderBean> xmlOrderBeanList= new ArrayList<XmlFulfilmentOrderBean>();
		Properties properties=rabbitAdminForJson.getQueueProperties(MessageConfig.XML_QUEUE);
		//logger.info("XML Queue properties are"+properties.toString());
		int mesCount = (Integer)(properties!=null? properties.get(rabbitAdminForXml.QUEUE_MESSAGE_COUNT):0);
		try {
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
			
		}catch (MacysRunTimeException e) {

			logger.error("--------------------EXCEPTIONAL EVENT LOG BEGINs-----------------------------------");
			logger.error("ERROR MESSAGE AS--" + e.getMessage() + " ERROR CAUSE :" + e.getCause());
			logger.error("Exception caught in getXmlMessages Method--");
			logger.error("--------------------EXCEPTIONAL EVENT LOG ENDS-----------------------------------");
			cause.append(", ").append(e.getMessage());

		} catch (Exception e) {

			logger.error("--------------------EXCEPTIONAL EVENT LOG BEGINs-----------------------------------");
			logger.error("ERROR MESSAGE AS--" + e.getMessage() + " ERROR CAUSE :" + e.getCause());
			logger.error("Exception caught in getXmlMessages Method--");
			logger.error("--------------------EXCEPTIONAL EVENT LOG ENDS-----------------------------------");
			cause.append(", ").append(e.getMessage());
			throw new MacysRunTimeException(e.getMessage());
		}
		
		return xmlOrderBeanList;
	
	}

	public OrderServiceImpl(JsonOrderDomainRepo jsonRepo, XmlFulfilmentOrderRepo xmlRepo,
			RabbitAdmin rabbitAdminForJson, RabbitAdmin rabbitAdminForXml, AmqpTemplate templateforJson,
			AmqpTemplate templateforXml, OrderSetterHelper setter) {
		super();
		this.jsonRepo = jsonRepo;
		this.xmlRepo = xmlRepo;
		this.rabbitAdminForJson = rabbitAdminForJson;
		this.rabbitAdminForXml = rabbitAdminForXml;
		this.templateforJson = templateforJson;
		this.templateforXml = templateforXml;
		this.setter = setter;
	}

	public OrderServiceImpl() {
		super();
	}

}

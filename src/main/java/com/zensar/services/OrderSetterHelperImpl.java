package com.zensar.services;

import java.sql.Timestamp;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zensar.beans.Address;
import com.zensar.beans.BillingAddress;
import com.zensar.beans.Contact;
import com.zensar.beans.JsonOrderBean;
import com.zensar.beans.Name;
import com.zensar.beans.OrderTotals;
import com.zensar.beans.Source;
import com.zensar.beans.XmlFulfilmentOrderBean;
import com.zensar.domain.AddressDomain;
import com.zensar.domain.BillingAddressDomain;
import com.zensar.domain.ContactDomain;
import com.zensar.domain.JsonOrderDomain;
import com.zensar.domain.NameDomain;
import com.zensar.domain.OrderTotalsDomain;
import com.zensar.domain.SourceDomain;
import com.zensar.domain.XmlFulfilmentOrderDomain;
import com.zensar.repo.AddressRepo;
import com.zensar.repo.BillingAddressRepo;
import com.zensar.repo.ContactRepo;
import com.zensar.repo.NameRepo;
import com.zensar.repo.OrderTotalRepo;
import com.zensar.repo.SourceRepo;
@Service
public class OrderSetterHelperImpl implements OrderSetterHelper {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderSetterHelperImpl.class);
	
	@Autowired
	private AddressRepo addressRepo;
	
	@Autowired
	private BillingAddressRepo billingRepo;
	
	@Autowired
	private ContactRepo contactRepo;
	
	@Autowired
	private NameRepo nameRepo;
	
	@Autowired
	private OrderTotalRepo orderTotalRepo;
	
	@Autowired
	private SourceRepo sourceRepo;
	
	
	@Override
	public JsonOrderDomain setJsonOrderDomain(JsonOrderBean order) {
		JsonOrderDomain domain = new JsonOrderDomain();
		domain.setCommand(order.getCommand());
		domain.setImagePathname(order.getImagePathname());
		domain.setItemDescription(order.getItemDescription());
		domain.setItemHeight(order.getItemHeight());
		domain.setItemLength(order.getItemLength());
		domain.setItemName(order.getItemName());
		domain.setItemWeight(order.getItemWeight());
		domain.setItemWidth(order.getItemWidth());
		domain.setMessageName(order.getMessageName());
		domain.setPickType(order.getPickType());
		domain.setRfidTagged(order.getRfidTagged());
		domain.setStorageAttribute(order.getStorageAttribute());
		domain.setUpcList(order.getUpcList());
		domain.setCreatedDate(new Timestamp(new Date().getTime()).toString());
		return domain;
	}

	@Override
	public XmlFulfilmentOrderDomain setXmlOrderDomain(XmlFulfilmentOrderBean xmlOrder) {
		XmlFulfilmentOrderDomain xmlDomain = new XmlFulfilmentOrderDomain();
		SourceDomain sourceDomain = new SourceDomain();
		OrderTotalsDomain orderToltalsDomain = new OrderTotalsDomain();
		BillingAddressDomain billingDomain = new BillingAddressDomain();
		ContactDomain contactDomain = new ContactDomain();
		AddressDomain addressDomain = new AddressDomain();
		NameDomain nameDomain = new NameDomain();
		BillingAddress billingAddress = xmlOrder.getBillingAddress();
		Contact contact = billingAddress.getContact();
		Name name = contact.getName();
		Address address = contact.getAddress();
		addressDomain.setCity(address.getCity());
		addressDomain.setCountryCode(address.getCountryCode());
		addressDomain.setLine1(address.getLine1());
		addressDomain.setLine2(address.getLine2());
		addressDomain.setLine3(address.getLine3());
		addressDomain.setSeperatorAddress0(address.getSeperatorAddress0());
		addressDomain.setState(address.getState());
		addressDomain.setZipCode(address.getZipCode());
		contactDomain.setAddress(addressDomain);
		contactDomain.setAlternatePhoneNumber(contact.getAlternatePhoneNumber());
		contactDomain.setDayTimePhoneNumber(contact.getDayTimePhoneNumber());
		contactDomain.setEmailAddress(contact.getEmailAddress());
		contactDomain.setHomePhoneNumber(contact.getHomePhoneNumber());
		nameDomain.setFirstName(name.getFirstName());
		nameDomain.setLastName(name.getLastName());
		nameDomain.setSeperatorName0(name.getSeperatorName0());
		nameDomain.setSeperatorName1(name.getSeperatorName1());
		contactDomain.setName(nameDomain);
		contactDomain.setSendsMessage(contact.getSendsMessage());
		contactDomain.setSeperatorContact0(contact.getSeperatorContact0());
		billingDomain.setContact(contactDomain);
		OrderTotals orderTotal = xmlOrder.getOrderTotal();
		orderToltalsDomain.setSeperatorOrderTotals0(orderTotal.getSeperatorOrderTotals0());
		orderToltalsDomain.setTotalPurchaseAmount(orderTotal.getTotalPurchaseAmount());
		xmlDomain.setOrderTotal(orderToltalsDomain);
		Source source = xmlOrder.getSource();
		sourceDomain.setClientId(source.getClientId());
		sourceDomain.setSellingChannelCode(source.getSellingChannelCode());
		sourceDomain.setSeperatorSource0(source.getSeperatorSource0());
		sourceDomain.setSeperatorSource1(source.getSeperatorSource1());
		sourceDomain.setSubClientId(source.getSubClientId());
		xmlDomain.setSource(sourceDomain);
		xmlDomain.setFulfilmentChannelCode(xmlOrder.getFulfilmentChannelCode());
		xmlDomain.setMessageCreatedTimeStamp(xmlOrder.getMessageCreatedTimeStamp());
		xmlDomain.setOrderStatus(xmlOrder.getOrderStatus());
		xmlDomain.setOrderStatusCode(xmlOrder.getOrderStatusCode());
		xmlDomain.setOrderStatusDescription(xmlOrder.getOrderStatusDescription());
		xmlDomain.setOrderTypeCode(xmlOrder.getOrderTypeCode());
		xmlDomain.setPartnerOrderId(xmlOrder.getPartnerOrderId());
		xmlDomain.setSellZlDivisonnbr(xmlOrder.getSellZlDivisonnbr());
		xmlDomain.setSellZlLocationnbr(xmlOrder.getSellZlLocationnbr());
		addressRepo.saveAndFlush(addressDomain);
		billingRepo.saveAndFlush(billingDomain);
		contactRepo.saveAndFlush(contactDomain);
		nameRepo.saveAndFlush(nameDomain);
		orderTotalRepo.saveAndFlush(orderToltalsDomain);
		sourceRepo.saveAndFlush(sourceDomain);
		return xmlDomain;
		
	}

}

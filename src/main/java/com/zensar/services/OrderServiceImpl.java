package com.zensar.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zensar.beans.JsonOrderBean;
import com.zensar.domain.JsonOrderDomain;
import com.zensar.repo.JsonOrderDomainRepo;
@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private JsonOrderDomainRepo jsonRepo;
	
	@Override
	public JsonOrderDomain setAndSaveJsonOrderDomain(JsonOrderBean order) {
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
		JsonOrderDomain res = jsonRepo.saveAndFlush(domain);
		if(res!=null)
			System.out.println("Order saved to db!");
		else
			System.out.println("Order not saved to db!");
		return domain;
	}

}

package com.zensar.services;

import java.util.List;

import com.zensar.beans.JsonOrderBean;
import com.zensar.beans.XmlFulfilmentOrderBean;

public interface OrderService {


	public List<JsonOrderBean> getJsonMessages();

	public List<XmlFulfilmentOrderBean> getXmlMessages();

}

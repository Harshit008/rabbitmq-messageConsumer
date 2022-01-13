package com.zensar.services;

import java.util.List;

import com.zensar.beans.JsonOrderBean;
import com.zensar.beans.XmlFulfilmentOrderBean;
import com.zensar.domain.JsonOrderDomain;

public interface OrderService {

	public JsonOrderDomain setAndSaveJsonOrderDomain(JsonOrderBean order);

	public List<JsonOrderBean> getJsonMessages();

	public List<XmlFulfilmentOrderBean> getXmlMessages();

}

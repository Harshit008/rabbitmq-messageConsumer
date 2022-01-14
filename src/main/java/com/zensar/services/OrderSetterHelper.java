package com.zensar.services;

import com.zensar.beans.JsonOrderBean;
import com.zensar.beans.XmlFulfilmentOrderBean;
import com.zensar.domain.JsonOrderDomain;
import com.zensar.domain.XmlFulfilmentOrderDomain;

public interface OrderSetterHelper {

	public JsonOrderDomain setJsonOrderDomain(JsonOrderBean order);

	public XmlFulfilmentOrderDomain setXmlOrderDomain(XmlFulfilmentOrderBean xmlOrder);

}

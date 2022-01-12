package com.zensar.services;

import com.zensar.beans.JsonOrderBean;
import com.zensar.domain.JsonOrderDomain;

public interface OrderService {

	public JsonOrderDomain setAndSaveJsonOrderDomain(JsonOrderBean order);

}

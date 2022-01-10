package com.zensar.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class XmlFulfilmentOrderBean {
	
	private String orderId;
	private String orderType;
	private String partnerId;
	private String orderStatus;
	private String messageCreatedTimeStamp;
	private String fulfilmentChannelCode;
	private String orderStatusCode;
	private String orderStatusDescription;
	private String sellZlDivisonnbr;
	private String sellZlLocationnbr;
	private Status status;
	private OrderTotals orderTotal;
	private BillingAddress billingAddress;

}

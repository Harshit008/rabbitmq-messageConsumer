package com.zensar.beans;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@XmlRootElement
public class XmlFulfilmentOrderBean {
	
	private String orderId;
	private String orderTypeCode;
	private String partnerOrderId;
	private String orderStatus;
	private String messageCreatedTimeStamp;
	private String fulfilmentChannelCode;
	private String orderStatusCode;
	private String orderStatusDescription;
	private String sellZlDivisonnbr;
	private String sellZlLocationnbr;
	private Source source;
	private OrderTotals orderTotal;
	private BillingAddress billingAddress;

}

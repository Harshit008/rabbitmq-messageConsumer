package com.zensar.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class XmlFulfilmentOrderDomain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;
	private String orderTypeCode;
	private String partnerOrderId;
	private String orderStatus;
	private String messageCreatedTimeStamp;
	private String fulfilmentChannelCode;
	private String orderStatusCode;
	private String orderStatusDescription;
	private String sellZlDivisonnbr;
	private String sellZlLocationnbr;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private SourceDomain source;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private OrderTotalsDomain orderTotal;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private BillingAddressDomain billingAddress;
}

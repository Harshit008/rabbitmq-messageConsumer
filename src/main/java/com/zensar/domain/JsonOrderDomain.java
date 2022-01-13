package com.zensar.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JsonOrderDomain {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;
	private String messageName;
	private String command;
	private String itemName;
	private String itemDescription;
	private String itemLength;
	private String itemWidth;
	private String itemHeight;
	private String itemWeight;
	private String imagePathname;
	private String rfidTagged;
	private String storageAttribute;
	private String pickType;
	private String upcList;
	private String createdDate;

}

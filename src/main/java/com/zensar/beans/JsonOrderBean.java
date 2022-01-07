package com.zensar.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JsonOrderBean {
	
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
	
	

}

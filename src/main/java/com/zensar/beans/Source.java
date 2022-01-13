package com.zensar.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Source {
	
	private String clientId;
	private String subClientId;
	private String sellingChannelCode;
	private String seperatorSource0;
	private String seperatorSource1;
	

}

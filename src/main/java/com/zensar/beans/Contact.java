package com.zensar.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Contact {
	
	private String custId;
	private Name name;
	private Address address;
	private String dayTimePhoneNumber;
	private String homePhoneNumber;
	private String alternatePhoneNumber;
	private String sendsMessage;
	private String emailAddress;
	private String seperatorContact0;
	
}

package com.zensar.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Address {

	private String line1;
	private String line2; 
	private String line3; 
	private String city;
	private String state;
	private String zipCode;
	private String countryCode;
	private String seperatorAddress0;
}

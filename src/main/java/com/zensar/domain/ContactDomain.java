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
public class ContactDomain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer custId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private NameDomain name;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private AddressDomain address;
	private String dayTimePhoneNumber;
	private String homePhoneNumber;
	private String alternatePhoneNumber;
	private String sendsMessage;
	private String emailAddress;
	private String seperatorContact0;
	

}

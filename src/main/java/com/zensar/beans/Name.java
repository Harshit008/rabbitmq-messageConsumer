package com.zensar.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Name {
	
	private String firstName;
	private String lastName;
	private String seperatorName0;
	private String seperatorName1;
}

package com.nj.gov.uhip.citizenregistrationmodel;

import lombok.Data;

@Data
public class ICitizenRegistration {
	private int registrationNo;
	private String firstName;
	private String lastName;
	private String email;
	private String planName;
	private String Dob;
	private Long ssn;
	private String address;
	private Integer income;
	private String kids;
	private String gender;
}

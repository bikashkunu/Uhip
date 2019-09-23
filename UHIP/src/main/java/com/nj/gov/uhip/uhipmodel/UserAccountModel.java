package com.nj.gov.uhip.uhipmodel;

import lombok.Data;

@Data
public class UserAccountModel {
	private Integer userAccId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String dob;
	private String gender;
	private Long ssn;
	private String role;
	private String active;

}

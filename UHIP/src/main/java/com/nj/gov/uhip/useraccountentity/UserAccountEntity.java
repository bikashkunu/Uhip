package com.nj.gov.uhip.useraccountentity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Table(name = "Account_Entity")
@Data
public class UserAccountEntity {
	@Id
	@GeneratedValue(generator = "uhip_generatorr")
	@SequenceGenerator(name = "uhip_generatorr", sequenceName = "uhip_sequence", initialValue = 1, allocationSize = 1)

	@Column(name = "user_AccId")
	private Integer userAccId;

	@Column(name = "first_Name")
	private String firstName;
	@Column(name = "active")
	private String active;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "Dob")
	@DateTimeFormat(style = "dd/MM/yyyy")
	private String dob;

	@Column(name = "Gender")
	private String gender;

	@Column(name = "ssn")
	private Long ssn;

	@Column(name = "Role")
	private String role;

	@CreationTimestamp
	private Date createDate;

	@UpdateTimestamp
	private Date updateDate;

}

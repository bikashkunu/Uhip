package com.nj.gov.uhip.citizenregistrationentity;

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
@Table(name = "CitizenRegistration_Entity")
@Data
public class CitizenRegistrationEntity {
	@Id
	@GeneratedValue
	/*@SequenceGenerator(name = "RNO_REGISTER", sequenceName = "RNO_REGISTERR", initialValue = 1, allocationSize = 1)*/
	@Column(name = "REGISTRATION_NO")
	private int registrationNo;
	@Column(name = "FIRST_NAME")
	private String firstName;
	@Column(name = "lAST_NAME")
	private String lastName;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "PLAN_NAME")
	private String planName;
	@Column(name = "SSN")
	private Long ssn;
	@Column(name = "ADDRESS")
	private String address;
	@Column(name = "KIDS")
	private String kids;
	@Column(name = "INCOME")
	private Integer income;
	@Column(name = "Dob")
	@DateTimeFormat(style = "dd/MM/yyyy")
	private String Dob;
	@Column(name = "GENDER")
	private String gender;
	@CreationTimestamp
	private Date createDate;
	@UpdateTimestamp
	private Date updateDate;

}

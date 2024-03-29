package com.nj.gov.uhip.corespondenceentity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "CO_BATCH_RUN_DTLS")
public class CorespondenceRunDetailsEntity {
	@Id
	@GeneratedValue
	@Column(name = "BATCH_RUN_SEQ")
	Integer runSeq;

	@Column(name = "BATCH_NAME")
	String batchName;

	@CreationTimestamp
	@Column(name = "START_DATE")
	Date startDate;

	@Column(name = "END_DATE")
	Date endDate;

	@Column(name = "BATCH_RUN_STATUS")
	private String batchRunStatus;

	@Column(name = "INSTANCE_NUM")
	private Integer instanceNum;

}

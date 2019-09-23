package com.nj.gov.uhip.corespondencemodel;

import java.util.Date;

import lombok.Data;

@Data
public class CorespondenceRunDetailsModel {
	private Integer runSeq;

	private String batchName;

	private Date startDate;

	private Date endDate;

	private String batchRunStatus;

	private Integer instanceNum;

}

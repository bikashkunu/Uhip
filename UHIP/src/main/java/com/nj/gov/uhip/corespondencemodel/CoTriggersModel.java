package com.nj.gov.uhip.corespondencemodel;

import java.util.Date;

import lombok.Data;

@Data
public class CoTriggersModel {
	private Integer triggerId;

	private Long caseNum;

	private String triggerStatus;

	private Date createdDate;

	private Date updatedDate;

}

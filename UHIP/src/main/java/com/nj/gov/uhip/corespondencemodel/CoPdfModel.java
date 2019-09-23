package com.nj.gov.uhip.corespondencemodel;

import lombok.Data;

@Data
public class CoPdfModel {

	Integer coPdfId;

	Long caseNumber;

	byte[] pdfDocument;

	String planName;

	String PlanStatus;

}

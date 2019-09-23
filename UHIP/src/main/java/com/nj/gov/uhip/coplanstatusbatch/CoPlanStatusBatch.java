package com.nj.gov.uhip.coplanstatusbatch;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Header;
import com.nj.gov.uhip.EligibilityDetailEntity.EligibilityDetailEntity;
import com.nj.gov.uhip.corespondenceentity.CoPdfEntity;
import com.nj.gov.uhip.corespondencemodel.CoPdfModel;
import com.nj.gov.uhip.corespondencemodel.CoTriggersModel;
import com.nj.gov.uhip.corespondencemodel.CorespondenceRunDetailsModel;
import com.nj.gov.uhip.corespondencservice.CorespondenceRunDetailsService;
import com.nj.gov.uhip.corespondencservice.CorespondenceRunDetailsServiceImpl;
import com.nj.gov.uhip.ed.EligibilityDetailModel.EligibilityDetailModel;
import com.nj.gov.uhip.ed.EligibilityDetailsRepository.EligibilityDetailsRepository;
import com.nj.gov.uhip.utility.PdfGeneration;

@Service
public class CoPlanStatusBatch {
	// EligibilityDetailsRepository repository
	@Autowired
	private EligibilityDetailsRepository edrepository;
	private int SUCC_TRIGGERS;
	private int FAIL_TRIGGERS;
	@Autowired
	private CorespondenceRunDetailsServiceImpl corespondencerundetails;
	@Autowired
	private PdfGeneration pdfgenerator;
	@Autowired
	private CorespondenceRunDetailsService corespondenceservice;

	public void PreProcess() {
		CorespondenceRunDetailsModel cosmodel = new CorespondenceRunDetailsModel();
		cosmodel.setBatchName("CoPlanStatusBatch");
		cosmodel.setBatchRunStatus("ST");
		cosmodel.setEndDate(new Date());
		boolean flag = corespondenceservice.insertCorespondenceDetails(cosmodel);
		Start();
	}

	public void Start() {
		List<CoTriggersModel> cotriggermodel = corespondenceservice.findAllTrigger();
		if (!cotriggermodel.isEmpty()) {
			for (CoTriggersModel cotrigger : cotriggermodel) {
				process(cotrigger);
			}

		}

	}

	public void process(CoTriggersModel trigger) {
		try {
			// from trigger get case number
			long caseNo = trigger.getCaseNum();

			// using case num read eligibility data --- 1
			EligibilityDetailModel edModel = corespondencerundetails.findByCaseNo(caseNo);

			// from elig data identify elig status
			String planStatus = edModel.getPlanStatus();

			// Based Status -- generate pdf
			if (planStatus.equals("AP")) {
				buildApPdf(edModel);
			} else if (planStatus.equals("DN")) {
				buildDnPdf(edModel);
			} else {
				buildTnPdf();
			}
			// coBatchService.storePdf(null);

			// send pdf in email
			sendPdfEmail();

			// update trigger as completed ---- 3
			// coBatchService.updateTrgStatus(trigger.getTriggerId(), "C");

			SUCC_TRIGGERS++;

		} catch (Exception e) {
			FAIL_TRIGGERS++;
		}
	}

	private void buildDnPdf(EligibilityDetailModel edmodel) {
		// TODO Auto-generated method stub
		/*
		 * ByteArrayInputStream bis = pdfgenerator.citiesReport(edmodel); HttpHeaders
		 * headers = new HttpHeaders(); headers.add("Content-Disposition",
		 * "inline; filename=citiesreport.pdf");
		 */
		ByteArrayInputStream bis = pdfgenerator.citiesReport(edmodel);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

	}

	private void buildApPdf(EligibilityDetailModel edmodel) throws IOException {
		ByteArrayInputStream bis = pdfgenerator.citiesReport(edmodel);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

		// copdfentity.setPdfDocument(bis.read());
		try {
			FileInputStream fin = new FileInputStream("E:\\ITextTest.pdf");
			int file = fin.read();
			BigInteger bit = BigInteger.valueOf(file);
			byte[] b = bit.toByteArray();
			CoPdfModel model = new CoPdfModel();
			model.setCaseNumber(edmodel.getCaseNum());
			model.setPdfDocument(b);
			model.setPlanName(edmodel.getPlanName());
			model.setPlanStatus(edmodel.getPlanStatus());
			/* CoPdfEntity copdfentity = new CoPdfEntity(); */
			/*model.setCaseNumber(edmodel.getCaseNum());
			model.setPdfDocument(b);
			model.setPlanName(edmodel.getPlanName());
			model.setPlanStatus(edmodel.getPlanStatus());*/
			// Call The Service Class Method to Save Data
			corespondenceservice.storePdf(model);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Call To Save The Data

		// TODO Auto-generated method stub
	}

	private void buildTnPdf() {
		// TODO Auto-generated method stub

	}

	private void sendPdfEmail() {
		// TODO Auto-generated method stub
	}
	// Save inside DataBase

}

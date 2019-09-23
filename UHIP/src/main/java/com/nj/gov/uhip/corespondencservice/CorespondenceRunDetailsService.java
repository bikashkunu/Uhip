package com.nj.gov.uhip.corespondencservice;

import java.util.List;

import com.nj.gov.uhip.corespondencemodel.CoPdfModel;
import com.nj.gov.uhip.corespondencemodel.CoTriggersModel;
import com.nj.gov.uhip.corespondencemodel.CorespondenceRunDetailsModel;
import com.nj.gov.uhip.ed.EligibilityDetailModel.EligibilityDetailModel;

public interface CorespondenceRunDetailsService {
	public boolean insertCorespondenceDetails(CorespondenceRunDetailsModel corespondencemodel);

	public List<CoTriggersModel> findAllTrigger();
	public EligibilityDetailModel findByCaseNo(Long caseNo);
	public boolean storePdf(CoPdfModel model);

}

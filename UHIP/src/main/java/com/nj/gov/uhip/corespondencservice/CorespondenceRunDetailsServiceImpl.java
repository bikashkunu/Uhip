package com.nj.gov.uhip.corespondencservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nj.gov.uhip.EligibilityDetailEntity.EligibilityDetailEntity;
import com.nj.gov.uhip.corespondenceentity.CoPdfEntity;
import com.nj.gov.uhip.corespondenceentity.CoTriggersEntity;
import com.nj.gov.uhip.corespondenceentity.CorespondenceRunDetailsEntity;
import com.nj.gov.uhip.corespondencemodel.CoPdfModel;
import com.nj.gov.uhip.corespondencemodel.CoTriggersModel;
import com.nj.gov.uhip.corespondencemodel.CorespondenceRunDetailsModel;
import com.nj.gov.uhip.corespondencrepository.CoPdfRepository;
import com.nj.gov.uhip.corespondencrepository.CoTriggerRepository;
import com.nj.gov.uhip.corespondencrepository.CorespondenceRunDetailsRepository;
import com.nj.gov.uhip.ed.EligibilityDetailModel.EligibilityDetailModel;
import com.nj.gov.uhip.ed.EligibilityDetailsRepository.EligibilityDetailsRepository;

@Service
public class CorespondenceRunDetailsServiceImpl implements CorespondenceRunDetailsService {
	// AddRepository Method
	@Autowired
	private CorespondenceRunDetailsRepository corepondencerepository;
	// Correspondence Entity
	CorespondenceRunDetailsEntity corespondenceentity = null;
	@Autowired
	private CoTriggerRepository corespondencerepository;
	@Autowired
	private EligibilityDetailsRepository eligiblitydetails;
	@Autowired
	private CoPdfRepository coPdfRepo;

	@Override
	public boolean insertCorespondenceDetails(CorespondenceRunDetailsModel corespondencemodel) {
		// TODO Auto-generated method stub
		CorespondenceRunDetailsEntity corespondenceentity = new CorespondenceRunDetailsEntity();
		// Copy The model to Entity
		BeanUtils.copyProperties(corespondencemodel, corespondenceentity);
		corespondenceentity = corepondencerepository.save(corespondenceentity);
		if (corespondenceentity.getRunSeq() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<CoTriggersModel> findAllTrigger() {
		// TODO Auto-generated method stub
		List<CoTriggersEntity> entities = corespondencerepository.findAllPendingTrigger("P");
		List<CoTriggersModel> models = new ArrayList();
		if (!entities.isEmpty()) {
			for (CoTriggersEntity entity : entities) {
				CoTriggersModel model = new CoTriggersModel();
				BeanUtils.copyProperties(entity, model);
				models.add(model);
			}
		}
		return models;

	}

	@Override
	public EligibilityDetailModel findByCaseNo(Long caseNo) {
		// TODO Auto-generated method stub
		EligibilityDetailEntity edentity = eligiblitydetails.findByCaseNum(caseNo);
		EligibilityDetailModel edmodel = new EligibilityDetailModel();
		BeanUtils.copyProperties(edentity, edmodel);
		return edmodel;
	}

	@Override
	public boolean storePdf(CoPdfModel model) {
		CoPdfEntity entity = new CoPdfEntity();
		BeanUtils.copyProperties(entity, model);
		entity = coPdfRepo.save(entity);
		return entity.getCoPdfId() > 0;
	}
}

package com.nj.gov.uhip.citizenregistrationservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nj.gov.uhip.adminexception.NotEligibleFormPolicy;
import com.nj.gov.uhip.citizenregistrationentity.CitizenRegistrationEntity;
import com.nj.gov.uhip.citizenregistrationmodel.ICitizenRegistration;
import com.nj.gov.uhip.citizenregistrationrepository.CitiRegistrationRepository;
import com.nj.gov.uhip.restclient.RestClient;

@Service
public class CitizenRegistrationimpl implements CitizenRegistration {
	@Autowired
	public CitiRegistrationRepository cicrrepository;
	@Autowired
	public RestClient restclient;
	private static final Logger logger = LoggerFactory.getLogger(CitizenRegistrationimpl.class);

	@Override
	public boolean citizenRegistration(ICitizenRegistration citizenregistration) {
		// TODO Auto-generated method stub
		logger.debug("**Inside citizenRegistration() Method **");
		CitizenRegistrationEntity entity = new CitizenRegistrationEntity();
		// Get The Ssn Number
		Long ssn = citizenregistration.getSsn();
		// copy the property from model to entity
		BeanUtils.copyProperties(citizenregistration, entity);
		// Call Rest Client Class
		String state = restclient.verifySsnNumber(ssn);
		if (state.equals("Alabama")) {
			CitizenRegistrationEntity entityy = cicrrepository.save(entity);
		} else {

			throw new NotEligibleFormPolicy("Sorry You ARe Not Belongs From Alabama");
		}
		logger.info("****");

		return true;
	}
}

package com.nj.gov.uhip.uhipservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nj.gov.uhip.adminexception.AdminException;
import com.nj.gov.uhip.uhipmodel.UserAccountModel;
import com.nj.gov.uhip.uhiprepository.UserAccountRepository;
import com.nj.gov.uhip.useraccountentity.UserAccountEntity;
import com.nj.gov.uhip.utility.PasswordEncryption;
import com.nj.gov.uhip.utility.UhipConstrant;
import com.nj.gov.uhip.utility.UhipMailService;

/**
 * @author HP UHIP Service Class
 *
 */
@Service
public class UshipServiceImpl implements UhipServiceI {
	public static boolean isSave = false;
	@Autowired
	private UserAccountRepository accrepository;
	@Autowired
	private UhipMailService uhipmailservice;
	private UhipConstrant uhipconstant = new UhipConstrant();

	private static final Logger logger = LoggerFactory.getLogger(UshipServiceImpl.class);

	/**
	 * Method For AccountCreation All Entity Details Method Name:SaveAccount
	 * Parameter:UserAccountModel return Boolean
	 */
	@Override
	public boolean saveAccount(UserAccountModel accountmodel) {
		// Create Entity Class Object
		logger.info("Exececution Staarted Of SaveAccount Method");

		try {
			logger.debug("Before Clling Repository 0Method");
			accountmodel.setActive("Y");
			UserAccountEntity userentity = new UserAccountEntity();
			// Encrypt the password
			
			// convert the account model to user entity
			BeanUtils.copyProperties(accountmodel, userentity);
			// Call The Repository Class Method
			userentity = accrepository.save(userentity);
			logger.debug("Successfully Called Repository Clas ethod");
			if (!userentity.getSsn().equals(null)) {

				// accountRegistrationConformaion(accountmodel.getEmail());
				isSave = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("**Exception Occured in Save()**" + e.getMessage());
			isSave = false;
			throw new AdminException(uhipconstant.ADMIN_EXCEPTION_MSG);
		}
		logger.info("**Method Execution  SuccessFully  Complited**");
		return isSave;
	}

	@Override
	public String emailValidation(String email) {
		// TODO Auto-generated method stub
		logger.debug("**Email Validation Mehod**");
		int count = accrepository.findByEmail(email);
		if (count >= 1) {
			return uhipconstant.DUPLICATE_EMAL;
		}
		logger.info("**Email Validation method Execution Success**");
		return uhipconstant.UNIQUE_EMAIL;
	}

	/**
	 * Method For Sending Email
	 *
	 * @param String
	 * @return boolean
	 */
	public void accountRegistrationConformaion(String Sendto) {
		logger.debug("**Email Conformation Mail**");
		uhipmailservice.SendConformationMail(Sendto, "Checking Purpose", "Check It Out");
	}

	/**
	 * Method For Getting all SuccesFull Registration
	 *
	 * @param
	 * @return UserAccountModel
	 */
	@Override
	public List<UserAccountModel> getAllRegistration() {
		// TODO Auto-generated method stub
		// connect to the DataBase To GET Data
		logger.debug("**getAllRegistration() Execution Started**");
		List<UserAccountEntity> getAllAcount = accrepository.findAll();
		List<UserAccountModel> accmodel = new ArrayList<UserAccountModel>();
		if (!getAllAcount.isEmpty()) {
			for (UserAccountEntity account : getAllAcount) {
				UserAccountModel useraccountmodel = new UserAccountModel();
				BeanUtils.copyProperties(account, useraccountmodel);
				accmodel.add(useraccountmodel);
			}
		}
		logger.info("**getAllRegistration()  Complited**");
		return accmodel;
	}

	/**
	 * Method For Deactivate User
	 *
	 * @param active
	 *            id
	 * @return String
	 */
	@Override
	public String deactivateAccount(String active, int id) {
		// TODO Auto-generated method stub
		// Call Repository Method
		try {
			logger.debug("**deactivateAccount() Execution Started**");
			int updation = accrepository.statusupdate(active, id);
			if (updation > 0) {
				logger.warn("**updation condition check**" + updation);
				return uhipconstant.STATUS_UPDATE_DEACTIVATE;
			}

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception Occured IN DeactivateAccount Method");
			e.printStackTrace();
		}
		logger.info("**deactivateAccount Method Execution Successsfully Complited**");
		return uhipconstant.FAILED_TOUPDATE;
	}

	/**
	 * Method For activate account
	 *
	 * @param activate
	 *            id
	 * @return UserAccountModel
	 */

	@Override
	public String activeAccount(String active, int id) {
		// TODO Auto-generated method stub
		try {
			logger.debug("**activeAccount Method Execution STARTED**");
			int updation = accrepository.statusactivate(active, id);
			if (updation > 0) {
				logger.warn("**updation condition check**" + updation);
				logger.info("**activeAccount() Execution Success**");
				return uhipconstant.STATUS_UPDATE_ACTIVATE;
			}

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("**Exeception Occured In activeAccount()**");
			e.printStackTrace();
		}

		return uhipconstant.FAILED_TOUPDATE;
	}

	/**
	 * Method For Edit account
	 * 
	 * @param id
	 * 
	 * @return List<UserAccountModel>
	 */
	@Override
	public UserAccountModel editAccount(int id) {
		// TODO Auto-generated method stub
		logger.debug("**editAccount() MethodExecution Started**");
		UserAccountModel useraccmodel = new UserAccountModel();

		UserAccountEntity entity = accrepository.findById(id).get();
		UserAccountModel useraccount = new UserAccountModel();
		BeanUtils.copyProperties(entity, useraccmodel);

		logger.info("**editAccount() Method Execution Complited**");
		return useraccmodel;
	}

}

package com.nj.gov.uhip.uhipservice;

import java.util.List;

import com.nj.gov.uhip.uhipmodel.UserAccountModel;
import com.nj.gov.uhip.uhiprepository.UserAccountRepository;
import com.nj.gov.uhip.useraccountentity.UserAccountEntity;

public interface UhipServiceI {
	public boolean saveAccount(UserAccountModel accountmodel);

	public String emailValidation(String email);

	public List<UserAccountModel> getAllRegistration();

	public String deactivateAccount(String active, int id);
	public String activeAccount(String active,int id);
	public UserAccountModel editAccount(int id);

}

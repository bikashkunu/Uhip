package com.nj.gov.uhip.loginservice;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nj.gov.uhip.uhipmodel.UserAccountModel;
import com.nj.gov.uhip.uhiprepository.UserAccountRepository;
import com.nj.gov.uhip.useraccountentity.UserAccountEntity;

@Service
public class LoginService implements ILoginService {
	@Autowired
	UserAccountRepository useraccountrepository;

	public UserAccountModel userloginmode(String email) {
		// Call Repository Method To GetData
		UserAccountEntity useraccountentity = useraccountrepository.findByEmailforVerification(email);
		UserAccountModel useraccountmodel = new UserAccountModel();
		BeanUtils.copyProperties(useraccountentity, useraccountmodel);
		return useraccountmodel;

	}
}

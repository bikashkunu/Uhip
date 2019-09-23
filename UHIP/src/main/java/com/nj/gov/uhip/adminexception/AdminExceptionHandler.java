package com.nj.gov.uhip.adminexception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nj.gov.uhip.useraccountreationcontroller.UserAccountCreationController;
import com.nj.gov.uhip.utility.UhipConstrant;

/**
 * @author HP AdminExceptionHandler This Method Is Useful Form
 *         GlobalExceptionHandling in UHIP 
 *         return:ErrorPage
 *
 */
@Controller
@ControllerAdvice
public class AdminExceptionHandler {
	UhipConstrant uhipconstrant = new UhipConstrant();

	@ExceptionHandler(value = AdminException.class)
	public String AdminException(Model model) {
		return uhipconstrant.Erro_Page;
	}
}
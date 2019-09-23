package com.nj.gov.uhip.useraccountreationcontroller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import com.nj.gov.uhip.citizenregistrationentity.CitizenRegistrationEntity;
import com.nj.gov.uhip.citizenregistrationmodel.ICitizenRegistration;
import com.nj.gov.uhip.citizenregistrationservice.CitizenRegistration;
import com.nj.gov.uhip.ed.EligibilityDetailModel.EligibilityDetailModel;

import com.nj.gov.uhip.uhipmodel.UserAccountModel;
import com.nj.gov.uhip.uhipproperty.UhipProperty;
import com.nj.gov.uhip.uhipservice.UhipServiceI;
import com.nj.gov.uhip.utility.PasswordEncryption;
import com.nj.gov.uhip.utility.PdfGeneration;
import com.nj.gov.uhip.utility.UhipConstrant;

/**
 * @author HP UhIp Controller UserAccountCreationController
 *
 */
@Controller
public class UserAccountCreationController {
	private static final Logger logger = LoggerFactory.getLogger(UserAccountCreationController.class);

	@Autowired
	private UhipServiceI uhipservice;
	@Autowired
	public UhipProperty uhipproperty = new UhipProperty();
	@Autowired
	public PasswordEncryption passwordencryption;
	@Autowired
	public CitizenRegistration citzenregistrationservice;
	/**
	 * Method verifyStateName enrollSsn
	 * 
	 * @param Model
	 * @return Form
	 */
	// Create Constant Class Object
	UhipConstrant uhipconstant = new UhipConstrant();
	@Autowired
	PdfGeneration pdfgenerator;

	@RequestMapping(value = "/Form", method = RequestMethod.GET)
	public String showForm(Model model) {
		logger.info("**ShowForm Method Started**");
		UserAccountModel useraccountmodel = new UserAccountModel();
		model.addAttribute(uhipconstant.USERACCOUNT_MODEL, useraccountmodel);
		logger.info("ShowForm Method Ended");
		formValue(model);
		return uhipconstant.MODEL_ATTRIBUTE;
	}

	/**
	 * Method loading FormValue
	 * 
	 * @param Model
	 * @return void
	 */
	public void formValue(Model model) {
		logger.debug("**formValue() Execution Started**");
		List<String> genders = new ArrayList<>();
		genders.add("Male");
		genders.add("Female");
		model.addAttribute(uhipconstant.Gender_list, genders);
		List<String> roles = new ArrayList<>();
		roles.add("Admin");
		roles.add("CaseWorker");
		logger.info("**formValue() Execution Complited**");
		model.addAttribute(uhipconstant.ROLES_LIST, roles);
	}

	/**
	 * Method For Registration PostMapping
	 * 
	 * @param Model
	 * @return RedirectView
	 */
	@RequestMapping(value = "/Register", method = RequestMethod.POST)
	public RedirectView register(@ModelAttribute("useraccountmodel") UserAccountModel uamodel,
			RedirectAttributes redirect) {
		logger.debug("**register() Execution Started**");
		// Get The Password

		String password = uamodel.getPassword();
		// Call Encryption Class Method
		String pwd = passwordencryption.secretKey(password, "ranjan");
		// Set The Password To Model Class
		uamodel.setPassword(pwd);
		boolean Issave = uhipservice.saveAccount(uamodel);
		Map<String, String> props = uhipproperty.getUhipProperty();
		if (Issave) {
			logger.debug(props.get("regdsuccess"));
			redirect.addFlashAttribute("Success", props.get("regdsuccess"));
		}
		logger.info("register() Execution Complited");

		return new RedirectView("/redirect", true);
	}

	/**
	 * Method For Getting All Register
	 * 
	 * @param UserAccountModel,
	 *            HttpServletRequest ,Model
	 * @return Result Form
	 */
	@RequestMapping(value = "/redirect", method = RequestMethod.GET)
	public String redirect(@ModelAttribute("useraccountmodel") UserAccountModel uamodel, HttpServletRequest request,
			Model model) {
		logger.info("**Entering inside RedirectMethod**");
		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
		if (inputFlashMap != null) {
			model.addAttribute("success", inputFlashMap.get("regdsuccess"));
			logger.info("**MethodExecution Success**");
		}
		formValue(model);
		logger.info("Redirect Metod Execution Complited");
		return uhipconstant.MODEL_ATTRIBUTE;
	}

	/**
	 * Method For Getting DuplicateCheck
	 * 
	 * @param string
	 *            email
	 * @return response
	 */

	@GetMapping(value = "/emailvalid/{email}")
	public @ResponseBody String checkDuplicateEmail(@PathVariable("email") String email) {
		String emaill = email;
		logger.debug("**checkDuplicateEmail() Execution Started**");
		if (emaill == null || "".equals(emaill)) {

		}
		String response = uhipservice.emailValidation(emaill);
		logger.info("**checkDuplicateEmail() Execution Complited**");
		return response;

	}

	/**
	 * Method For Getting All Register
	 * 
	 * @param model
	 * @return Result
	 */
	@GetMapping(value = "/allaccount")
	public String getAllregistration(Model model, HttpServletRequest request) {
		logger.info("**getAllregistration() Execution Started**");
		List<UserAccountModel> allaccount = uhipservice.getAllRegistration();
		model.addAttribute("allaccount", allaccount);
		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
		if (inputFlashMap != null) {
			model.addAttribute("status", inputFlashMap.get("status"));

		}
		logger.info("**getAllregistration() Execution Complited**");
		return UhipConstrant.ALL_ACCOUNT;

	}

	/**
	 * Method For Deactivateing Account
	 * 
	 * @param model,id,redirect
	 * @return Redirect
	 */
	@GetMapping(value = "/deactivate")
	public RedirectView deactivate(@RequestParam int id, RedirectAttributes redirect,
			@ModelAttribute("useraccountmodel") UserAccountModel uamodel) {
		// Call Service Class Method
		logger.debug("**deactivate() Execution Started**");
		String status = uhipservice.deactivateAccount(uhipconstant.DEACTIVATE_KEY, id);
		redirect.addFlashAttribute(uhipconstant.STATUS, status);
		logger.info("**deactivate() Execution Complited**");
		return new RedirectView(uhipconstant.REDIRECT_ALL_ACCOUNT, true);
	}

	/**
	 * Method For Activate account,
	 * 
	 * @param model,id,redirect,UserAccountModel
	 * @return RedirectView
	 */
	@GetMapping(value = "/activate")
	public RedirectView activate(int id, RedirectAttributes redirect,
			@ModelAttribute("useraccountmodel") UserAccountModel uamodel) {
		logger.debug("** activate Method Execution Started**");
		String status = uhipservice.activeAccount(uhipconstant.ACTIVATE_KEY, id);
		redirect.addFlashAttribute(uhipconstant.STATUS, status);
		logger.info("** activate() Execution Complited**");
		return new RedirectView(uhipconstant.REDIRECT_ALL_ACCOUNT, true);
	}

	/**
	 * Method For Ac editAccount
	 * 
	 * @param model,id
	 * @return String
	 */

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editAccount(@RequestParam int id, Model model) {
		// call Service ClassMethod
		logger.debug("**editAccount() Method Execution Started**");
		UserAccountModel useraccountmodel = uhipservice.editAccount(id);
		model.addAttribute("editaccount", useraccountmodel);
		formValue(model);
		logger.info("** editAccount() Method Execution Complited**");
		return "editAccount";
	}

	/**
	 * Method For Ac editAccount
	 * 
	 * @param model,id
	 * @return String
	 */

	@RequestMapping(value = "/editSave", method = RequestMethod.POST)
	public String saveEditi(Model model, @ModelAttribute("editaccount") UserAccountModel modelaccount) {
		logger.debug("** saveEditi() Method**");
		boolean isSave = uhipservice.saveAccount(modelaccount);
		if (isSave) {
			model.addAttribute("Successfull", "SuccessFullyEdited");
		} else {
			model.addAttribute("Failed", "FailedToAddData");
		}
		formValue(model);
		logger.info("** saveEditi() Execution Complited**");
		return "editAccount";
	}

	@RequestMapping(value = "/Menu", method = RequestMethod.GET)
	public String menu(Model model) {
		return "menu";
	}

	/**
	 * Method For applicationRegistration
	 * 
	 * @param model,id
	 * @return String
	 */

	@RequestMapping(value = "/appregister", method = RequestMethod.GET)
	public String applicationRegistration(Model model) {
		ICitizenRegistration citizenregistration = new ICitizenRegistration();
		model.addAttribute("citizenregistration", citizenregistration);
		formValue(model);
		return "citizenRegistration";
	}

	/**
	 * Method For registerApplication
	 * 
	 * @param model
	 * @return String
	 */

	@RequestMapping(value = "/Registration", method = RequestMethod.POST)
	public String registerApplication(@ModelAttribute("citizenregistration") ICitizenRegistration citizenregistration,
			Model model) {
		boolean status = citzenregistrationservice.citizenRegistration(citizenregistration);
		if (status) {
			model.addAttribute("SuccessFullyRegister", "Registration Success");
			formValue(model);
		}
		return "citizenRegistration";
	}

}
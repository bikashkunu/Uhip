package com.nj.gov.uhip.logincontroller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nj.gov.uhip.coplanstatusbatch.CoPlanStatusBatch;
import com.nj.gov.uhip.loginservice.LoginService;
import com.nj.gov.uhip.uhipmodel.UserAccountModel;
import com.nj.gov.uhip.utility.PasswordEncryption;
import com.nj.gov.uhip.utility.UhipConstrant;

@Controller
public class LoginController {
	@Autowired
	private LoginService loginservice;
	@Autowired
	private PasswordEncryption passwordencryption;
	private UhipConstrant uhipconstrant = new UhipConstrant();
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	// For Testing Purpose I uSE This
	@Autowired
	private CoPlanStatusBatch batch;

	@GetMapping(value = "/loging")
	public String loging(Model model) {
		logger.debug("**ENTER INSIDE THE LOGIN METHOD**");
		return "loging";

	}

	@RequestMapping(value =  "/logincheck", method = RequestMethod.GET)
	public String loginCredientialCheck(HttpServletRequest request) {
		logger.debug("**IoginCredientialCheck() nethod execution started**");
		String mail = request.getParameter(uhipconstrant.USER_EMAIL);
		String password = request.getParameter(uhipconstrant.USER_PASSWORD);

		// Call To The Service Class Method
		UserAccountModel useraccmodel = loginservice.userloginmode(mail);
		String status = useraccmodel.getActive();
		String role = useraccmodel.getRole();
		String pwd = useraccmodel.getPassword();
		if (!useraccmodel.equals(null)) {

			String passwordd = passwordencryption.decript(pwd, "ranjan");
			if (passwordd.equals(password)) {
				if (status.equals("Y")) {
					if (role.equals("Admin")) {
						return "Admin";
					} else {
						return "caseworker";
					}
				} else {
					return "loging";
				}

			} else {
				return "loging";
			}

		}
 
		logger.info("**loging loginCredientialCheck() method execution succeccfully complited**");
		return "loging";
	}

	@GetMapping(value = "/check")
	public String PreProcess() {
		batch.PreProcess();
		return "Test";
	}

/*	@RequestMapping(value = "/pdfreport", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> planStatus(EligibilityDetailModel edmodel) throws IOException {

		

		
		ByteArrayInputStream bis =	pdfgenerator.citiesReport(edmodel);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");
		
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}
*/
}

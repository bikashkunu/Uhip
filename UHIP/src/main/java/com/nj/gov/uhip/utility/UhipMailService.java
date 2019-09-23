package com.nj.gov.uhip.utility;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class UhipMailService {
	@Autowired
	private JavaMailSender mailsender;
	

	public boolean SendConformationMail(String SendTo,String subject,String body) {
		try {
			SimpleMailMessage mailmessage = new SimpleMailMessage();
			mailmessage.setTo(SendTo);
			mailmessage.setSubject(subject);
			mailmessage.setText(body);
			mailsender.send(mailmessage);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return true;
	}

}

package com.nj.gov.uhip.restclient;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestClient {
	public static String state = null;

	public String verifySsnNumber(long ssn) {
		try {
			final String url = "http://localhost:2323/statename/" + ssn;
			RestTemplate resttemplate = new RestTemplate();
			state = resttemplate.getForObject(url, String.class);
		

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return state;

	}

}

package com.nj.gov.uhip.utility;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

@Service
public class PasswordEncryption {
	private static SecretKey secretkey;
	private static byte[] key;

	public static void secretKey(String keyy) {
		MessageDigest sha = null;
		try {
			key = keyy.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			secretkey = new SecretKeySpec(key, "AES");
			System.out.println(secretkey.toString());

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String secretKey(String encrypt, String secretKey) {
		try {
			secretKey(secretKey);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretkey);
			return Base64.getEncoder().encodeToString(cipher.doFinal(encrypt.getBytes("UTF-8")));

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error While DoingEncryption" + e.getMessage());
		}
		return null;

	}

	public static String decript(String strToDecrypt, String secret) {

		try {
			secretKey(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, secretkey);
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			System.out.println("Exception Occured In Decryption" );

		}
		return secret;
	}

}

package com.jkkp.appapi.common.filter;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.security.auth.login.LoginException;

import sun.misc.BASE64Encoder;

public class CommonInterService {

	private boolean isValidUser(Map<String, Object> map) throws LoginException {

		try {
			String loginUser=(String) map.get("login_user");
			String loginFlag=(String) map.get("login_flag");
				if (EncoderByMd5(loginUser).equals(loginFlag))
					return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return true;
		}

	}
	
	public String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		BASE64Encoder base64en = new BASE64Encoder();

		String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
		return newstr;
	}
}

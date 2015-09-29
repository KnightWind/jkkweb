package com.jkkp.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.jkkp.modules.supplier.model.SupplierUser;


/**
 * session 会话过期跳转类
 * @author Administrator
 *
 */
public class LoginUtils {

	/**
	 * 根据传入的登录 action url 地址跳转
	 * @param response
	 * @param url
	 * @throws IOException 
	 */
	public static void sendRedirectUrl( SupplierUser su, String url) {
		if(su == null){
		}
	}
	
}

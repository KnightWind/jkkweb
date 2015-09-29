package com.jkkp.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// if (!(handler instanceof HandlerMethod)) {
		// return true;
		// }
		
		if(request.getRequestURI().endsWith("admin/index.xhtml")){
			return true;
		}
		
		if(request.getSession().getAttribute("su") == null){
			//String url = UserLoginType.returnPageUrl(request);
			response.sendRedirect("/jtxweb/supplierlogin.html");
			return false;
		}
	
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView mav)
			throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception excptn)
			throws Exception {
	}

}

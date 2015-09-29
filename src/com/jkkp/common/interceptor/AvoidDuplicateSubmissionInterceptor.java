package com.jkkp.common.interceptor;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AvoidDuplicateSubmissionInterceptor extends HandlerInterceptorAdapter {

	private static final Logger LOG = Logger .getLogger(AvoidDuplicateSubmissionInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();

		AvoidDuplicateSubmission annotation = method.getAnnotation(AvoidDuplicateSubmission.class);
		if (annotation != null) {
			boolean saveToken = annotation.saveToken();
			boolean removeToken = annotation.removeToken();
			if (saveToken) {
				request.getSession().setAttribute("token",UUID.randomUUID().toString());
			} else if (removeToken) {
				if (!check(request,response,handler)) {
					LOG.warn("please don't repeat submit!");
					request.getSession().removeAttribute("token");
					return false;
				}
				request.getSession().removeAttribute("token");
			}
		}
		return true;
	}

	/**
	 * 比较token
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private boolean check(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String serverToken = (String) request.getSession().getAttribute("token");
		if (StringUtils.isBlank(serverToken)) {
			handleInvalidToken(request,response,handler);
		}
		String clinetToken = request.getParameter("token");
		return serverToken.equals(clinetToken);
	}
	
	
    /** 
     * 当出现一个非法token调用 
     */  
    protected boolean handleInvalidToken(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception  
    {  
        String html = "<p style='text-align:center;padding-top:16%;color:red;font-size:20px'>请不要重复提交数据！</p>";
        writeMessageUtf8(response, html);  
        return false;  
    }  
      
    
    private void writeMessageUtf8(HttpServletResponse response, String html) throws IOException  
    {  
        try{  
        	response.setContentType("text/html; charset=utf-8");
            response.getWriter().print(html);  
        }  
        finally {  
            response.getWriter().close();  
        }  
    }

}

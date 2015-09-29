package com.jkkp.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jkkp.modules.supplier.service.ISupplierUserService;
import com.jkkp.modules.supplier.service.impl.SupplierUserServiceImpl;
import com.jkkp.modules.supplier.view.VSupplierUser;
import com.jkkp.secure.JkkpUserPrincipal;
import com.jkkp.utils.Pagination;
import com.jkkp.utils.ProjectContext;
import com.jkkp.utils.SpringContextUtil;

public class ProjectInterceptor extends HandlerInterceptorAdapter {
	
	private final static String SUPPLIER_URL = "/supplier/supplierlogin_list.xhtml";
	private final static String CROWD_URL = "/supplier/jl_index.xhtml";
	private final static String SUPERVISOR_URL = "/material/account/zc_index.xhtml";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		
		if (!StringUtils.isNotEmpty(ProjectContext.PROJECT_BASE_PATH)) {
			ProjectContext.PROJECT_SITE_PATH = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
			ProjectContext.PROJECT_BASE_PATH = ProjectContext.PROJECT_SITE_PATH + request.getContextPath();
		}
		
		ISupplierUserService supplierUserService = SpringContextUtil.getBean(SupplierUserServiceImpl.class);
		JkkpUserPrincipal userPrincipal = (JkkpUserPrincipal) request.getUserPrincipal();
		if (userPrincipal != null && (userPrincipal.isSupplier() || userPrincipal.isSupervisor() || userPrincipal.isCrowd() || userPrincipal.isForeman())) {
			if (request.getSession().getAttribute("su") == null) {
				int loginType = 0;
				if(userPrincipal.isSupplier()){
					loginType = 1;
				}else if(userPrincipal.isCrowd()){
					loginType = 2;
				}else if(userPrincipal.isSupervisor()){
					loginType = 5;
				}else if(userPrincipal.isForeman()){
					loginType = 3;
				}
				VSupplierUser user = supplierUserService.findSupplier(userPrincipal.getName(), loginType);
				request.getSession().setAttribute("su",user);
//				Cookie type = CookieUtils.getCookieByName(request, "loginType");
//				if(type == null){
//					response.addCookie(new Cookie("loginType", String.valueOf(loginType)));
//				}
			}
		}
//		else{
//			Cookie loginType = CookieUtils.getCookieByName(request, "loginType");
//			if(loginType != null){
//				String url = redirectUrl(loginType.getValue());
//				if(url != null){
//					response.sendRedirect(url);
//				}
//			}
//		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mav)
			throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception excptn)
			throws Exception {
		Pagination.clear();
		Pagination.clearSearchParams();
	}
	
	public String redirectUrl(String type){
		if(type != null){
			if(type.equals("1")){
				return SUPPLIER_URL;
			}
			if(type.equals("2")){
				return CROWD_URL;
			}
			if(type.equals("5")){
				return SUPERVISOR_URL;
			}
		}
		return null;
	}
}

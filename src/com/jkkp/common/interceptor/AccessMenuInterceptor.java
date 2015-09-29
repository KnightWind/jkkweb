package com.jkkp.common.interceptor;

import java.lang.reflect.Method;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jkkp.modules.system.model.AdminMenu;
import com.jkkp.modules.system.service.IAdminMenuService;
import com.jkkp.utils.CommonUtil;

public class AccessMenuInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private IAdminMenuService adminMenuService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		Method method = ((HandlerMethod) handler).getMethod();
		AccessMenu annotation = method.getAnnotation(AccessMenu.class);
		if (annotation != null) {
			this.initMenu(request);
		}
		return true;
	}

	
	//TODO ccn建议:把菜单目录保存至session中，只查询一次，不用每次都去数据库查询菜单目录，提升性能
	protected void initMenu(HttpServletRequest request) {
		String username = request.getRemoteUser();
		username="admin";
		Principal principal = request.getUserPrincipal();
		//TOTO dele
		username = "admin";
		/*Principal principal = request.getUserPrincipal();
		com.jkkp.secure.JkkpUserPrincipal userPrincipal = (com.jkkp.secure.JkkpUserPrincipal)principal;
		System.out.println(userPrincipal.getFullName());
		System.out.println(userPrincipal.getName());
		System.out.println(userPrincipal.getPassword());*/
		
		List<AdminMenu> menuList = adminMenuService.findBannerByAdmin(username);
		request.setAttribute("topMenu", menuList);
		Integer parentId = CommonUtil.stringToInteger(request.getParameter("pid"));
		if (parentId == null && !menuList.isEmpty()) {
			parentId = menuList.get(0).getId();
		}
		if (parentId != null) {
			request.setAttribute("menuTree", adminMenuService.findByAdmin(username, parentId));
			request.setAttribute("pid", parentId);
		}

		Integer menuId = CommonUtil.stringToInteger(request.getParameter("mid"));
		request.setAttribute("mid", menuId);
		if (menuId != null) {
			List<AdminMenu> navList = new ArrayList<AdminMenu>();
			this.addNavigation(navList, menuId);
			request.setAttribute("navList", navList);
		}
	}

	private void addNavigation(List<AdminMenu> menuList, Integer menuId) {
		if (menuId != null && menuId > 0) {
			AdminMenu parent = adminMenuService.findById(menuId);
			if (parent != null) {
				menuList.add(0, parent);
				this.addNavigation(menuList, parent.getPid());
			}
		}
	}
}

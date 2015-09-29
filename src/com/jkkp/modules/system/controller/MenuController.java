package com.jkkp.modules.system.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.modules.system.model.Menu;
import com.jkkp.modules.system.service.impl.MenuServiceImpl;
import com.jkkp.utils.CommonUtil;

@Controller
@RequestMapping(value = "/admin/menu")
public class MenuController extends BaseController{
	@Autowired
	private MenuServiceImpl menuServiceImpl;
	@AccessMenu
	@RequestMapping(value = "/index")
	public String list(HttpServletRequest request) {		
		request.setAttribute("pagination",menuServiceImpl.findPagination(null));		
		return "/admin/menu_list";
	}
	@AccessMenu
	@RequestMapping(value = "/up")
	public String lit(HttpServletRequest request,String[] id,String[] name,String[] url) {
		for (int i = 0; i < id.length; i++) {
			Menu menu=menuServiceImpl.findById(CommonUtil.stringToInteger(id[i]));
			menu.setName(name[i]);
			menu.setUrl(url[i]);
			menuServiceImpl.update(menu);
		}
		request.setAttribute("pagination",menuServiceImpl.findPagination(null));
		request.setAttribute("mid",CommonUtil.stringToInteger(request.getParameter("mid")));
		request.setAttribute("pid",CommonUtil.stringToInteger(request.getParameter("pid")));
		return "/admin/menu_list";
	}
}

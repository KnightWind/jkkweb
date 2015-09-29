package com.jkkp.modules.system.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.modules.system.service.IAdminMenuService;

@Controller
@RequestMapping(value = "/admin")
public class AdminLoginController extends BaseController {
	@Autowired
	private IAdminMenuService adminMenuService;

	@AccessMenu
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request) {
//		System.out.println("the real path is " + request.getSession().getServletContext().getRealPath("/"));
		return "/admin/index";
	}

	@ResponseBody
	@RequestMapping(value = "/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		Cookie cookies[] = request.getCookies();
		Cookie cookie = null;
		for (int i = 0; i < cookies.length; i++) {
			cookie = cookies[i];
			cookie.setMaxAge(0);
			cookie.setPath("/");
			response.addCookie(cookie);
		}
		response.sendRedirect(request.getContextPath() + "/admin/index.xhtml");
	}

	@RequestMapping(value = "/head")
	public String head() {
		return "/admin/include/head";
	}
}

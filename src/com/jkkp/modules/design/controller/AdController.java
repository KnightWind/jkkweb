package com.jkkp.modules.design.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.modules.design.model.Ad;
import com.jkkp.modules.design.service.impl.AdServiceImpl;
import com.jkkp.utils.CommonUtil;
@Controller
@RequestMapping(value = "/de")
public class AdController extends BaseController {
	@Autowired
	private AdServiceImpl adServiceImpl;
	@AccessMenu
	@RequestMapping(value = "/index")
	public String list(HttpServletRequest request) {
		request.setAttribute("pagination",adServiceImpl.findPagination(null));
		request.setAttribute("mid",CommonUtil.stringToInteger(request.getParameter("mid")));
		request.setAttribute("pid",CommonUtil.stringToInteger(request.getParameter("pid")));
		return "/design/ad";
	}
	@AccessMenu
	@RequestMapping(value = "/update")
	public String lit(HttpServletRequest request,Ad ad) {
		adServiceImpl.saveUpdate(ad);
		request.setAttribute("pagination",adServiceImpl.findPagination(null));
		request.setAttribute("mid",CommonUtil.stringToInteger(request.getParameter("mid")));
		request.setAttribute("pid",CommonUtil.stringToInteger(request.getParameter("pid")));
		return "/design/ad";
	}
}

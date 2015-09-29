package com.jkkp.modules.design.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.modules.basedata.service.IAreaDomainService;
import com.jkkp.modules.design.model.DesignRecommand;
import com.jkkp.modules.design.service.impl.DesignRecommandServiceImpl;
import com.jkkp.utils.CommonUtil;

@Controller
@RequestMapping(value = "/design/xg")
public class DesignRecommandController extends BaseController {
	@Autowired
	private DesignRecommandServiceImpl designRecommandServiceImpl;
	@Autowired
	private IAreaDomainService areaDomainService;
	@AccessMenu
	@RequestMapping(value = "/index")
	public String list(HttpServletRequest request) {
		request.setAttribute("lst", areaDomainService.finAll());
		request.setAttribute("mid",CommonUtil.stringToInteger(request.getParameter("mid")));
		request.setAttribute("pid",CommonUtil.stringToInteger(request.getParameter("pid")));
		return "/design/designrecommand";
	}
	@AccessMenu
	@RequestMapping(value = "/cha")
	public String in(HttpServletRequest request) {
		request.setAttribute("lst", areaDomainService.finAll());
		request.setAttribute("index",designRecommandServiceImpl.fin(request.getParameter("city")));
		request.setAttribute("mid",CommonUtil.stringToInteger(request.getParameter("mid")));
		request.setAttribute("pid",CommonUtil.stringToInteger(request.getParameter("pid")));
		request.setAttribute("city", request.getParameter("city"));
		return "/design/designrecommand_list";
	}
	@AccessMenu
	@RequestMapping(value = "/bn")
	public String lin(HttpServletRequest request) {
		Integer itemId=CommonUtil.stringToInteger(request.getParameter("id"));
		Integer id=CommonUtil.stringToInteger(request.getParameter("did"));
		DesignRecommand designRecommand=designRecommandServiceImpl.findById(id);
		designRecommand.setDesignId(itemId);
		designRecommandServiceImpl.update(designRecommand);
		request.setAttribute("lst", areaDomainService.finAll());
		request.setAttribute("index",designRecommandServiceImpl.fin(request.getParameter("city")));
		request.setAttribute("mid",CommonUtil.stringToInteger(request.getParameter("mid")));
		request.setAttribute("pid",CommonUtil.stringToInteger(request.getParameter("pid")));
		return "/design/designrecommand_list";
	}
}

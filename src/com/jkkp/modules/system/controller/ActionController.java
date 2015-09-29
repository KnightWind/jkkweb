package com.jkkp.modules.system.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.modules.system.model.Action;
import com.jkkp.modules.system.service.IActionService;
import com.jkkp.utils.CommonUtil;

@Controller
@RequestMapping("/admin/action")
public class ActionController extends BaseController {
	@Autowired
	private IActionService actionService;
	
	@AccessMenu
	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		request.setAttribute("dataList", actionService.findDataList());
		request.setAttribute("operation", actionService.findParentList());
		return "/admin/action-list";
	}

	@ResponseBody
	@RequestMapping("/save.do")
	public Object save(HttpServletRequest request) {
		try {
			Action action = new Action();
			action.setId(CommonUtil.stringToInteger(request.getParameter("id")));
			action.setName(request.getParameter("name"));
			action.setLink(request.getParameter("link"));
			actionService.saveOrUpdate(action);
			return new ResponseObject(true);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseObject("保存失败");
		}

	}

	@ResponseBody
	@RequestMapping("/remove.do")
	public Object remove(HttpServletRequest request) {
		try {
			Integer id = CommonUtil.stringToInteger(request.getParameter("id"));
			actionService.remove( id);
			return new ResponseObject("保存失败");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseObject("保存失败");
		}
	}
}

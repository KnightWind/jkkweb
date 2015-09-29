package com.jkkp.modules.system.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.modules.system.model.AdminMenu;
import com.jkkp.modules.system.service.IAdminMenuService;
import com.jkkp.utils.CommonUtil;

@Controller
@RequestMapping(value = "/admin/menu")
public class AdminMenuController extends BaseController {
	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private IAdminMenuService adminMenuService;

	@AccessMenu
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request) {
		request.setAttribute("menuList", adminMenuService.findMenuList(0));
		return "/admin/menu";
	}

	@ResponseBody
	@RequestMapping(value = "/save.do")
	public ResponseObject saveMenu(HttpServletRequest request) {
		try {
			AdminMenu menu = new AdminMenu();
			Integer id = CommonUtil.stringToInteger(request.getParameter("id"));
			if (id == null || id == 0) {
				menu.setPid(CommonUtil.isNull(CommonUtil.stringToInteger(request.getParameter("pid")), 0));
			} else {
				menu.setId(CommonUtil.stringToInteger(request.getParameter("id")));
			}
			menu.setName(request.getParameter("name"));
			menu.setLink(request.getParameter("link"));
			menu.setIcon(request.getParameter("icon"));
			adminMenuService.saveOrUpdate(menu);
			return new ResponseObject(true, "保存菜单成功", menu);
		} catch (Exception e) {
			logger.error("保存菜单出现异常", e);
			return new ResponseObject("保存菜单出现异常");
		}
	}

	@ResponseBody
	@RequestMapping(value = "/remove.do")
	public ResponseObject removeMenu(HttpServletRequest request, @RequestParam Integer id) {
		try {
			adminMenuService.removeMenu(id);
			return new ResponseObject(true, "删除菜单成功");
		} catch (Exception e) {
			logger.error("删除菜单出现异常", e);
			return new ResponseObject("删除菜单出现异常");
		}
	}

}

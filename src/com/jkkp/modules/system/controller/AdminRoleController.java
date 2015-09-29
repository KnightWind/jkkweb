package com.jkkp.modules.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.modules.system.service.IAdminMenuService;
import com.jkkp.modules.system.service.IRoleService;
import com.jkkp.modules.system.view.VRole;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping("/admin/role")
public class AdminRoleController extends BaseController {
	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private IRoleService roleService;

	@Autowired
	private IAdminMenuService adminMenuService;

	// 获取跟单员
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/admin")
	public String userList(HttpServletRequest request) {
		request.setAttribute("pagination", roleService.pagination());
		return "/appointment/appointmentAdmin_list";
	}

	@ResponseBody
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_YES)
	@RequestMapping("/pagination.do")
	public void pagination(HttpServletRequest request) {
		Pagination.setContext(roleService);
	}

	// 跟单员预约转移
	@AccessMenu
	@RequestMapping("/move")
	public String move(HttpServletRequest request) {
		List<VRole> list = roleService.selectAllGD();
		int id = CommonUtil.stringToInteger(request.getParameter("id"));
		request.setAttribute("adminList", list);
		request.setAttribute("adminId", id);
		return "/appointment/appointmentAdmin_move";
	}

	@AccessMenu
	@RequestMapping("/list")
	public String roleList(HttpServletRequest request) {
		request.setAttribute("pagination", roleService.findPagination(null));
		return "/admin/role";
	}

	@ResponseBody
	@RequestMapping("/save.do")
	public Object save(HttpServletRequest request) {
		try {
			Integer id = CommonUtil.stringToInteger(request.getParameter("id"));
			String name = request.getParameter("name");
			roleService.saveOrUpdate(id, name);
			return new ResponseObject(true, "保存角色成功！");
		} catch (Exception e) {
			logger.error("保存角色出现异常！", e);
			return new ResponseObject("保存角色出现异常！");
		}
	}

	@ResponseBody
	@RequestMapping("/remove.do")
	public Object remove(HttpServletRequest request, @RequestParam Integer id) {
		try {
			roleService.delete(id);
			return new ResponseObject(true, "删除角色成功！");
		} catch (Exception e) {
			logger.error("删除角色出现异常！", e);
			return new ResponseObject("删除角色出现异常！");
		}
	}

	@AccessMenu
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request) {
		Integer id = CommonUtil.stringToInteger(request.getParameter("id"));
		if (id != null && id > 0) {
			request.setAttribute("view", roleService.findById(id));
			request.setAttribute("datalist", adminMenuService.findSelectedMenuList(id));
		}
		return "/admin/role_edit";
	}

	@ResponseBody
	@RequestMapping("/saveRole.do")
	public Object saveRole(HttpServletRequest request) {
		try {
			Integer id = CommonUtil.stringToInteger(request.getParameter("id"));
			String name = request.getParameter("name");
			roleService.saveOrUpdate(id, name);
			Integer[] keys = CommonUtil.stringToIntegerArray(request.getParameter("keys"));
			adminMenuService.saveRoleMenu(id, keys);
			return new ResponseObject(true, "保存角色成功！");
		} catch (Exception e) {
			logger.error("保存角色出现异常！", e);
			return new ResponseObject("保存角色出现异常！");
		}
	}
}

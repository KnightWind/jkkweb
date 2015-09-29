package com.jkkp.modules.system.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.CommonResult;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.modules.system.model.Admin;
import com.jkkp.modules.system.service.IAdminService;
import com.jkkp.modules.system.service.IRoleService;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping(value = "/admin/user")
public class AdminController extends BaseController {

	@Autowired
	private IAdminService adminService;
	@Autowired
	private IRoleService roleService;
	
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String userList(HttpServletRequest request) {
		request.setAttribute("roleList", roleService.select(null));
		request.setAttribute("pagination", adminService.pagination());
		return "admin/user_list";
	}

	@ResponseBody
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_YES)
	@RequestMapping("/pagination.do")
	public void pagination(HttpServletRequest request) {
		Pagination.setContext(adminService);
	}

	@ResponseBody
	@RequestMapping(value = "/save.do")
	public Object updateOne(HttpServletRequest request, Admin admin) {
		try {
			CommonResult<String> result = adminService.checkAdmin(admin);
			if (!result.isSuccess()) {
				return new ResponseObject(result.getResult());
			}
			adminService.saveOrUpdate(admin);
			return new ResponseObject(true, "保存管理员成功！");
		} catch (Exception e) {
			logger.error("保存管理员出现异常！", e);
			return new ResponseObject("保存管理员失败！");
		}
	}
	
	@ResponseBody
	@RequestMapping("/remove.do")
	public Object remove(HttpServletRequest request) {
		Integer id = CommonUtil.stringToInteger(request.getParameter("id"));
		try {
			adminService.deleteAdmin(id);
			return new ResponseObject(true, "删除管理员成功！");
		} catch (Exception e) {
			logger.error("删除管理员失败", e);
			return new ResponseObject("删除管理员失败！");
		}
	}
}

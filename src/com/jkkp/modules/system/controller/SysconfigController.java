package com.jkkp.modules.system.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.modules.system.model.Sysconfig;
import com.jkkp.modules.system.service.impl.SysconfigServiceImpl;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pagination;
@Controller
@RequestMapping("/admin/sy")
public class SysconfigController  extends BaseController {
	@Autowired
	private SysconfigServiceImpl sysconfigServiceImpl;
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String userList(HttpServletRequest request) {
		Pagination.setIsConvert();
		request.setAttribute("pagination", sysconfigServiceImpl.pagination());
		request.setAttribute("mid",CommonUtil.stringToInteger(request.getParameter("mid")));
		request.setAttribute("pid",CommonUtil.stringToInteger(request.getParameter("pid")));
		return "admin/sysconfig_list";
	}
	@ResponseBody
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_YES)
	@RequestMapping("/pagination.do")
	public void pagination(HttpServletRequest request) {
		Pagination.setIsConvert();
		Pagination.setContext(sysconfigServiceImpl);
	}
	@AccessMenu
	@RequestMapping(value = "/up")
	public String updateOne(HttpServletRequest request,Sysconfig sysconfig) {
		sysconfigServiceImpl.saveOrUpdate(sysconfig);
		Integer mid=CommonUtil.stringToInteger(request.getParameter("mid"));
		Integer pid=CommonUtil.stringToInteger(request.getParameter("pid"));
	    return "redirect:index.xhtml?mid="+mid+"&pid="+pid+"";
	}
	@AccessMenu
	@RequestMapping(value = "/sa")
	public String update(HttpServletRequest request) {
			if(request.getParameter("id")!=null&&request.getParameter("id")!=""){
				Sysconfig sysconfig=sysconfigServiceImpl.findById(CommonUtil.stringToInteger(request.getParameter("id")));
				request.setAttribute("sys",sysconfig);
			}
			return "admin/sysconfig_edit";
	}
	@ResponseBody
	@RequestMapping("/remove.do")
	public Object remove(HttpServletRequest request) {
		Integer id = CommonUtil.stringToInteger(request.getParameter("id"));
		try {
			sysconfigServiceImpl.deleteById(id);
			return new ResponseObject(true, "删除成功！");
		} catch (Exception e) {
			logger.error("删除失败", e);
			return new ResponseObject("删除失败！");
		}
	}
}

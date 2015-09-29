package com.jkkp.modules.basedata.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.modules.basedata.model.AreaDomain;
import com.jkkp.modules.basedata.service.IAreaDomainService;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping(value = "/admin/area")
public class AreaDomainController extends BaseController {

	@Autowired
	private IAreaDomainService areaDomainService;

	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String userList(HttpServletRequest request) {
		Pagination.setIsConvert();
		request.setAttribute("lst", areaDomainService.finAll());
		request.setAttribute("pagination", areaDomainService.pagination());
		return "admin/area-list";
	}

	@ResponseBody
	
	@RequestMapping(value = "/operate.do")
	public Object operate(HttpServletRequest request) {
		try {
			Integer id = CommonUtil.stringToInteger(request.getParameter("id"));
			boolean isOpen = "1".equals(request.getParameter("isopen"));
			return new ResponseObject(areaDomainService.operate(id, isOpen));
		} catch (Exception e) {
			logger.error("操作失败", e);
			return new ResponseObject("操作失败");
		}
	}
	@ResponseBody
	@RequestMapping(value = "/sheng.do")
	public Object finBy(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String name=request.getParameter("province");
		List<AreaDomain> list=areaDomainService.finName(name);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		return map;
	}
	//根据域名去查对应的省份
			@ResponseBody
			@RequestMapping(value = "/city.do")
			public Object finByName(HttpServletRequest request,HttpServletResponse response) throws IOException {
				AreaDomain areaDomain=new AreaDomain();
				areaDomain.setProvinceDomain(request.getParameter("city"));
				List<AreaDomain> list=areaDomainService.select(areaDomain);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("list", list);
				return map;
			}
			
			@ResponseBody
			@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_YES)
			@RequestMapping("/pagination.do")
			public void pagination(HttpServletRequest request) {
				Pagination.setIsConvert();
				Pagination.setContext(areaDomainService);
			}
}
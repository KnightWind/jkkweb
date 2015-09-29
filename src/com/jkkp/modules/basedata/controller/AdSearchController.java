package com.jkkp.modules.basedata.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.modules.basedata.model.AdSearch;
import com.jkkp.modules.basedata.service.impl.AdSearchServiceImpl;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping(value = "/appointment/ad")
public class AdSearchController extends BaseController{
	@Autowired
	private AdSearchServiceImpl adSearchServiceImpl;
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String list(HttpServletRequest request) {
		Pagination.setIsConvert();
		request.setAttribute("pagination",adSearchServiceImpl.pagination());
		request.setAttribute("mid",CommonUtil.stringToInteger(request.getParameter("mid")));
		request.setAttribute("pid",CommonUtil.stringToInteger(request.getParameter("pid")));
		return "/appointment/adsearch_list";
	}
	@ResponseBody
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_YES)
	@RequestMapping("/pagination.do")
	public void pagination(HttpServletRequest request) {
		Pagination.setIsConvert();
		Pagination.setContext(adSearchServiceImpl);
	}
	@ResponseBody
	@RequestMapping(value = "/save.do")
	public ResponseObject add(HttpServletRequest request,AdSearch adSearch) {
		try {
			adSearchServiceImpl.saveOrUpdate(adSearch,request.getParameter("aname"));	
			return new ResponseObject(true, "保存成功!");
		} catch (Exception e) {
			logger.error("保存出现异常", e);
			return new ResponseObject("保存失败!");
		}finally{
			
		}
	}
	@ResponseBody
	@RequestMapping(value = "/oper.do")
	public Object operate(HttpServletRequest request) {
		try {
			Integer id = CommonUtil.stringToInteger(request.getParameter("id"));
			boolean isOpen = "0".equals(request.getParameter("isopen"));
			return new ResponseObject(adSearchServiceImpl.operate(id, isOpen));
		} catch (Exception e) {
			logger.error("操作失败", e);
			return new ResponseObject("操作失败");
		}
		finally{
			
		}
	}
	@AccessMenu
	@RequestMapping("/add")
	public String add(HttpServletRequest request) {
		return "/appointment/adsearch_add";
	}
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request) {
		 Map<String, Object> params = new HashMap<String, Object>();
			params.put("id", CommonUtil.stringToInteger(request.getParameter("id")));
			Pagination.setSearchParams(params);
			request.setAttribute("pagination",adSearchServiceImpl.pagination());
		return "/appointment/adsearch_edit";
	}
}

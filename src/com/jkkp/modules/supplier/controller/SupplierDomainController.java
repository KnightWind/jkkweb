package com.jkkp.modules.supplier.controller;



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
import com.jkkp.modules.supplier.service.impl.SupplierDomainServiceImpl;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping(value = "/supplier/dc")
public class SupplierDomainController extends BaseController{
	@Autowired
	private SupplierDomainServiceImpl serviceImpl;
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String list(HttpServletRequest request) {
		Pagination.setIsConvert();
		request.setAttribute("pagination",serviceImpl.pagination());
		return "/supplier/supplierdomain_list";
	}
	@ResponseBody
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_YES)
	@RequestMapping("/pagination.do")
	public void pagination(HttpServletRequest request) {
		Pagination.setIsConvert();
		Pagination.setContext(serviceImpl);
	}
	@ResponseBody
	@RequestMapping(value = "/remove.do")
	public ResponseObject remove(HttpServletRequest request, @RequestParam Integer id) {
		try {
			serviceImpl.deleteById(id);
			return new ResponseObject(true, "删除成功!");
		} catch (Exception e) {
			logger.error("删除出现异常", e);
			return new ResponseObject("删除失败!");
		} finally {

		}
	}
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request, @RequestParam String id,@RequestParam String yu, @RequestParam String did) {
			Integer dd=CommonUtil.stringToInteger(id);
			Integer pp=CommonUtil.stringToInteger(did);
			Pagination.setIsConvert();
			request.setAttribute("mid",request.getParameter("mid"));
			request.setAttribute("pid",request.getParameter("pid"));
			serviceImpl.add(dd, yu,pp);
			request.setAttribute("pagination",serviceImpl.pagination());
			return "/supplier/supplierdomain_list";
	}
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/lst")
	public String lst(HttpServletRequest request) {
		Pagination.setIsConvert();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("splid", CommonUtil.stringToInteger(request.getParameter("splid")));
		Pagination.setSearchParams(params);
		request.setAttribute("yu",request.getParameter("yu"));
		request.setAttribute("mid",request.getParameter("mid"));
		request.setAttribute("pid",request.getParameter("pid"));
		request.setAttribute("did",request.getParameter("did"));
		request.setAttribute("pagination",serviceImpl.pagination());
		return "/supplier/supplierdomaindeie_list";
	}
	
}

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
import com.jkkp.modules.supplier.service.impl.SupplierProxyServiceImpl;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping(value = "/supplier/sp")
public class SupplierProxyController extends BaseController{
	@Autowired
	private SupplierProxyServiceImpl supplierProxyServiceImpl;
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String list(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("checkStatus", CommonUtil.stringToInteger(request.getParameter("checkStatus")));
		Pagination.setSearchParams(params);
		Pagination.setIsConvert();
		request.setAttribute("checkStatus",CommonUtil.stringToInteger(request.getParameter("checkStatus")));
		request.setAttribute("pagination",supplierProxyServiceImpl.pagination());
		return "/supplier/supplierproxy_list";
	}
	@ResponseBody
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_YES)
	@RequestMapping("/pagination.do")
	public void pagination(HttpServletRequest request) {
		Pagination.setIsConvert();
		Pagination.setContext(supplierProxyServiceImpl);
	}
	@ResponseBody
	@RequestMapping(value = "/remove.do")
	public ResponseObject remove(HttpServletRequest request, @RequestParam Integer id) {
		try {
			supplierProxyServiceImpl.deleteById(id);
			return new ResponseObject(true, "删除成功!");
		} catch (Exception e) {
			logger.error("删除出现异常", e);
			return new ResponseObject("删除失败!");
		} finally {

		}
	}
}

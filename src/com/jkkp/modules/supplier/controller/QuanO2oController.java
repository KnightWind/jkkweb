package com.jkkp.modules.supplier.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.basedata.service.IAreaDomainService;
import com.jkkp.modules.supplier.mapper.QuanO2oMapper;
import com.jkkp.modules.supplier.service.IQuanO2oService;
import com.jkkp.modules.supplier.service.IQuanService;
import com.jkkp.modules.supplier.view.VSupplierUser;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping("/quanO2o/")
public class QuanO2oController extends BaseController {

	@Autowired
	private IQuanService quanService;
	@Autowired
	private IQuanO2oService quanO2oService;
	@Autowired
	private IAreaDomainService areaDomainService;
	@Autowired
	private QuanO2oMapper quanO2oMapper;

	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		Pagination.setIsConvert();
		request.setAttribute("lst",areaDomainService.finAll());
		request.setAttribute("pagination",quanO2oService.pagination());
		return "/quan/quan2_list";
	}

	@ResponseBody
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_YES)
	@RequestMapping("/pagination.do")
	public void pagination(HttpServletRequest request) {
		Pagination.setIsConvert();
		Pagination.setContext(quanO2oService);
	}
	
	/**
	 * 商家o2o消纳券列表
	 */
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/supplierO2OQuan_list")
	public String supplierO2OList(HttpServletRequest request) {
		VSupplierUser su = (VSupplierUser ) request.getSession().getAttribute("su");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sId",su.getSpId());
		Pagination.setSearchParams(params);
		Pagination.setPageParams(request, quanO2oMapper, "supplierQuanList",
				"supplierQuanCount");
		request.setAttribute("pagination", quanService.paginationCustom(params));
		request.setAttribute("lst", areaDomainService.finAll());
		return "/supplier/supplier_quan_list";
	}
	/**
	 * 商家o2o消纳券列表
	 */
	@ResponseBody
	@RequestMapping("/supplierO2OPagination.do")
	public Object supplierO2OPagination(HttpServletRequest request) {
		VSupplierUser su = (VSupplierUser ) request.getSession().getAttribute("su");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sId",su.getSpId());
		Pagination.setSearchParams(params);
		Pagination.setPageParams(request, quanO2oMapper, "supplierQuanList",
				"supplierQuanCount");
		return new ResponsePagination(quanService.paginationCustom(params));
	}
	
	

	@AccessMenu
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request) {
		Integer id = CommonUtil.stringToInteger(request.getParameter("qid"));
			request.setAttribute("view", quanService.findById(id));
			request.setAttribute("num",request.getParameter("num"));
			request.setAttribute("city",request.getParameter("city"));
		return "/quan/quan_edit";
	}

	@AccessMenu
	@RequestMapping("/add")
	public String add(HttpServletRequest request) {
		request.setAttribute("lst", areaDomainService.finAll());
		return "/quan/quanO2o_add";
	}
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping("/list")
	public String list(HttpServletRequest request) {
		Pagination.setIsConvert();
		request.setAttribute("lst",areaDomainService.finAll());
		request.setAttribute("pagination",quanO2oService.pagination());
		return "/quan/quanO2o_list";
	}
}

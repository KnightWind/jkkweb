package com.jkkp.pc.supervisor.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;

import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.basedata.service.impl.AttachmentServiceImpl;
import com.jkkp.modules.supplier.mapper.SupplierMapper;
import com.jkkp.pc.main.service.IRegionService;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping("/main/supervisorPC")
public class SupervisorPcController extends BaseController {

	@Autowired
	private IRegionService regionService;
	@Autowired
	private SupplierMapper supplierMapper;
	@Autowired
	private AttachmentServiceImpl impl;
	
	
	
	@RequestMapping("index")
	public String index(HttpServletRequest request){
		//预约面板省列表
		request.setAttribute("parentRegions", regionService.getParentRegions());
		//监理列表
		Pagination.setPageParams(request, supplierMapper,"selectAllJLSuppliers", "selectAllJLSuppliersCount");
		request.setAttribute("pagination",regionService.paginationCustom());
		request.setAttribute("basePath", impl.getAccessPath());
		return "/pc/supervisor/supervisor_list";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/pagination.do")
	public Object pagination(HttpServletRequest request) {
		Pagination.setPageParams(request, supplierMapper,
				"selectAllJLSuppliers", "selectAllJLSuppliersCount");
		return new ResponsePagination(
				regionService.paginationCustom());
	}
	
}

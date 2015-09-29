package com.jkkp.pc.business.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessPagination;

import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.basedata.service.impl.AttachmentServiceImpl;
import com.jkkp.modules.supplier.mapper.SupplierMapper;
import com.jkkp.pc.business.service.IBusinessService;
import com.jkkp.pc.business.view.VBusiness;
import com.jkkp.pc.cases.service.ICasesService;
import com.jkkp.pc.cases.view.VCases;
import com.jkkp.pc.main.service.IRegionService;
import com.jkkp.pc.staff.service.IStaffService;
import com.jkkp.pc.staff.view.VStaff;
import com.jkkp.utils.CheckedUtil;
import com.jkkp.utils.Pagination;

@RequestMapping("/main/businessPC")
@Controller
public class BusinessController extends BaseController {

	@SuppressWarnings("unused")
	private final static Logger log = LoggerFactory.getLogger(BusinessController.class);
	
	@Autowired
	private IBusinessService businessService;
	@Autowired
	private IStaffService 	 staffService;
	@Autowired
	private ICasesService 	 casesService;
	@Autowired
	private SupplierMapper supplierMapper;
	@Autowired
	private IRegionService regionService;
	@Autowired
	private AttachmentServiceImpl impl;
	
	
	@RequestMapping("/details")
	public String details(HttpServletRequest request,Integer id){
		if(CheckedUtil.isNotEmpty(id)){
			
			VBusiness vb = businessService.selectBusiness(id);
			vb.setIntro(CheckedUtil.splitString(vb.getIntro(), 186));
			request.setAttribute("vb", vb);
			
			List<VStaff> staffList = staffService.query4Staff(id);
			request.setAttribute("staffList", staffList);
			
			List<VCases> caseList = casesService.queryXCases(id,0,3);
			request.setAttribute("caseList", caseList);
			
			List<VCases> tyList = casesService.queryXCases(id,44,4);
			List<VCases> zsList = casesService.queryXCases(id,45,4);
			List<VCases> osList = casesService.queryXCases(id,16,4);
			List<VCases> jjList = casesService.queryXCases(id,47,4);
			List<VCases> gdList = casesService.queryXCases(id,0,4);
			
 			request.setAttribute("tyList", tyList);
			request.setAttribute("zsList", zsList);
			request.setAttribute("osList", osList);
			request.setAttribute("jjList", jjList);
			request.setAttribute("gdList", gdList);
			request.setAttribute("basePath", impl.getAccessPath());
		} 
		return "pc/business/business_detail";
	}
	
	
	
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping("/index.do")
	public String index(HttpServletRequest request){
		request.setAttribute("parentRegions", regionService.getParentRegions());
		Pagination.setPageParams(request, supplierMapper,"selectBusinessList", "selectBusinessListCount");
		request.setAttribute("pagination", businessService.paginationCustom());
		request.setAttribute("basePath", impl.getAccessPath());
		return "pc/business/business_list";
	}
	
	
	/**
	 * 上下分页
	 */
	
	@ResponseBody
	@RequestMapping(value = "/pagination.do")
	public Object pagination(HttpServletRequest request) {
		Pagination.setPageParams(request, supplierMapper,"selectBusinessList", "selectBusinessListCount");
		return new ResponsePagination(businessService.paginationCustom());
	}
	
	
}

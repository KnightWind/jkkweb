package com.jkkp.modules.crowdfunding.controller;

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
import com.jkkp.common.response.ResponseObject;
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.supplier.mapper.SupplierCompanyStaffMapper;
import com.jkkp.modules.supplier.service.ISupplierCompanyStaffService;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping(value="/admin/supplierStaff")
public class AdminSupplierStaffController extends BaseController {

	@Autowired
	private ISupplierCompanyStaffService supplierCompanyStaffService;
	@Autowired
	private SupplierCompanyStaffMapper supplierCompanyStaffMapper;
	
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("status", CommonUtil.stringToInteger(request.getParameter("status")));
		Pagination.setPageParams(request, supplierCompanyStaffMapper,"adminSupplierStaff", "adminSupplierStaffCount");
		request.setAttribute("pagination",supplierCompanyStaffService.paginationCustom(map));
		request.setAttribute("status", request.getParameter("status"));
		return "/materials/admin/adminSupplierStaff";
	}

	@ResponseBody
	@RequestMapping(value = "/pagination.do")
	public Object pagination(HttpServletRequest request) {
		Pagination.setPageParams(request, supplierCompanyStaffMapper,"adminSupplierStaff", "adminSupplierStaffCount");
		return new ResponsePagination(supplierCompanyStaffService.paginationCustom());
	}
	
	@ResponseBody
	@RequestMapping(value="/peruseOne.do")
	public Object peruseOne(HttpServletRequest request){
		try {
			Integer status=CommonUtil.stringToInteger(request.getParameter("status"));
			Integer staffId=CommonUtil.stringToInteger(request.getParameter("staffId"));
			Float gainRate=CommonUtil.stringToFloat(request.getParameter("gainRate"));
			supplierCompanyStaffService.updateSupplierStaffStatus(staffId,gainRate,status);
			return new ResponseObject(true, "审核成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseObject(false, "审核出错");
		}
	
		
	}
}

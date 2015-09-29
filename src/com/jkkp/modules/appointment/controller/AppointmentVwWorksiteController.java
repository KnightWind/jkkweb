package com.jkkp.modules.appointment.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.appointment.mapper.AppointmentVwWorksiteMapper;
import com.jkkp.modules.appointment.model.AppointmentVwWorksite;
import com.jkkp.modules.appointment.service.IAppointmentVWorksiteService;
import com.jkkp.modules.supplier.view.VSupplierUser;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pagination;

@RequestMapping("/appWorksite")
@Controller
public class AppointmentVwWorksiteController {

	
	@Autowired
	private IAppointmentVWorksiteService appointmentVwWorksiteService;
	@Autowired
	private AppointmentVwWorksiteMapper appointmentVwWorksiteMapper;
	
	
	
	
	@ResponseBody
	@RequestMapping(value = "/change.do")
	public Object change(HttpServletRequest request,Integer id,Integer status) {
		try{
			AppointmentVwWorksite avw = appointmentVwWorksiteService.findById(id);
			avw.setStatus(status);
			avw.setRespondTime(new Date());
			appointmentVwWorksiteService.update(avw);
			return new ResponsePagination(true, "操作成功！");
		}catch(Exception e){
			return new ResponsePagination(false, "系统出错，请联系管理员！");
		}
	}
	
	
	
	
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/supplierPushGDIndex")
	public String supplierPushGDIndex(HttpServletRequest request) {
		VSupplierUser  su = (VSupplierUser ) request.getSession().getAttribute("su");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("spId", su.getSpId());
		params.put("status",request.getParameter("status"));
		Pagination.setPageParams(request, appointmentVwWorksiteMapper,"appointmentBuilding", "appointmentBuildingCount");
		request.setAttribute("pagination",appointmentVwWorksiteService.paginationCustom(params));
		request.setAttribute("status",CommonUtil.stringToInteger(request.getParameter("status")));
		return "/appointment/supplier_gdPush_list";
	}
	
	@ResponseBody
	@RequestMapping(value = "/supplierPushGDPagination.do")
	public Object supplierPushGDPagination(HttpServletRequest request) {
		VSupplierUser  su = (VSupplierUser ) request.getSession().getAttribute("su");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("spId",su.getSpId());
		Pagination.setPageParams(request, appointmentVwWorksiteMapper,"appointmentBuilding", "appointmentBuildingCount");
		return new ResponsePagination(appointmentVwWorksiteService.paginationCustom(params));
	}
	
	
	
}

package com.jkkp.modules.sale_theme.controller;

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
import com.jkkp.modules.appointment.mapper.AppointmentMapper;
import com.jkkp.modules.appointment.model.Appointment;
import com.jkkp.modules.appointment.service.IAppointmentService;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping("wxActivityAppointment")
public class ActivityAppointmentController extends BaseController {

     @Autowired
     private IAppointmentService appointmentService;
     @Autowired
     private AppointmentMapper appointmentMapper;
	
	
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request) {
	
		Pagination.setPageParams(request, appointmentMapper,"selectAllWXActivityAppointment", "selectAllWXActivityAppointmentCount");
		request.setAttribute("pagination",appointmentService.paginationCustom());
		return "/saleActivity/activityAppointment_list";
	}
	
	@ResponseBody
	@RequestMapping(value = "/pagination.do")
	public Object pagination(HttpServletRequest request) {
		Pagination.setPageParams(request, appointmentMapper,"selectAllWXActivityAppointment", "selectAllWXActivityAppointmentCount");
		return new ResponsePagination(appointmentService.paginationCustom());
	}

	@ResponseBody
	@RequestMapping("save")
	public Object saveActivityAppointment(Appointment bean){
		try {
			appointmentService.saveActivityAppointment(bean);
			return new ResponseObject(true, "保存成功");
		} catch (Exception e) {
             logger.error("web保存微信引流预约单出错");
             return new ResponseObject(false, "保存出错");
		}
	}
	
}

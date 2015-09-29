package com.jkkp.pc.main.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.common.BaseController;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.modules.appointment.model.Appointment;
import com.jkkp.pc.main.service.IAppointmentService;
import com.jkkp.pc.main.service.IRegionService;

@Controller
@RequestMapping("/main/mainPC")
public class mainController extends BaseController {

	@Autowired
	private IRegionService regionService;
	@Autowired
	private IAppointmentService appointmentService;

	@RequestMapping("index.do")
	public String index(HttpServletRequest request) {
		request.setAttribute("parentRegions", regionService.getParentRegions());
		return "/pc/main/index";
	}

	@RequestMapping("supervisorDetails.do")
	public String supervisorDetails(HttpServletRequest request) {
		request.setAttribute("parentRegions", regionService.getParentRegions());
		return "/pc/main/supervisor_details";
	}
	
	@ResponseBody
	@RequestMapping("child.do")
	public Object child(Integer pid) {
		if (pid != null) {
			return regionService.getChileRegionsHandle(pid);
		}
		return null;
	}

	
	//新增一pc web预约
	
	@ResponseBody
	@RequestMapping("addOne.do")
	public Object addOnePcAppointment(Appointment appointment){
		try {
			if(appointment.getMobile().isEmpty()){
				return new ResponseObject(false, "请填写预约电话");
			}
			appointmentService.saveOneAppointment(appointment);
			return new ResponseObject(true, "预约成功,我们会尽快联系您");
		} catch (Exception e) {
			logger.error("pc保存预约失败");
			return new ResponseObject(false, "预约失败,请联系客服");
		}
	}
}

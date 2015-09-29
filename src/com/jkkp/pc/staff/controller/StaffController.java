package com.jkkp.pc.staff.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.jkkp.common.BaseController;

import com.jkkp.pc.staff.service.IStaffService;
import com.jkkp.pc.staff.view.VStaff;
import com.jkkp.utils.CheckedUtil;

@RequestMapping("/staff")
@Controller
public class StaffController extends BaseController {

	@Autowired
	private IStaffService staffService;
	
	
	@RequestMapping("/query4Staff")
	public List<VStaff> query4Staff(HttpServletRequest request,@RequestParam(required=false)Integer id){
		if(CheckedUtil.isNotEmpty(id)){
			List<VStaff> list = staffService.query4Staff(id);
			return list;
		}
		return null;
		
	}
	
	
}

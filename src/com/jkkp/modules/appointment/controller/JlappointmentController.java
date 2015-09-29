package com.jkkp.modules.appointment.controller;

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
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.appointment.mapper.JlappointmentMapper;
import com.jkkp.modules.appointment.service.IJlAppointmentService;
import com.jkkp.modules.appointment.view.VJlappointment;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping("/jlappointment")
public class JlappointmentController extends BaseController {

	@Autowired
	HttpServletRequest request;
	@Autowired
	private IJlAppointmentService jlAppointmentService;
	@Autowired
	private JlappointmentMapper jlappointmentMapper;

	@AccessMenu
	@RequestMapping("view")
	public String view(@RequestParam(value = "id") Integer id) {
		VJlappointment jlappointment = jlAppointmentService
				.findJlappointmentInfoById(id);
		request.setAttribute("jlappointment", jlappointment);
		return "/appointment/jlappointment_view";
	}

	// /**
	// * 预约监理列表
	// */
	// @AccessMenu
	// @AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	// @RequestMapping("/jlappointment_list.xhtml")
	// public String jlappointmentList(HttpServletRequest request){
	//
	// Integer state =
	// CommonUtil.stringToInteger(request.getParameter("state"));
	// state = state == null ? -1 : state;
	// Map<String, Object> params = new HashMap<String, Object>();
	// params.put("status",
	// CommonUtil.stringToInteger(request.getParameter("status")));
	// params.put("state", state);
	// Pagination.setPageParams(request, jlappointmentMapper,
	// "findPage","countPage");
	// Pagination.setSearchParams(params);
	// request.setAttribute("mid",CommonUtil.stringToInteger(request.getParameter("mid")));
	// request.setAttribute("pid",CommonUtil.stringToInteger(request.getParameter("pid")));
	// request.setAttribute("state",state);
	// request.setAttribute("status",CommonUtil.stringToInteger(request.getParameter("status")));
	// request.setAttribute("lst", areaDomainService.finAll());
	// request.setAttribute("pagination",jlAppointmentService.paginationCustom(params));
	// return "/appointment/jlappintment_list";
	// }
	// /**
	// * 预约监理列表上下页
	// */
	// @ResponseBody
	// @RequestMapping("/pagination.do")
	// public Object pagination(HttpServletRequest request) {
	// Pagination.setPageParams(request, jlappointmentMapper,
	// "findPage","countPage");
	// request.setAttribute("state",
	// CommonUtil.stringToInteger(request.getParameter("state")));
	// request.setAttribute("status",
	// CommonUtil.stringToInteger(request.getParameter("status")));
	// return new ResponsePagination(jlAppointmentService.paginationCustom());
	// }

	// web管理后台@黄宇健 监理预约
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status",
				CommonUtil.stringToInteger(request.getParameter("status")));
		Pagination.setSearchParams(params);
		Pagination.setPageParams(request, jlappointmentMapper,
				"selectAllJLAppointment", "selectAllJLAppointmentCount");
		request.setAttribute("status",
				CommonUtil.stringToInteger(request.getParameter("status")));
		request.setAttribute("pagination",
				jlAppointmentService.paginationCustom());
		return "/appointment/jlappintment_list";
	}

	@ResponseBody
	@RequestMapping(value = "/managePagination.do")
	public Object managePagination(HttpServletRequest request) {
		Pagination.setPageParams(request, jlappointmentMapper,
				"selectAllJLAppointment", "selectAllJLAppointmentCount");
		return new ResponsePagination(jlAppointmentService.paginationCustom());
	}

	@ResponseBody
	@RequestMapping(value = "/close.do")
	public Object close(HttpServletRequest request){
		try {
			int id=CommonUtil.stringToInteger(request.getParameter("id"));
			jlAppointmentService.closeOneJLAppointment(id);
			return new ResponseObject(true, "关闭成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("监理预约关闭出错");
			return new ResponseObject(false, "关闭失败");
		}
	}
}

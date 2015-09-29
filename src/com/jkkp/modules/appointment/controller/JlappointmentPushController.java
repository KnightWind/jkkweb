package com.jkkp.modules.appointment.controller;

import java.util.Date;
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
import com.jkkp.modules.appointment.mapper.JlappointmentPushMapper;
import com.jkkp.modules.appointment.model.JlappointmentPush;
import com.jkkp.modules.appointment.service.IJlAppointmentPushService;
import com.jkkp.modules.supplier.view.VSupplierUser;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pagination;


@RequestMapping("jlappointmentPush")
@Controller
public class JlappointmentPushController extends BaseController {

	@Autowired
	private IJlAppointmentPushService jlappointmentPushService;
	@Autowired
	private JlappointmentPushMapper jlappointmentPushMapper;
	
	
	/**
	 * 监理应单或者放弃
	 */
	@ResponseBody
	@RequestMapping("/response.do")
	public Object examine(HttpServletRequest request,
			@RequestParam(value="id",required=false) Integer id,
			@RequestParam(value="flag",required=false) boolean flag,
			@RequestParam(value="cause",required=false) String cause) 
	{
		JlappointmentPush push  = jlappointmentPushService.findById(id);
		try {
			
			if(flag){
				push.setStatus(new Byte("10"));
				push.setSingleTime(new Date());
				jlappointmentPushService.update(push);
				return new ResponseObject(true, "您已应单成功！");
			}else{
				push.setStatus(new Byte("11"));
				push.setReason(cause);
				jlappointmentPushService.update(push);
				return new ResponseObject(true, "您已取消预约单！");
			}
		} catch (Exception e) {
			return new ResponseObject(false, "系统出错,请联系管理员 !"); 
		}
	}
	
	
	// 针对监理  用户预约推送  列表 ================================================>>
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/jlPushIndex")
	public String jlPushIndex(HttpServletRequest request) {
		VSupplierUser  su = (VSupplierUser ) request.getSession().getAttribute("su");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sId", su.getSpId());
		params.put("status",request.getParameter("status"));
		Pagination.setSearchParams(params);
		Pagination.setPageParams(request, jlappointmentPushMapper,"selectJlAppointmentPushes", "selectJlAppointmentPushCount");
		request.setAttribute("pagination",jlappointmentPushService.paginationCustom(params));
		request.setAttribute("status",CommonUtil.stringToInteger(request.getParameter("status")));
		return "/appointment/supervisor_push_list";
	}
	
	@ResponseBody
	@RequestMapping(value = "/jlPushIndexPagination.do")
	public Object jlPushIndexPagination(HttpServletRequest request) {
		VSupplierUser  su = (VSupplierUser ) request.getSession().getAttribute("su");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sId", su.getSpId());
		Pagination.setPageParams(request, jlappointmentPushMapper,"selectJlAppointmentPushes", "selectJlAppointmentPushCount");
		return new ResponsePagination(jlappointmentPushService.paginationCustom(params));
	}
	//=============================================================>>
	
	
	//web  后台
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status",
				CommonUtil.stringToInteger(request.getParameter("status")));
		Pagination.setSearchParams(params);
		Pagination.setPageParams(request, jlappointmentPushMapper,
				"selectAllJLAppointmentPush", "selectAllJLAppointmentPushCount");
		request.setAttribute("status",
				CommonUtil.stringToInteger(request.getParameter("status")));
		request.setAttribute("pagination",
				jlappointmentPushService.paginationCustom());
		return "/appointment/jlappintmentPush_list";
	}

	@ResponseBody
	@RequestMapping(value = "/pagination.do")
	public Object managePagination(HttpServletRequest request) {
		Pagination.setPageParams(request, jlappointmentPushMapper,
				"selectAllJLAppointmentPush", "selectAllJLAppointmentPushCount");
		return new ResponsePagination(jlappointmentPushService.paginationCustom());
	}
	
	//关闭预约
	@ResponseBody
	@RequestMapping(value = "/close.do")
	public Object close(HttpServletRequest request){
		try {
			int id=CommonUtil.stringToInteger(request.getParameter("id"));
			jlappointmentPushService.closeOne(id);
			return new ResponseObject(true, "关闭成功");
		} catch (Exception e) {
			logger.error("监理预约关闭失败");
			return new ResponseObject(false, "关闭失败");
		}
	}
	
	//重新推送
	@ResponseBody
	@RequestMapping(value = "/open.do")
	public Object open(HttpServletRequest request){
		try {
			int id=CommonUtil.stringToInteger(request.getParameter("id"));
			int aid=CommonUtil.stringToInteger(request.getParameter("aid"));
			jlappointmentPushService.openOne(id,aid);
			return new ResponseObject(true, "推送成功");
		} catch (Exception e) {
			logger.error("监理预约推送失败");
			return new ResponseObject(false, "推送失败");
		}
	}
}

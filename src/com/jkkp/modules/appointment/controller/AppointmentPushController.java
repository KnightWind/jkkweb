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
import com.jkkp.modules.appointment.mapper.AppointmentPushMapper;
import com.jkkp.modules.appointment.model.Appointment;
import com.jkkp.modules.appointment.model.AppointmentPush;
import com.jkkp.modules.appointment.service.IAppointmentPushService;
import com.jkkp.modules.appointment.service.IAppointmentService;
import com.jkkp.modules.supplier.view.VSupplierUser;
import com.jkkp.modules.system.constants.ConfigConstant;
import com.jkkp.modules.system.model.Sysconfig;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping("/appointmentPush")
public class AppointmentPushController extends BaseController {

	@Autowired
	private AppointmentPushMapper appointmentPushMapper;
	@Autowired
	private IAppointmentPushService appointmentPushService;
	@Autowired
	private IAppointmentService appointmentService;
	
	
	/**
	 * 装修公司取消预约单
	 * @return
	 */
	@RequestMapping("/cancel")
	public String giveUp(HttpServletRequest request,Integer id,String cause,String status){
		AppointmentPush push  = appointmentPushService.findById(id);
		push.setRandom(2);
		push.setReason(cause);
		appointmentPushService.update(push);
		return  "redirect:/appointmentPush/supplierPushIndex.xhtml?status=" + status;
	}
	
	
	/**
	 * 已量房按钮事件
	 */
	@RequestMapping("/already")
	public String already(HttpServletRequest request,
			@RequestParam(value="sate",required=false) Integer sate,
			@RequestParam(value="id",required=false) Integer id,
			@RequestParam(value="sate",required=false) String status,
			@RequestParam(value="type",required=false) Integer type) 
	{
		AppointmentPush push  = appointmentPushService.findById(id);
		try {
			push.setStatus(new Byte(sate.toString()));
			appointmentPushService.update(push);
			//return new ResponseObject(true, "已确认量房 !"); 
			return "redirect:/appointmentPush/supplierPushIndex.xhtml?type="+type+"&status=20";
		} catch (Exception e) {
			//return new ResponseObject(false, "系统错误 !"); 
			return "redirect:/appointmentPush/supplierPushIndex.xhtml?type="+type+"&status=20";
		}
	}
	
	
	
	/**
	 * 装修公司抢单
	 */
	@RequestMapping("/response")
	public String examine(HttpServletRequest request,Integer id) 
	{
		AppointmentPush push  = appointmentPushService.findById(id);
		String msg = "";
		try {
			push.setStatus(new Byte("10"));
			push.setSingleTime(new Date());
			Appointment app = appointmentService.findById(push.getAid());
			int count = CommonUtil.stringToInteger(Sysconfig.CONFIG_PARAM.get(ConfigConstant.APPOINTMENT_MAX_SUPPLIER));
			if(app.getGrabCount() != null){
				if(app.getGrabCount() < count){
					app.setGrabCount(app.getGrabCount() + 1);
				}else{
					msg = "预约单已经被抢光啦，下次下手快点！";
					request.setAttribute("msg", msg);
					return "/appointment/response_result";
				}
			}else{
				app.setGrabCount(1);
			}
			appointmentService.update(app);
			appointmentPushService.update(push);
			msg = "您已抢单成功！";
			request.setAttribute("msg", msg);
			return "/appointment/response_result";
		} catch (Exception e) {
			msg = "系统出错,请联系管理员 !";
			request.setAttribute("msg", msg);
			return "/appointment/response_result";
		}
	}
	
	// 针对商家  用户预约推送  列表 ================================================>>
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/supplierPushIndex")
	public String supplierPushIndex(HttpServletRequest request) {
		VSupplierUser  su = (VSupplierUser ) request.getSession().getAttribute("su");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sId", su.getSpId());
		String radom = request.getParameter("random");
		String status = request.getParameter("status");
		if(radom == null){
			if(status != null && status.equals("0")){
				int count = CommonUtil.stringToInteger(Sysconfig.CONFIG_PARAM.get(ConfigConstant.APPOINTMENT_MAX_SUPPLIER));
				params.put("count", count);
				params.put("status",status);
			}
		}
		params.put("random",radom);
		params.put("type",1);
		Pagination.setPageParams(request, appointmentPushMapper,"selectAllAppointmentPushes", "selectAllAppointmentPushCount");
		request.setAttribute("pagination",appointmentPushService.paginationCustom(params));
		request.setAttribute("status",CommonUtil.stringToInteger(request.getParameter("status")));
		return "/appointment/supplier_push_list";
	}
	
	@ResponseBody
	@RequestMapping(value = "/supplierPushPagination.do")
	public Object supplierPushPagination(HttpServletRequest request) {
		VSupplierUser  su = (VSupplierUser ) request.getSession().getAttribute("su");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sId", su.getSpId());
		String radom = request.getParameter("random");
		String status = request.getParameter("status");
		if(radom == null){
			if(status != null && status.equals("0")){
				int count = CommonUtil.stringToInteger(Sysconfig.CONFIG_PARAM.get(ConfigConstant.APPOINTMENT_MAX_SUPPLIER));
				params.put("count", count);
				params.put("status",status);
			}
		}
		params.put("random",radom);
		params.put("type",1);
		Pagination.setPageParams(request, appointmentPushMapper,"selectAllAppointmentPushes", "selectAllAppointmentPushCount");
		return new ResponsePagination(appointmentPushService.paginationCustom(params));
	}
	
	//商家     预约看工地
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/supplierPushGDIndex")
	public String supplierPushGDIndex(HttpServletRequest request) {
		VSupplierUser  su = (VSupplierUser ) request.getSession().getAttribute("su");
		int count = CommonUtil.stringToInteger(Sysconfig.CONFIG_PARAM.get(ConfigConstant.APPOINTMENT_MAX_SUPPLIER));
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sId", su.getSpId());
		params.put("status",request.getParameter("status"));
		params.put("type",2);
		params.put("count", count);
		Pagination.setPageParams(request, appointmentPushMapper,"selectAllAppointmentPushes", "selectAllAppointmentPushCount");
		request.setAttribute("pagination",appointmentPushService.paginationCustom(params));
		request.setAttribute("status",CommonUtil.stringToInteger(request.getParameter("status")));
		return "/appointment/supplier_gdPush_list";
	}
	
	@ResponseBody
	@RequestMapping(value = "/supplierPushGDPagination.do")
	public Object supplierPushGDPagination(HttpServletRequest request) {
		VSupplierUser  su = (VSupplierUser ) request.getSession().getAttribute("su");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sId",su.getSpId());
		params.put("type",2);
		Pagination.setPageParams(request, appointmentPushMapper,"selectAllAppointmentPushes", "selectAllAppointmentPushCount");
		return new ResponsePagination(appointmentPushService.paginationCustom(params));
	}
	
	
	//================================================================>>

	
	
	// web管理员后台 用户预约推送  列表 =========================================>>
	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String adminPushIndex(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status",CommonUtil.stringToInteger(request.getParameter("status")));
		Pagination.setSearchParams(params);
		Pagination.setPageParams(request, appointmentPushMapper,"selectAllAppointmentPushes", "selectAllAppointmentPushCount");
		request.setAttribute("pagination",appointmentPushService.paginationCustom());
		request.setAttribute("status",CommonUtil.stringToInteger(request.getParameter("status")));
		return "/appointment/appointmentpush_list";
	}

	@ResponseBody
	@RequestMapping(value = "/pagination.do")
	public Object pagination(HttpServletRequest request) {
		Pagination.setPageParams(request, appointmentPushMapper,"selectAllAppointmentPushes", "selectAllAppointmentPushCount");
		return new ResponsePagination(appointmentPushService.paginationCustom());
	}
	

	// 预约单重新推送 改变push表，appointment（status==90） 两表状态  UPDATE appointment_push SET random=0 WHERE id=${id}
	@ResponseBody
	@RequestMapping(value = "/rePush.do")
	public Object rePush(int id, int aid) {
		try {
			appointmentPushService.updateStatusTo10(id, aid);
			return new ResponseObject(true, "推送成功！");
		} catch (Exception e) {
			logger.error("预约单重新推送报错", e);
			return new ResponseObject(false, "推送失败！");
		} finally {

		}
	}

	// 关闭  UPDATE appointment_push SET random=2 WHERE id=${id}
	@ResponseBody
	@RequestMapping(value = "/close.do")
	public Object rePush(int id) {
		try {
			Boolean flag = appointmentPushService.checkPayStatus(id);
			if(flag==false){
			    return new ResponseObject(false, "此单已支付,关闭失败");
			}
			appointmentPushService.closePush(id);
			return new ResponseObject(true, "关闭成功！");
		} catch (Exception e) {
			logger.error("关闭预约单报错", e);
			return new ResponseObject(false, "关闭失败！");
		} finally {

		}
	}
}

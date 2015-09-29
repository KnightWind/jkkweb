package com.jkkp.modules.appointment.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jkkp.appapi.common.service.interfaces.IAppointmentPushSV;
import com.jkkp.appapi.common.service.interfaces.IAppointmentSV;
import com.jkkp.common.BaseController;
import com.jkkp.common.interceptor.AccessMenu;
import com.jkkp.common.interceptor.AccessPagination;
import com.jkkp.common.response.ResponseObject;
import com.jkkp.common.response.ResponsePagination;
import com.jkkp.modules.appointment.mapper.AppointmentMapper;
import com.jkkp.modules.appointment.model.AppoinCondition;
import com.jkkp.modules.appointment.model.AppoinConditionFeedback;
import com.jkkp.modules.appointment.model.Appointment;
import com.jkkp.modules.appointment.service.IAppConditionService;
import com.jkkp.modules.appointment.service.IAppoinConditionFeedbackService;
import com.jkkp.modules.appointment.service.IAppointmentService;
import com.jkkp.modules.appointment.service.impl.AppointmentPushServiceImpl;
import com.jkkp.modules.appointment.service.impl.AppointmentServiceImpl;
import com.jkkp.modules.appointment.view.VAppointment;
import com.jkkp.modules.basedata.service.IAreaDomainService;
import com.jkkp.modules.basedata.view.VRegionName;
import com.jkkp.modules.supplier.view.VSupplierUser;
import com.jkkp.pc.main.service.IRegionService;
import com.jkkp.utils.CommonUtil;
import com.jkkp.utils.Pagination;

@Controller
@RequestMapping("/appointment")
public class AppointmentController extends BaseController {
	@Autowired
	private IAreaDomainService areaDomainService;
	@Autowired
	private IAppointmentService appointmentService;
	@Autowired
	private IAppointmentSV appointmentServiceApi;
	@Autowired
	private IAppointmentPushSV appointmentPushServiceApi;
	@Autowired
	private AppointmentServiceImpl appointmentServiceImpl;
	@Autowired
	private AppointmentPushServiceImpl appointmentPushServiceImpl;
	@Autowired
	private AppointmentMapper appointmentMapper;
	@Autowired
	private IAppConditionService appConditionService;
	@Autowired
	private IAppoinConditionFeedbackService appoinConditionFeedbackService;
	@Autowired
	private IRegionService regionService;

	// 20150625@黄宇健 针对最新的数据库结构做数据获取变更
	// @AccessMenu
	// @AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	// @RequestMapping("/index")
	// public String index(HttpServletRequest request) {

	// // 指针模块显示
	// Map<String, Object> params = new HashMap<String, Object>();
	// params.put("status",
	// CommonUtil.stringToInteger(request.getParameter("status")));
	// Pagination.setSearchParams(params);
	//
	// request.setAttribute("lst", areaDomainService.finAll());
	// request.setAttribute("pagination", appointmentServiceImpl.pagination());
	// return "/appointment/appointment_list";
	// }
	//
	// @ResponseBody
	// @AccessPagination(custom = true, async =
	// AccessPagination.ASYNC.ASYNC_YES)
	// @RequestMapping("/pagination.do")
	// public void pagination(HttpServletRequest request) {
	// Pagination.setIsConvert();
	// Pagination.setContext(appointmentServiceImpl);
	// }

	@ResponseBody
	@RequestMapping("/ajaxGetDate.do")
	public Object ajaxGetDate() {
		List<VAppointment> list = appointmentService.ajaxGetDate();
		return list;
	}

	@RequestMapping("/saveAppoinSay")
	public String saveAppoinSay(HttpServletRequest request,
			AppoinCondition cond, Integer mid, Integer pid)
			throws ParseException {
		if (cond.getUseId() != 7)
			cond.setUseRemark("");
		if (cond.getVmatch() != 0)
			cond.setMatchRemark("");
		if (cond.getId() != null && cond.getId() > 0) {
			cond.setCreateTime(new Date());
			appConditionService.update(cond);
		} else {
			// cond.setId(0);
			cond.setCreateTime(new Date());
			cond.setCreateUser(request.getRemoteUser());
			appConditionService.save(cond);
		}
		return "redirect:/appointment/beijingSay.xhtml?mid=" + mid + "&pid="
				+ pid;
	}

	@AccessMenu
	@RequestMapping("/sayView")
	public String sayView(HttpServletRequest request, Integer id) {
		if (id != null) {
			List<AppoinCondition> say = appConditionService.selectByProperty(
					"aid", id);
			if (say != null && say.size() > 0) {
				AppoinCondition cond = say.get(0);
				request.setAttribute("say", cond);
				List<AppoinConditionFeedback> list = appoinConditionFeedbackService
						.findByPid(cond.getId());
				request.setAttribute("feedbacks", list);
				request.setAttribute("freedbackId", cond.getId());
			}
			request.setAttribute("appointment",
					appointmentService.selectAppointmentDetail(id));
		}
		request.setAttribute("id", id);
		return "appointment/appion_say_details";
	}

	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/beijingSay")
	public String beijingSay(HttpServletRequest request, Integer mid,
			Integer pid) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (request.getParameter("status") != null) {
			params.put("status",
					CommonUtil.stringToInteger(request.getParameter("status")));
			request.setAttribute("status",
					CommonUtil.stringToInteger(request.getParameter("status")));
		}
		Pagination.setPageParams(request, appointmentMapper, "beijingSay",
				"beijingSayCount");
		request.setAttribute("pagination",
				appointmentService.paginationCustom(params));
		return "/appointment/appointment_say";
	}

	@ResponseBody
	@RequestMapping(value = "/beijingSayPage.do")
	public Object beijingSayPage(HttpServletRequest request) {
		Pagination.setPageParams(request, appointmentMapper, "beijingSay",
				"beijingSayCount");
		return new ResponsePagination(appointmentService.paginationCustom());
	}

	// 查看一预约详情
	@AccessMenu
	@RequestMapping(value = "/appointmentDetail")
	public String appointmentDetail(HttpServletRequest request, Integer id) {
        if(id!=null){
        VAppointment detail = appointmentService.selectAppointmentDetailWeb(id);
		request.setAttribute("appointment", detail);
			if(detail!=null&&detail.getRegionid()!=null){
				VRegionName regionName = regionService.selectRegionName(detail.getRegionid());
				request.setAttribute("regionInfo", regionName); 
			}
        }
		return "/appointment/appointment_detail";
	}

	/**
	 * ajax获取一条预约记录
	 * 
	 * @param id
	 */
	@ResponseBody
	@RequestMapping(value = "/getAppointment")
	public void getAppointment(HttpServletRequest request,
			HttpServletResponse response, @RequestParam Integer id)
			throws IOException {
		Appointment app = appointmentService.findById(id);
		String json = "";
		if (app != null) {
			json = JSON.toJSONString(app, SerializerFeature.WriteMapNullValue);
			// System.out.println(json);
		}
		response.setContentType("text/html; charset=utf-8");
		response.resetBuffer();
		response.getWriter().print(json);
	}

	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status",
				CommonUtil.stringToInteger(request.getParameter("status")));
		Pagination.setSearchParams(params);
		Pagination.setPageParams(request, appointmentMapper,
				"selectMemberAppointments", "selectMemberAppointmentsCount");
		request.setAttribute("status",
				CommonUtil.stringToInteger(request.getParameter("status")));
		request.setAttribute("pagination",
				appointmentService.paginationCustom());
		return "/appointment/appointment_list";
	}

	@ResponseBody
	@RequestMapping(value = "/pagination.do")
	public Object pagination(HttpServletRequest request) {
		Pagination.setPageParams(request, appointmentMapper,
				"selectMemberAppointments", "selectMemberAppointmentsCount");
		return new ResponsePagination(appointmentService.paginationCustom());
	}

	// 20150626@数据获取已改
	// @AccessMenu
	// @AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	// @RequestMapping("/list")
	// public String list(HttpServletRequest request) {
	// Map<String, Object> params = new HashMap<String, Object>();
	// params.put("status",
	// CommonUtil.stringToInteger(request.getParameter("status")));
	// Pagination.setSearchParams(params);
	// request.setAttribute("mid", request.getParameter("mid"));
	// request.setAttribute("pid", request.getParameter("pid"));
	// request.setAttribute("lst", areaDomainService.finAll());
	// request.setAttribute("pagination", appointmentServiceImpl.pagination());
	// return "/appointment/appointmentpush_list";
	// }

	@ResponseBody
	@RequestMapping(value = "/operate.do")
	public Object operate(HttpServletRequest request) {
		try {
			Integer id = CommonUtil.stringToInteger(request.getParameter("id"));
			boolean isOpen = "3".equals(request.getParameter("isopen"));
			return new ResponseObject(
					appointmentServiceImpl.operate(id, isOpen));
		} catch (Exception e) {
			logger.error("操作失败", e);
			return new ResponseObject("操作失败");
		} finally {

		}
	}

	// 关闭预约 2015-6-11针对最新appointment表 status=90做关闭处理
	@ResponseBody
	@RequestMapping(value = "/close.do")
	public Object close(HttpServletRequest request) {
		try {
			int id = CommonUtil.stringToInteger(request.getParameter("id"));
			// appointmentService.closeOneAppointment(id);
			if (appointmentServiceApi.over(id) == true) {
				return new ResponseObject(true, "关闭成功");
			} else {
				return new ResponseObject(false, "订单进行中，关闭失败");
			}
		} catch (Exception e) {
			logger.error("关闭预约失败", e);
			return new ResponseObject("关闭失败");
		}
	}

	// 跟单员预约转移
	@ResponseBody
	@RequestMapping(value = "/moveAppointment.do")
	public Object move(int oldId, int newId) {
		try {
			int result = appointmentService.moveAppointments(oldId, newId);
			if (result == 1) {
				return new ResponseObject(true, "转移成功");
			}
			return new ResponseObject("转移失败");
		} catch (Exception e) {
			logger.error("预约转移失败", e);
			return new ResponseObject("转移失败");
		}
	}

	@ResponseBody
	@RequestMapping(value = "/oper.do")
	public Object oper(HttpServletRequest request) {
		try {
			Integer id = CommonUtil.stringToInteger(request.getParameter("id"));
			boolean isOpen = "-1".equals(request.getParameter("isopen"));
			return new ResponseObject(appointmentServiceImpl.oper(id, isOpen));
		} catch (Exception e) {
			logger.error("操作失败", e);
			return new ResponseObject("操作失败");
		} finally {

		}
	}

	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping("/lst")
	public String lis(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", CommonUtil.stringToInteger(request.getParameter("id")));
		Pagination.setSearchParams(params);
		request.setAttribute("paginationq",
				appointmentPushServiceImpl.pagination());
		request.setAttribute("pagination", appointmentServiceImpl.pagination());
		request.setAttribute("status",
				CommonUtil.stringToInteger(request.getParameter("isRead")));
		return "/appointment/appointmentdeie_list";
	}

	@AccessMenu
	@AccessPagination(custom = true, async = AccessPagination.ASYNC.ASYNC_NO)
	@RequestMapping(value = "/mylist")
	public String mylist(HttpServletRequest request) {
		VSupplierUser su = (VSupplierUser) request.getSession().getAttribute(
				"su");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("state",
				CommonUtil.stringToInteger(request.getParameter("state")));
		params.put("isRead",
				CommonUtil.stringToInteger(request.getParameter("isRead")));
		params.put("sId", su.getSpId());
		Pagination.setSearchParams(params);
		Pagination.setPageParams(request, appointmentMapper,
				"selectAppointmentBySupplierId", "selectAppointmentCount");
		request.setAttribute("isRead",
				CommonUtil.stringToInteger(request.getParameter("isRead")));
		request.setAttribute("lst", areaDomainService.finAll());
		request.setAttribute("pagination",
				appointmentService.paginationCustom(params));
		return "/appointment/mylist";
	}

	@ResponseBody
	@RequestMapping(value = "/appointmentPage.do")
	public Object appointmentPage(HttpServletRequest request) {
		VSupplierUser su = (VSupplierUser) request.getSession().getAttribute(
				"su");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("isRead",
				CommonUtil.stringToInteger(request.getParameter("isRead")));
		params.put("sId", su.getSpId());
		Pagination.setSearchParams(params);
		request.setAttribute("status",
				CommonUtil.stringToInteger(request.getParameter("status")));
		request.setAttribute("isRead",
				CommonUtil.stringToInteger(request.getParameter("isRead")));
		Pagination.setPageParams(request, appointmentMapper,
				"selectAppointmentBySupplierId", "selectAppointmentCount");
		return new ResponsePagination(
				appointmentService.paginationCustom(params));
	}
}

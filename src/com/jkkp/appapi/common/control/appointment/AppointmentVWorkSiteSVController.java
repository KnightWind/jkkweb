/**
 * 
 */
package com.jkkp.appapi.common.control.appointment;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.appapi.common.filter.PaginationInterceptor;
import com.jkkp.appapi.common.jsonmap.ApiCommonUtil;
import com.jkkp.appapi.common.jsonmap.CommonJsonMap;
import com.jkkp.appapi.common.service.interfaces.IAppointmentPushSV;
import com.jkkp.appapi.common.service.interfaces.IAppointmentVWorksiteSV;
import com.jkkp.common.BaseController;
import com.jkkp.modules.appointment.model.AppointmentVwWorksite;
import com.jkkp.modules.appointment.view.VAppointmentVwWorksite;

/**
 * 预约看工地
 * @author Administrator
 *
 */
@SuppressWarnings("finally")
@Controller
@RequestMapping("/")
public class AppointmentVWorkSiteSVController  extends BaseController {
	@Autowired	
	IAppointmentPushSV appointmentPushSV;
	@Autowired	
	IAppointmentVWorksiteSV appVWorksiteSV;
	@Autowired 	
	CommonJsonMap commonJsonMap;
	
	/**
	 * 预约看工地
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/appointment_view_worksite_Add.do")
	public Object appointment_view_worksite_Add(HttpServletRequest request) {
		Map<String, Object> paramMap = null;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			//如果判断异常，则退出不做业务处理
			if(!ApiCommonUtil.dealCommonBuget(resultMap,paramMap, request))
			{				
				return -1;
			}
			//解析参数
			paramMap = commonJsonMap.setRequestMap(request);
			//业务处理
			resultMap = appVWorksiteSV.addViewWorksiteAppointment(paramMap, request, resultMap);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("doneCode", "9999");
			resultMap.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap( null,resultMap);
		}
		
	}
	
	
	
	/**
	 *  预约看工地列表查询
	 *  参数 spid
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/appointment_view_worksite_list.do")
	public Object appointment_view_worksite_list(HttpServletRequest request) {
		List<VAppointmentVwWorksite> appointmentVWorksiteList = null;

		Map<String, Object> paramsMap = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		Map<String, Object> pagination = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		String currentPage = "";

		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, paramsMap, request)) {
				return -1;
			}
			paramsMap = commonJsonMap.setRequestMap(request);
			currentPage = (String) paramsMap.get("curpage");
			pagination.put("currentPage", currentPage);

			paramsMap = PaginationInterceptor.pagination(paramsMap);
			appointmentVWorksiteList = appVWorksiteSV.queryAppointmentVWorksiteList(paramsMap);

			// 查询下一个页面数否有数据，如无则返回hastnest为false
			paramsMap = PaginationInterceptor.nextPagination(paramsMap);
			if (appVWorksiteSV.queryAppointmentVWorksiteList(paramsMap).size() > 0) {
				pagination.put("hasnext", true);
			} else {
				pagination.put("hasnext", false);
			}
			mapBusi.put("pagination", pagination);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap(setAppointment_view_list_Result(appointmentVWorksiteList), mapBusi);
		}
	}
		
	private List setAppointment_view_list_Result(List<VAppointmentVwWorksite> appointmentVWorksiteList){
		List dataList = new ArrayList();
		HashMap map = new HashMap();
		if(appointmentVWorksiteList!=null){
			for(VAppointmentVwWorksite a:appointmentVWorksiteList){
				map.put("community", a.getCommunity());
				map.put("userId", a.getUserId());
				map.put("createTime", a.getCreateTime());
				map.put("createTimeStr", a.getCreateTimeStr());
				map.put("status", a.getStatus());
				map.put("statusName", a.getStatusName());
				map.put("appointAddress", a.getAppointAddress());
				map.put("id", a.getId());
				dataList.add(map);
			}
		}
		return dataList;
	}
	
	/**
	 *  预约看工地列表查询
	 *  参数  id
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/appointment_view_worksite_detail.do")
	public Object appointment_view_worksite_detail(HttpServletRequest request) {
		VAppointmentVwWorksite appointmentVWorksite = null;

		Map<String, Object> paramsMap = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		Map<String, Object> pagination = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		String currentPage = "";

		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, paramsMap, request)) {
				return -1;
			}
			paramsMap = commonJsonMap.setRequestMap(request);
			appointmentVWorksite = appVWorksiteSV.queryAppointmentVWorksiteDetail(paramsMap);

		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(appointmentVWorksite, mapBusi);
		}
	}

	/**
	 *  预约看工地单应答
	 *  参数  id
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/appointment_view_worksite_respond.do")
	public Object appointment_view_worksite_respond(HttpServletRequest request) {
		VAppointmentVwWorksite appointmentVWorksite = null;

		Map<String, Object> paramsMap = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		Map<String, Object> pagination = new HashMap<String, Object>();
		Map<String, Object> dataMap = null;
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		String currentPage = "";

		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, paramsMap, request)) {
				return -1;
			}
			paramsMap = commonJsonMap.setRequestMap(request);
			//更新
			dataMap = appVWorksiteSV.appointmentVWorksiteRespond(paramsMap);

		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(dataMap, mapBusi);
		}
	}
	
}

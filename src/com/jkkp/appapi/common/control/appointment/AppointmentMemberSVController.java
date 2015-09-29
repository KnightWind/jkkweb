package com.jkkp.appapi.common.control.appointment;

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
import com.jkkp.appapi.common.service.interfaces.IAppointmentMemberSV;
import com.jkkp.appapi.modules.mapper.VIAppointmentMember;
import com.jkkp.appapi.modules.mapper.VIAppointmentPush;
import com.jkkp.common.BaseController;

@Controller
@RequestMapping("/")
public class AppointmentMemberSVController extends BaseController{

	@Autowired
	CommonJsonMap commonJsonMap;
	@Autowired
	IAppointmentMemberSV appointmentMemberSV;
	
	@ResponseBody
	@RequestMapping("/qry_Member_Push_detail.do")//我的需求
	public Object qryMemberDetailByMid(HttpServletRequest request) {

		List<VIAppointmentMember> appointmentMembers = null;
		List<VIAppointmentMember> appointmentMemberV1s = null;

		Map<String, Object> map = null;
		Map<String, Object> pagination= new HashMap<String, Object>();
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		String currentPage="";
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			currentPage=(String) map.get("curpage");		
			map= PaginationInterceptor.pagination( map);
			appointmentMembers=appointmentMemberSV.qryMemberDetailByMid(map);
			//查询下一个页面数否有数据，如无则返回hastnest为false
			map= PaginationInterceptor.nextPagination( map);
			
			appointmentMemberV1s=appointmentMemberSV.qryMemberDetailByMid(map);
			pagination.put("currentPage", currentPage);
			if(appointmentMemberV1s.size()>0){
				pagination.put("hasnext", true);
			}else{
				pagination.put("hasnext", false);
			}
			mapBusi.put("pagination", pagination);
			


		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap(appointmentMembers, mapBusi);
		}
	}

}

package com.jkkp.appapi.common.control.design;

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
import com.jkkp.appapi.common.service.interfaces.IAppointmentSV;
import com.jkkp.appapi.common.service.interfaces.IDesigner;
import com.jkkp.appapi.modules.mapper.Staff;
import com.jkkp.appapi.modules.mapper.VDesignCaseSV;
import com.jkkp.appapi.modules.mapper.VDesing;
import com.jkkp.common.BaseController;
import com.jkkp.utils.CommonUtil;

@Controller
@RequestMapping("/")
public class DesignerBncController extends BaseController{
	
	@Autowired
	IDesigner designer;
	@Autowired
	IAppointmentSV appointmentSV;
	
	@Autowired
	CommonJsonMap commonJsonMap;
	
	@ResponseBody
	@RequestMapping("/design_list.do")
	public Object qrySupplierCollectUpdate(HttpServletRequest request) {
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> map = null;
		List<Staff> list=null;
		List<VDesing> list2=new ArrayList<VDesing>();
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			Integer spid =CommonUtil.stringToInteger((String)map.get("spid"));
			list=designer.all(spid);
			for (Staff staff : list) {
			VDesing vDesing=new VDesing();	
			vDesing=designer.every(spid,staff.getId(),staff.getName());
			list2.add(vDesing);
			}
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());			
		} finally {
			return commonJsonMap.autoMap(list2,mapBusi); 
		}
	}
	@ResponseBody
	@RequestMapping("/case_design_detail.do")
	public Object topicAllDetail(HttpServletRequest request) {
		List<VDesignCaseSV> list = new ArrayList<VDesignCaseSV>();	
		Map<String, Object> map = null;
		Map<String, Object> data = new HashMap<String, Object>();
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
			Integer aid=CommonUtil.stringToInteger(map.get("aid").toString());
			pagination.put("currentPage",currentPage);
			pagination.put("aid",aid);
			map= PaginationInterceptor.pagination(map);
			//查询下一个页面数否有数据，如无则返回hastnest为false
			list=designer.getCaseDesign(map);
			map= PaginationInterceptor.nextPagination(map);
			if(designer.getCaseDesign(map).size()>0){
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
			return commonJsonMap.autoMap(list,mapBusi);
		}
	}
}

package com.jkkp.appapi.common.control.basedata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.appapi.common.jsonmap.ApiCommonUtil;
import com.jkkp.appapi.common.jsonmap.CommonJsonMap;
import com.jkkp.appapi.common.service.interfaces.IDesignSV;
import com.jkkp.appapi.common.service.interfaces.ILabelSV;
import com.jkkp.appapi.common.service.interfaces.ISgtopicSV;
import com.jkkp.appapi.common.service.interfaces.ISupplierCollectSV;
import com.jkkp.appapi.common.service.interfaces.ISupplierSV;
import com.jkkp.appapi.common.service.interfaces.ISystemRegulationSVService;
import com.jkkp.appapi.common.service.interfaces.VIStaffSV;
import com.jkkp.modules.basedata.model.Region;
import com.jkkp.modules.basedata.service.IAttachmentService;
import com.jkkp.modules.system.model.SystemRegulation;

@Controller
@RequestMapping("/")
public class content {

	@Autowired
	ISystemRegulationSVService iSystemRegulationSVService;
	@Autowired
	CommonJsonMap commonJsonMap;
	
	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping("/qry_content.do")//协议文本
	public Object qry_content(HttpServletRequest request) {
		List<SystemRegulation> systemRegulation;
		String contentString="";
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		List<Region> regions=null;
		
		try {
			//如果判断异常，则退出不做业务处理
			if(!ApiCommonUtil.dealCommonBuget(mapBusi,map, request))
			{				
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			int type=Integer.valueOf((String) map.get("type"));
			systemRegulation=iSystemRegulationSVService.selectByProperty("type",type);
			if(!systemRegulation.isEmpty())
				contentString=systemRegulation.get(0).getContent();
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
		} finally {
			return commonJsonMap.autoMap(contentString,mapBusi);
		}
		
	}
}

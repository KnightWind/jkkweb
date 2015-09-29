package com.jkkp.appapi.common.control.basedata;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.appapi.common.jsonmap.ApiCommonUtil;
import com.jkkp.appapi.common.jsonmap.CommonJsonMap;
import com.jkkp.modules.basedata.model.ApplicationVersion;
import com.jkkp.modules.basedata.model.CaseCollect;
import com.jkkp.modules.basedata.service.IApplicationVersionService;
import com.jkkp.modules.basedata.service.ICaseCollectService;

@Controller
@RequestMapping(value = "/basedata")
public class ApiApplicationVersionController {
	@Autowired private CommonJsonMap commonJsonMap;
	@Autowired private IApplicationVersionService applicationVersionservice;	
	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping("/checkVersion.do")
	public Object checkVersion(HttpServletRequest request) throws Exception {
		ApplicationVersion appver=null;
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			ApiCommonUtil.dealCommonBuget(mapBusi, map, request);
			map = commonJsonMap.setRequestMap(request);
			appver=applicationVersionservice.checkApplicationVersion(map);

		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			if(e.getMessage()=="parameters error")
				mapBusi.put("doneCode", "9998");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap(appver, mapBusi);
		}
		
	}
}

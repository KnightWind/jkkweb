package com.jkkp.appapi.common.control.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.appapi.common.jsonmap.ApiCommonUtil;
import com.jkkp.appapi.common.jsonmap.CommonJsonMap;
import com.jkkp.appapi.modules.mapper.Staff;
import com.jkkp.appapi.modules.mapper.VDesing;
import com.jkkp.common.BaseController;
import com.jkkp.modules.design.service.IDesignCateService;
import com.jkkp.utils.CommonUtil;
@Controller
@RequestMapping("/")
public class DesignCateApiController extends BaseController{
	@Autowired
	CommonJsonMap commonJsonMap;
	@Autowired IDesignCateService designcatesv;
	@ResponseBody
	@RequestMapping("/DesignCateFengGe_List.do")
	public Object qrySupplierCollectUpdate(HttpServletRequest request) {
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			// 如果判断异常，则退出不做业务处理
			if (!ApiCommonUtil.dealCommonBuget(mapBusi, map, request)) {
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());			
		} finally {
			return commonJsonMap.autoMap(designcatesv.findtypefengge(),mapBusi); 
		}
	}
}

package com.jkkp.appapi.common.jsonmap;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.jkkp.modules.appointment.model.Appointment;
import com.jkkp.utils.RequestUtils;

/**
 * 
 * @author 朱国忠
 *
 */
@Service("commonJsoMap")
public class CommonJsonMap {
	public Map<String, Object> autoMap(Integer os, Object object, String mess, String doneCode, String ret) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ver", "1.0");
		map.put("os", os);
		map.put("mess", mess);
		map.put("ret", ret);
		map.put("doneCode", doneCode);
		if (object == null)
			map.put("data", new HashMap<String, Object>());
		else
			map.put("data", object);
		return map;

	}

	Map<String, Object> map = new HashMap<String, Object>();

	public Map<String, Object> autoMap(Object object, Map<String, Object> mapDetail) {
		map.put("ver", "1.0");
		map.putAll(mapDetail);
		if (object == null)
			map.put("data", new HashMap<String, Object>());
		else
			map.put("data", object);
		return map;

	}

	// 把request的入参ping成map
	public Map<String, Object> setRequestMap(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map = RequestUtils.genParamMap(request);
		System.out.println("json:" + map.get("json"));
		if (map.get("json") != null && !"null".equals(map.get("json"))) {
			JSONObject jb = JSONObject.fromObject(map.get("json"));
			map.putAll((Map) jb);
		}
		return map;
	}
}

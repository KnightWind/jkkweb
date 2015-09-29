package com.jkkp.utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

/**
 * Request处理工具类
 */
public class RequestUtils {
	/**
	 * 根据request中的参数产生参数map
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, Object> genParamMap(HttpServletRequest request) {
		Enumeration<?> names = request.getParameterNames();
		Map<String, Object> paraMap = new HashMap<String, Object>();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			String values[] = request.getParameterValues(name);
			if (values.length == 1) {
				paraMap.put(name, values[0]);
			} else {
				paraMap.put(name, values);
			}
		}
		return paraMap;
	}
	
	/**
	 * 根据request中的参数产生参数map
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, Object> genParamMap(HttpServletRequest request, String property) {
		Map<String, Object> data = new HashMap<String, Object>();
		String values = request.getParameter(property);
		if (StringUtils.isNotEmpty(values)) {
			JSONObject obj = JSONObject.fromObject(values);
			for (Object key : JSONObject.fromObject(values).keySet()) {
				data.put(String.valueOf(key), obj.get(key));
			}
		}
		return data;
	}

	/**
	 * 获取请求头的ip地址
	 * 
	 * @param request
	 * @return ip地址
	 */
	public static String getRemoteAddr(HttpServletRequest request) {
		String ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null) {
			ipAddress = request.getHeader("X_FORWARDED_FOR");
			if (ipAddress == null) {
				ipAddress = request.getHeader("X-Forward-For");
				if (ipAddress == null) {
					ipAddress = request.getRemoteAddr();
				}
			}
		}
		return ipAddress;
	}

	/**
	 * 获得请求参数map并 过滤掉为空的参数
	 * @param request
	 * @return
	 */
	public static Map<String, Object> createCriteria(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();

		Map<String, Object> paraMap = genParamMap(request);
		for (String key : paraMap.keySet()) {
			Object value = paraMap.get(key);
			if (null!=value&&!value.toString().equals("")) {
				params.put(key, value);
			}
		}
		return params;
	}
	
	public static Map<String, String> createRequest(HttpServletRequest request) {
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"),"gbk");
			params.put(name, valueStr);
		}
		return params;
	}
}

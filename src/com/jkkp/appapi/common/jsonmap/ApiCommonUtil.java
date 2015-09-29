package com.jkkp.appapi.common.jsonmap;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.jkkp.modules.system.service.ISysconfigService;
import com.jkkp.utils.RequestUtils;

import net.sf.json.JSONObject;



public class ApiCommonUtil {
	
	@Autowired 
	ISysconfigService sysconfigSv;
	
	public static boolean dealCommonBuget(Map<String, Object>  mapBusi,Map<String, Object> mapDetail, HttpServletRequest request){
		try {
			mapDetail = setRequestMap(request);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("mess","报文格式错误！");
			mapBusi.put("doneCode","0001");
			return false;
		}
//		String ValidUserState = (String) request.getAttribute("ValidUserState");
//		if (!ValidUserState.contains("0000")) {
//			mapBusi.put("mess","验证失败！");
//			mapBusi.put("doneCode","0012");
//			return false;
//		}
		mapBusi.put("os",mapDetail.get("os"));
		mapBusi.put("mess","操作成功！");
		mapBusi.put("doneCode","0000");
		return true;
	}//把request的入参ping成map	
	public static boolean dealCommonJSON(JSONObject  mapBusi,Map<String, Object> mapDetail, HttpServletRequest request){
		try {
			mapDetail = setRequestMap(request);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("mess","报文格式错误！");
			mapBusi.put("doneCode","0001");
			return false;
		}
		String ValidUserState = (String) request.getAttribute("ValidUserState");
		if (!ValidUserState.contains("0000")) {
			mapBusi.put("mess","验证失败！");
			mapBusi.put("doneCode","0012");
			return false;
		}
		mapBusi.put("os",mapDetail.get("os"));
		mapBusi.put("mess","操作成功！");
		mapBusi.put("doneCode","0000");
		return true;
	}//把request的入参ping成map	
	
	public static Map<String, Object> setRequestMap(HttpServletRequest request){
		Map<String, Object> map=new HashMap<String, Object>();
		map=RequestUtils.genParamMap(request);
		System.out.println("json:"+map.get("json"));
		if(map.get("json")!=null&&!"null".equals(map.get("json"))){
			JSONObject jb = JSONObject.fromObject(map.get("json"));
			map.putAll((Map)jb);
		}
		return map;
	}
	
	public static Timestamp getTiem(){
		Timestamp time=new Timestamp(System.currentTimeMillis()); 
		return time;
	}
	
	public static String getSysConfig(String sys){
		String content="";
//		Sysconfig sysConfig =  sysconfigSv.qryContentByCode(sys);
//		if(sysConfig!=null)
//			content=sysConfig.getParaName();
		return content;
	}
}

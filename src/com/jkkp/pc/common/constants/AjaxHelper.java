package com.jkkp.pc.common.constants;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

public class AjaxHelper {

	/**
	 * 成功code标识
	 */
	public final static int SUCCESS_CODE = 0;
	/**
	 * 失败code标识
	 */
	public final static int FAIL_CODE    = 1;
	
	/**
	 * 设置code状态和消息
	 * @param paramMap
	 * @param msg  消息
	 * @param code 状态代码
	 */
	public static void setStatusAndMsg(Map<String, Object> paramMap,String msg,int code){
		paramMap.put("msg", msg);
		paramMap.put("code", code);
	}
	
	/**
	 * 根据Map集合序列化成json字符串
	 * @param response
	 * @param paramMap 序列化的对象
	 * @throws IOException
	 */
	public static void objectToJson(HttpServletResponse response,Map<String, Object> paramMap) throws IOException{
		response.setContentType("text/html; charset=utf-8");
		PrintWriter print = setEncodingAndGetWriter(response);
		print.write(JSONObject.toJSONString(paramMap));
	}
	
	/**
	 * 根据List<Object>集合序列化成json字符串
	 * @param response
	 * @param paramMap 序列化的对象
	 * @throws IOException
	 */
	public static void objectToJson(HttpServletResponse response,List<?> list) throws IOException{
		response.setContentType("text/html; charset=utf-8");
		PrintWriter print = setEncodingAndGetWriter(response);
		print.write(JSONObject.toJSONString(list));
	}
	
	/**
	 * 根据List<Object>集合序列化成json字符串
	 * @param <T>
	 * @param response
	 * @param obj 序列化的对象
	 * @throws IOException
	 */
	public static <T> void objectToJson(HttpServletResponse response,T obj) throws IOException{
		response.setContentType("text/html; charset=utf-8");
		PrintWriter print = setEncodingAndGetWriter(response);
		print.write(JSONObject.toJSONString(obj));
	}

	/**
	 * 设置response编码和获取一个io流对象PrintWriter对象
	 * @param response
	 * @return
	 * @throws IOException
	 */
	private static PrintWriter setEncodingAndGetWriter(HttpServletResponse response) throws IOException {
		PrintWriter print = response.getWriter();
		response.setCharacterEncoding("UTF-8");
		return print;
	}
	
	
	
	
}

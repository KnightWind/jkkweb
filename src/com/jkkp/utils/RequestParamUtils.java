package com.jkkp.utils;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

public class RequestParamUtils {

	/**
	 * 根据request 全部参数追加成一个字符串url地址参数
	 * @param request
	 * @return
	 */
	public static String joinRequestParams(HttpServletRequest request){
		Map<String, String[]> parMap = request.getParameterMap();
		StringBuilder sb = new StringBuilder();
		Set<String> keys = parMap.keySet();
		for (String key : keys) {
			if(parMap.containsKey(key)){
				sb.append("&");
				sb.append(key);
				sb.append("=");
			    sb.append(parMap.get(key)[0]);
			}
		}
		String paramString = sb.toString().replaceFirst("&", "?");
		return paramString;
	}
	
	/**
	 * 根据request 全部参数追加成一个字符串url地址参数
	 * @param request
	 * @param isReplace 是否需要替换第一个 '&' 为 '?'
	 * @return
	 */
	public static String joinRequestParams(HttpServletRequest request,boolean isReplace){
		Map<String, String[]> parMap = request.getParameterMap();
		StringBuilder sb = new StringBuilder();
		Set<String> keys = parMap.keySet();
		for (String key : keys) {
			if(parMap.containsKey(key)){
				sb.append("&");
				sb.append(key);
				sb.append("=");
				sb.append(parMap.get(key)[0]);
			}
		}
		if(!isReplace){
			return sb.toString();
		}
		return sb.toString().replaceFirst("&", "?");
	}
	
	/**
	 * 根据request 重定向时参(如后台mid,pid,id参数)追加成一个字符串url地址参数
	 * @param request
	 * @param list 需要追加的参数数组
	 * @return
	 */
	public static String joinRedirectParams(HttpServletRequest request,String[] list){
		Map<String, String[]> parMap = request.getParameterMap();
		StringBuilder sb = new StringBuilder();
		for (String key : list) {
			if(parMap.containsKey(key)){
				sb.append("&");
				sb.append(key);
				sb.append("=");
				sb.append(parMap.get(key)[0]);
			}
		}
		String paramString = sb.toString().replaceFirst("&", "?");
		return paramString;
	}
	
	/**
	 * 根据request 重定向时参数(如后台mid,pid,id参数)追加成一个字符串url地址参数
	 * @param request
	 * @param list 需要追加的参数数组
	 * @param isReplace 是否需要替换第一个 '&' 为 '?'
	 * @return
	 */
	public static String joinRedirectParams(HttpServletRequest request,String[] list,boolean isReplace){
		Map<String, String[]> parMap = request.getParameterMap();
		StringBuilder sb = new StringBuilder();
		for (String key : list) {
			if(parMap.containsKey(key)){
				sb.append("&");
				sb.append(key);
				sb.append("=");
				sb.append(parMap.get(key)[0]);
			}
		}
		if(!isReplace){
			return sb.toString();
		}
		return sb.toString().replaceFirst("&", "?");
	}
	
}

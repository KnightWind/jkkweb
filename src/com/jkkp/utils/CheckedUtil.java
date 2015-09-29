package com.jkkp.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CheckedUtil {

	/**
	 * Integer 类型数字不为空并且传入数组大于零则返回true
	 * @param num
	 * @return
	 */
	public static boolean isNotEmpty(Integer num){
		if(num != null && num > 0){
			return true;
		}
		return false;
	}
	
	/**
	 * Object 类型不为空则返回true
	 * @param num
	 * @return
	 */
	public static <T> boolean isNotEmpty(T obj){
		if(obj != null){
			return true;
		}
		return false;
	}
	
	
	/**
	 * List集合不为空&&集合个数大于零则返回true
	 * @param <E>
	 * @param list
	 * @return
	 */
	public static <T> boolean isNotEmpty(List<?> list){
		if(list != null && list.size() > 0){
			return true;
		}
		return false;
	}
	
	
	/**
	 * 字符串不为为null&&不为空串""则返回true
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str){
		if(str != null && str.length() > 0){
			return true;
		}
		return false;
	}
	
	/**
	 * Map集合不为为null则返回true
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(Map<String, Object> map){
		if(!map.isEmpty()){
			return true;
		}
		return false;
	}
	
	
	/**
	 * 字符串截取
	 * @param str
	 * @return
	 */
	public static String splitString(String str,int count){
		if(isNotEmpty(str) && str.length() > count){
			return str.substring(0,count) + "...";
		}
		return str;
	}
	
	/**
	 * 按指定字符将字符串分割成Integer数组
	 * @param str
	 * @return
	 */
	public static List<Integer> splitStringToArray(String string,String ext){
		try {
			if(!isNotEmpty(string))
				return null;
			if(string.indexOf(ext) == -1)
				return null;
			String strArr[] = string.split(ext);
			List<Integer> intArr = new ArrayList<Integer>();
			for (String str : strArr) {
				intArr.add(Integer.valueOf(str));
			}
			return intArr;
		} catch (Exception e) {
			return null;
		}
	}
	
	
}

package com.jkkp.utils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@SuppressWarnings("unchecked")
public class CommonUtil {

	public static <T> T isNull(T object, T defaultValue) {
		return object == null ? defaultValue : object;
	}

	public static Long stringToLong(String value) {
		if (StringUtils.isNotEmpty(value)) {
			try {
				return Long.parseLong(value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static Integer stringToInteger(String value) {
		if (StringUtils.isNotEmpty(value)) {
			try {
				return Integer.parseInt(value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static Integer[] stringToIntegerArray(String value) {
		if (StringUtils.isNotEmpty(value)) {
			String[] datas = value.split(",");
			Integer[] keys = new Integer[datas.length];
			for (int i = 0; i < datas.length; i++) {
				keys[i] = stringToInteger(datas[i]);
			}
			return keys;
		}
		return new Integer[0];
	}

	public static <T> T JsonToBean(String JsonString, Class<T> clazz) {
		return (T) JSONObject.toBean(JSONObject.fromObject(JsonString), clazz);
	}

	public static <T> List<T> jsonToList(String jsonString, Class<T> clazz) {
		return (List<T>) JSONArray.toList(JSONArray.fromObject(jsonString), clazz);
	}

	public static <E> E copyBean(Object source, Class<E> clazz) {
		try {
			E dest = clazz.newInstance();
			BeanUtils.copyProperties(dest, source);
			return dest;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static <T> void copySelective(T dest, T source) {
		if (dest == null || source == null) {
			return;
		}
		try {
			for (Field field : source.getClass().getDeclaredFields()) {
				Object value = BeanUtils.getProperty(source, field.getName());
				if (value != null) {
					BeanUtils.copyProperty(dest, field.getName(), value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public final static String md5(String string) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
        try {
            byte[] btInput = string.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

	public static void main(String[] args) {
		System.out.println(md5("a1234567"));
	}
	
	public static Float stringToFloat(String value) {
		if (StringUtils.isNotEmpty(value)) {
			try {
				return Float.parseFloat(value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static Object stringToDouble(String value) {
		if (StringUtils.isNotEmpty(value)) {
			try {
				return Double.parseDouble(value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static Double stringToDoubles(String value) {
		if (StringUtils.isNotEmpty(value)) {
			try {
				return Double.parseDouble(value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	public static void filterNullInt(Map<String, Object> params, String... strings) {
		if (strings == null || strings.length == 0) {
			return;
		}
		for (String field : strings) {
			Object value = params.get(field);
			if (value == null || "".equals(String.valueOf(value))) {
				continue;
			}
			if (!isNotNumberic(String.valueOf(value))) {
				params.put(field, "-1000");
			}
		}
	}

	private static boolean isNotNumberic(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 合并List
	 * 
	 * @param <T>
	 * @param list
	 * @param spliter
	 * @return
	 */
	public static <T> String join(List<T> list, String spliter, boolean withQuot) {
		if (list == null || list.isEmpty()) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		int i = 0;
		for (T obj : list) {
			String value = "" + obj;
			if (withQuot) {
				value = "'" + value + "'";
			}
			if (i == 0) {
				sb.append(value);
			} else {
				sb.append(spliter).append(value);
			}
			i++;
		}
		return sb.toString();
	}

	/**
	 * 合并List
	 * 
	 * @param <T>
	 * @param list
	 * @param spliter
	 * @return
	 */
	public static <T> String join(List<T> list, String spliter) {
		return join(list, spliter, false);
	}

	/**
	 * 合并List
	 * 
	 * @param <T> list
	 * @return
	 */
	public static <T> String join(List<T> list) {
		return join(list, ",", false);
	}

	public static String doubleToString(Double amount) {
		if (amount == null) {
			return "0";
		}
		return new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
	}
	
	public static Map<String, String> jsonToMap(String jsonString) {
		Map<String, String> result = new HashMap<String, String>();
		JSONObject data = JSONObject.fromObject(jsonString);
		for (Object item : data.keySet()) {
			String key = String.valueOf(item);
			result.put(key, data.getString(key));
		}
		return result;
	}
	
	/**获取随机数*/
	public static String getRandomStr(int length) {
		StringBuffer sb = new StringBuffer();
		for(int i=0; i < length; i++) {
			sb.append((int)(10 * Math.random()));
		}
		return sb.toString();
	}
}

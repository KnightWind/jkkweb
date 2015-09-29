package com.jkkp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.JSONUtils;

import org.apache.commons.lang.StringUtils;

@SuppressWarnings("unchecked")
public class JsonUtil {

	static {
		JSONUtils.getMorpherRegistry().registerMorpher(
				new DateMorpherExt(new String[] { "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", "yyyy-MM-dd't'HH:mm:ss" },
						(Date) null), true);
	}

	public static <T> T JsonToBean(String JsonString, Class<T> clazz) {
		if (!StringUtils.isNotEmpty(JsonString)) {
			return null;
		}
		return JsonToBean(JSONObject.fromObject(JsonString), clazz);
	}

	public static <T> T JsonToBean(JSONObject object, Class<T> clazz) {
		JSONObject jsonObject = object;
		T entity = (T) JSONObject.toBean(jsonObject, clazz);
		return entity;
	}

	public static <T> List<T> jsonToList(String jsonString, Class<T> clazz) {
		return (List<T>) JSONArray.toList(JSONArray.fromObject(jsonString), clazz);
	}

	public static String objectToJson(Object object) {
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		return JSONObject.fromObject(object, config).toString();
	}
}

class DateJsonValueProcessor implements JsonValueProcessor {
	private String format = "yyyy-MM-dd";

	public DateJsonValueProcessor() {
	}

	public DateJsonValueProcessor(String format) {
		this.format = format;
	}

	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		String[] obj = {};
		if (value instanceof Date[]) {
			SimpleDateFormat sf = new SimpleDateFormat(format);
			Date[] dates = (Date[]) value;
			obj = new String[dates.length];
			for (int i = 0; i < dates.length; i++) {
				obj[i] = sf.format(dates[i]);
			}
		}
		return obj;
	}

	public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
		if (value instanceof Date) {
			String str = new SimpleDateFormat(format).format((Date) value);
			return str;
		}
		String result = value == null ? "" : value.toString();
		if (result.contains("00:00:00")) {
			return result.substring(0, 10);
		} else {
			return result;
		}
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
}

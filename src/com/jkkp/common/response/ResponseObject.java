package com.jkkp.common.response;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.PropertyUtils;

public class ResponseObject {

	private static final int SUCCESS_CODE = 0; // 调用成功
	private static final int FAILURE_CODE = 1; // 调用失败
	private static final String SUCCESS_MESSAGE = "success";

	private String ret;
	private String msg;
	private Map<String, Object> data;

	public ResponseObject() {
	}

	public ResponseObject(int ret, String msg) {
		this.ret = String.valueOf(ret);
		this.msg = msg;
	}

	public ResponseObject(boolean isSuccess, String msg) {
		this.ret = String.valueOf(isSuccess ? SUCCESS_CODE : FAILURE_CODE);
		this.msg = isSuccess ? ((msg == null || msg.length() == 0) ? SUCCESS_MESSAGE : msg) : msg;
	}

	/**
	 * 失败
	 * 
	 * @param msg
	 */
	public ResponseObject(String msg) {
		this.ret = String.valueOf(FAILURE_CODE);
		this.msg = msg;
	}

	/**
	 * 成功
	 * 
	 * @param data
	 */
	public ResponseObject(boolean success) {
		this.ret = String.valueOf(success ? SUCCESS_CODE : FAILURE_CODE);
		this.msg = success ? SUCCESS_MESSAGE : "操作失败";
	}

	/**
	 * 成功
	 * 
	 * @param data
	 */
	public ResponseObject(Object data) {
		this.ret = String.valueOf(SUCCESS_CODE);
		this.msg = SUCCESS_MESSAGE;

		this.data = new HashMap<String, Object>();
		if (data != null) {
			Field[] fields = data.getClass().getDeclaredFields();
			for (Field field : fields) {
				String fieldName = field.getName();
				this.data.put(fieldName, this.getPropertyValue(data, fieldName));
			}
		}
	}
	
	public ResponseObject(boolean success, String msg, Object data) {
		this.ret = String.valueOf(success ? SUCCESS_CODE : FAILURE_CODE);
		this.msg = msg;
		this.data = new HashMap<String, Object>();
		if (data != null) {
			Field[] fields = data.getClass().getDeclaredFields();
			for (Field field : fields) {
				String fieldName = field.getName();
				this.data.put(fieldName, this.getPropertyValue(data, fieldName));
			}
		}
	}

	/**
	 * 成功
	 * 
	 * @param data
	 */
	public <T> ResponseObject(Map<String, T> data) {
		this.ret = String.valueOf(SUCCESS_CODE);
		this.msg = SUCCESS_MESSAGE;

		this.data = new HashMap<String, Object>();
		if (data != null) {
			for (String key : data.keySet()) {
				this.data.put(key, data.get(key));
			}
		}
	}

	private Object getPropertyValue(Object data, String fieldName) {
		try {
			return PropertyUtils.getProperty(data, fieldName);
		} catch (InvocationTargetException e) {
			return null;
		} catch (NoSuchMethodException e) {
			return null;
		} catch (IllegalAccessException e) {
			return null;
		}
	}

	/**
	 * 成功
	 * 
	 * @param property
	 * @param dataValue
	 */
	public ResponseObject(String property, Object dataValue) {
		this.ret = String.valueOf(SUCCESS_CODE);
		this.msg = SUCCESS_MESSAGE;
		data = new HashMap<String, Object>();
		data.put(property, dataValue);
	}

	public ResponseObject add(String property, Object dataValue) {
		if (data != null) {
			data.put(property, dataValue);
		}
		return this;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public String toString() {
		return JSONObject.fromObject(this).toString();
	}

	public String getRet() {
		return ret;
	}

	public void setRet(String ret) {
		this.ret = ret;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}

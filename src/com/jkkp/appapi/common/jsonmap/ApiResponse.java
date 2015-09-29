package com.jkkp.appapi.common.jsonmap;


public class ApiResponse<T> {

	private static final String SUCCESS_CODE = "0000";
	private static final String FAILURE_CODE = "9999";

	private static final String SUCCESS_MSG = "操作成功";
	private static final String FAILURE_MSG = "操作失败";

	private String mess;
	private String doneCode;
	private String ver = "1.0";
	private T data;

	public ApiResponse() {

	}

	public ApiResponse(boolean success) {
		this.doneCode = success ? SUCCESS_CODE : FAILURE_CODE;
		this.mess = success ?  SUCCESS_MSG : FAILURE_MSG;
	}
	
	public ApiResponse(boolean success, String message) {
		this.doneCode = success ? SUCCESS_CODE : FAILURE_CODE;
		this.mess = message;
	}

	public ApiResponse(T data) {
		this(true, SUCCESS_MSG, data);
	}

	public ApiResponse(boolean success, String message, T data) {
		this.doneCode = success ? SUCCESS_CODE : FAILURE_CODE;
		this.mess = message;
		this.data = data;
	}
	public ApiResponse( String message, String doneCode) {
		this.doneCode = doneCode;
		this.mess = message;
		this.data = data;
	}

	public String getMess() {
		return mess;
	}

	public void setMess(String mess) {
		this.mess = mess;
	}

	public String getDoneCode() {
		return doneCode;
	}

	public void setDoneCode(String doneCode) {
		this.doneCode = doneCode;
	}

	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}

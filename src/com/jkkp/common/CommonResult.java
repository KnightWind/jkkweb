package com.jkkp.common;

public class CommonResult<T> {

	private boolean success;
	private T result;
	private int field2;

	public CommonResult() {
	}

	public CommonResult(boolean success) {
		this.success = success;
	}
	
	public CommonResult(boolean success,int field2) {
		this.success = success;
		this.field2=field2;
	}

	public CommonResult(boolean success, T result) {
		this.success = success;
		this.result = result;
	}
	
	public CommonResult(boolean success, T result,int field2) {
		this.success = success;
		this.result = result;
		this.field2=field2;
	}

	public CommonResult(T result) {
		this.result = result;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public int getField2() {
		return field2;
	}

	public void setField2(int field2) {
		this.field2 = field2;
	}
}

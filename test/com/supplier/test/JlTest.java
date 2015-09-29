package com.supplier.test;

import org.junit.Test;

public class JlTest {
	@Test
	public void test(){
		String json="json={gcdId:\"1\",login_user:\"test\",login_flag:\"WhBei51A4TKXgNYuoiZdig==\",os:\"1\"}";
		//JSONObject jobj=JSONObject.fromObject(json);
		pln(json);
	}
	public void pln(Object obj){
		System.out.println(obj);
	}
}

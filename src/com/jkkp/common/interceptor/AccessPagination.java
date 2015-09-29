package com.jkkp.common.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessPagination {

	public enum ASYNC {
		ASYNC_YES, ASYNC_NO
	}

	public boolean custom() default false;

	public ASYNC async() default ASYNC.ASYNC_YES;
}

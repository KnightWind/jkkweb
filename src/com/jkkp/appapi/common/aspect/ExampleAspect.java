package com.jkkp.appapi.common.aspect;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jkkp.appapi.common.jsonmap.CommonJsonMap;


/**
 * @author Administrator
 *
 */
@Aspect
@Component
public class ExampleAspect {
	   @Autowired 
	   CommonJsonMap commonJsonMap;
	  //@Pointcut("execution(* com.jkkp.modules.design.service.impl..*.*(..))")
	  @Pointcut("execution(* com.jkkp.appapi.common.control.design..*.*(..))")
	  public void aPointcut() {
	  }
	  
	  @Before("aPointcut()")
	  public void beforeAdvice(JoinPoint jp) {
		  System.out.println(jp.getArgs());
	      System.out.println("before advice is executed!");
	  }
	  
	  @AfterReturning(pointcut = "aPointcut()", returning="r")
	  public void afterReturningAdvice(String r) {
	      if (r != null)
	          System.out.println("after returning advice is executed! returning String is : " + r);
	  }
	  
	  @After("aPointcut() && args(resultMap,request,parammap)")
	  public void AfterAdvice(Map<String, Object> resultMap,HttpServletRequest request,Map<String, Object> parammap) {
		  System.out.println("输入参数："+resultMap.toString());
		  System.out.println("输出参数："+parammap.toString());
	      System.out.println("after advice is executed!");
	  }
	  
	  @After("aPointcut() && args(str)")
	  public void AfterAdviceWithArg(String str) {
	      System.out.println("after advice with arg is executed!arg is : " + str);
	  }
	  
	  @AfterThrowing(pointcut="aPointcut()",throwing="e")
	  public void afterThrowingAdvice(Exception e) {
	      System.out.println("after throwing advice is executed!exception msg is : " + e.getMessage());
	  }
	  
	  @AfterReturning(pointcut = "aPointcut() && args(request)",returning="returnObj")
	  public void AfterAdvice(HttpServletRequest request,Object returnObj) {
		  Map paramMap = null;
		try {
			paramMap = commonJsonMap.setRequestMap(request);
			System.out.println("输入参数："+paramMap.toString());
			System.out.println("输出参数："+paramMap.toString());
			System.out.println("输出参数："+returnObj.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
	      System.out.println("after advice is executed!");
	  }

}

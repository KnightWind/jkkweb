/**
 * 
 */
package com.jkkp.appapi.common.aspect;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.log4j.chainsaw.Main;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sun.misc.BASE64Encoder;

import com.jkkp.appapi.common.jsonmap.CommonJsonMap;
import com.jkkp.modules.interfaceauth.mapper.InterfaceRecordMapper;
import com.jkkp.modules.interfaceauth.model.InterfaceRecord;
import com.jkkp.modules.interfaceauth.model.InterfaceRegister;
import com.jkkp.modules.interfaceauth.model.UserTocken;
import com.jkkp.modules.interfaceauth.service.InterfaceAuthService;
import com.jkkp.modules.interfaceauth.service.UserTockenService;

/**
 * @author Administrator
 *
 */
@Aspect
@Component
public class InterfaceAuthAspect {
	  protected Logger logger = Logger.getLogger(this.getClass());
	  @Autowired 
	  CommonJsonMap commonJsonMap;
	  
	  @Autowired
	  InterfaceRecordMapper interfaceRecordMapper;
	  @Autowired
	  UserTockenService userTockenService;
	  @Autowired
	  InterfaceAuthService interfaceAuthService;
	  
	  
	  //@Pointcut("execution(* com.jkkp.modules.design.service.impl..*.*(..))")
	  @Pointcut("execution(* com.jkkp.appapi.common.control..*.*(..))")
	  public void aPointcut() {
	  }
	  
	  /**
	   * 在这个切面 鉴权
	   * @param jp
	   */
	  @Before("aPointcut() && args(request)")
	  public void beforeAdvice(JoinPoint jp,HttpServletRequest request) {
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
	  /**
	   * 在这个切面记录日志
	   * @param request
	   * @param returnObj
	   */
	  @AfterReturning(pointcut = "aPointcut() && args(request)",returning="returnObj")
	  public void AfterAdvice(HttpServletRequest request,Object returnObj) {
			String interfaceCode = request.getServletPath().replace("/", "");
		  	Map paramMap = null;
			try {
				paramMap = commonJsonMap.setRequestMap(request);
				logger.info("输入参数："+paramMap.toString());
				if(returnObj != null)
					logger.info("输出参数："+returnObj.toString());
				else {
					logger.info("输出参数对象returnObj为空：");
				}
				//根据tocken 取到用户信息
				String tockenId = (String)paramMap.get("tocken_id");
				//获取用户session
				UserTocken userInfo = userTockenService.getSessionUserTocken(tockenId);
				//获取接口信息
				InterfaceRegister interfaceRegister = interfaceAuthService.getInterfaceRegister(interfaceCode);
				
				InterfaceRecord record = new InterfaceRecord();
				if(userInfo!=null){
					record.setTockenId(tockenId);
					record.setUserId(userInfo.getUserId());
					record.setUserType(userInfo.getUserType());
				}
				record.setInterfaceCode(interfaceCode);
				if(interfaceRegister!=null){
					record.setInterfaceName(interfaceRegister.getInterfaceName());
					record.setOperType(String.valueOf(interfaceRegister.getOperType()));
					record.setOperDesc(interfaceRegister.getOperDesc());
					record.setInputParam(subString(paramMap,500));
					
					if(returnObj != null) {
						record.setOutParam(subString(returnObj,1000));
						if(returnObj instanceof Map){
							Map returnMap = (Map)returnObj;
							record.setOperResult((String)returnMap.get("doneCode"));
							record.setOperResultDesc(subString((String)returnMap.get("mess"),300));
						}						
					}
					
					record.setOperTime(new Date());
					record.setSrcIp(request.getRemoteAddr());
					interfaceRecordMapper.insert(record);
				}else{
					logger.info("未注册的接口暂不记录日志："+interfaceCode);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("接口日志记录报错!");
			}
	      
	  }
	  
	  private String subString(Object strObj,int length){
		  if(strObj!=null){
				if(strObj.toString().length()>1000){
					return strObj.toString().substring(0,length);
				}else{
					return strObj.toString();
				}
		  }else{
			  return null;
		  }
	  }
}

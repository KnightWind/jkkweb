package com.jkkp.appapi.common.filter;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jkkp.appapi.common.jsonmap.CommonJsonMap;
import com.jkkp.modules.interfaceauth.model.InterfaceRegister;
import com.jkkp.modules.interfaceauth.model.UserTocken;
import com.jkkp.modules.interfaceauth.service.InterfaceAuthService;
import com.jkkp.modules.interfaceauth.service.UserTockenService;
import com.jkkp.utils.CommonUtil;

import sun.misc.BASE64Encoder;


/**
 * 接口是否需要登录鉴权
 * @author Administrator
 *
 */
@SuppressWarnings(value = { "unchecked" })
public class AppInterProxy extends HandlerInterceptorAdapter {
	private static boolean debug=true;
	@Autowired 
	CommonJsonMap commonJsonMap;	
	@Autowired
	InterfaceAuthService interfaceAuthService;
	@Autowired
	UserTockenService userTockenService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Map<String, Object> map=new HashMap();
		Map<String, Object> rmap=new HashMap();
		response.setContentType("text/xml; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		boolean result=false;
		try{
			map=commonJsonMap.setRequestMap(request);
			String interfaceCode = request.getServletPath().replace("/", "");
			//获取接口信息
			InterfaceRegister interfaceRegister = interfaceAuthService.getInterfaceRegister(interfaceCode);
			
			//检查md5与登录
			result = isValidUser(map,interfaceRegister,rmap);
			
			if(result){
				//检查重复提交防重复提交
				String tockenId=(String) map.get("tocken_id");
				String transactionId=(String) map.get("transaction_Id");
				result = checkRepeatedlyTrasaction(tockenId,transactionId,interfaceRegister,rmap);
			}
			//这里可以扩展其他的检查逻辑
			//...
			//检查完成
			if(result){
				return result;
			}else {
				//验证失败
				response.getWriter().write(rmap.toString());
				return false;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			//验证失败
			//request.setAttribute("ValidUserState", "9999");
			rmap.put("doneCode", "9009");
			rmap.put("mess", "权限验证出错");
			response.getWriter().write(rmap.toString());
			return false;
		}
	}
	
	/**
	 * 检查重复提交
	 * @return
	 */
	private boolean checkRepeatedlyTrasaction(String tockenId,String transactionId,InterfaceRegister interfaceRegister,Map<String, Object> rmap) {
		
		if(interfaceRegister==null){
			//未注册接口不检查重复提交
			return true;
		}
		
		String preTransactionId=null;
		//4是查询类型，不检查事物重复问题
		if(interfaceRegister.getOperType()==4){
			return true;
		}
		
		preTransactionId = userTockenService.getPreTransactionId(tockenId);
		if(transactionId==null&&"".equals(transactionId)){
			rmap.put("doneCode", "9004");
			rmap.put("mess", "transactionId不能为空");
			return false;
		}
		if(transactionId.equals(preTransactionId)){
			rmap.put("doneCode", "9005");
			rmap.put("mess", "重复提交：transactionId与上一个请求相同");
			return false;
		}
		//更新
		userTockenService.getTransactionMap().put(tockenId, transactionId);
		return true;
		
	}

	private boolean isValidUser(Map<String, Object> map,InterfaceRegister interfaceRegister,Map<String, Object> rmap) throws LoginException {
		//debug模式不校验md5与登录
		if(debug){
			return true;
		}
		boolean result=false;
		try {
			String loginUser=(String) map.get("login_user");
			String loginFlag=(String) map.get("login_flag");
			String tockenId=(String) map.get("tocken_id");
			//md5校验
			/*
			if (!EncoderByMd5(loginUser+1).equals(loginFlag)){
				rmap.put("doneCode", "9000");
				rmap.put("mess", "非法调用");
				return false;
			}*/
					
			if(interfaceRegister!=null){
				//接口要求需要登录
				if(1==interfaceRegister.getNeedLoginAuth()){
					return checkTockenId(rmap, tockenId);
				}else{
					return true;
				}
			//未注册的接口，未登录也不许访问
			}else{
				return checkTockenId(rmap, tockenId);
			}
			
		} catch (Exception e) {
			result=false;
			rmap.put("doneCode", "9009");
			rmap.put("mess", "鉴权报错");
			e.printStackTrace();
		}
		 return result;

	}

	private boolean checkTockenId(Map<String, Object> rmap, String tockenId) {
		UserTocken userInfo = userTockenService.getSessionUserTocken(tockenId);
		//已登录
		if(userInfo!=null){
			//检查重复提交
			return true;
		}else{
			rmap.put("doneCode", "9008");
			rmap.put("mess", "未登录或者tocken_id已失效");
			return false;
		}
	}
	
	public String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		BASE64Encoder base64en = new BASE64Encoder();

		String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
		return newstr;
	}

}

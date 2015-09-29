package com.jkkp.appapi.common.control.system;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.appapi.common.jsonmap.ApiCommonUtil;
import com.jkkp.appapi.common.jsonmap.CommonJsonMap;
import com.jkkp.appapi.common.jsonmap.SendMsgUtil;
import com.jkkp.common.BaseController;
import com.jkkp.modules.system.constants.ConfigConstant;
import com.jkkp.modules.system.model.Sysconfig;

@SuppressWarnings("finally")
@RequestMapping("/")
@Controller
public class MessageSVController extends BaseController{

	@Autowired
	CommonJsonMap commonJsonMap;
	
	
	@ResponseBody
	@RequestMapping("/send_buycard_success.do")
	public Object getRegisterPWDCode(HttpServletRequest request) {
		Map<String, Object> data=new HashMap<String, Object>();
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			//如果判断异常，则退出不做业务处理
			if(!ApiCommonUtil.dealCommonBuget(mapBusi,map, request))
			{				
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			
			String mobile = (String) map.get("memberMobile");
			String cardName = (String) map.get("cardName");
			String smsContent=Sysconfig.CONFIG_PARAM.get(ConfigConstant.SMS_BUY_CARD_SUCCESS);
			smsContent = smsContent.replace("card_name", cardName);
			SendMsgUtil.send(mobile,smsContent);
			data.put("smsCode", smsContent);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap( data,mapBusi);
		}

	}
	
	
	@ResponseBody
	@RequestMapping("/sendRecommendMsg.do")
	public Object sendRecommendMsg(HttpServletRequest request) {
		Map<String, Object> data=new HashMap<String, Object>();
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			//如果判断异常，则退出不做业务处理
			if(!ApiCommonUtil.dealCommonBuget(mapBusi,map, request))
			{				
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			
			String fromPhone = (String) map.get("fromPhone");
			String toPhone = (String) map.get("toPhone");
			String smsRecommend = Sysconfig.CONFIG_PARAM.get(ConfigConstant.SMS_RECOMMEND_SUCC);
			String smsRecommended = Sysconfig.CONFIG_PARAM.get(ConfigConstant.SMS_RECOMMENDED_SUCC);
			smsRecommend = smsRecommend.replace("toPhone", toPhone);
			smsRecommended = smsRecommended.replace("fromPhone", fromPhone);
			SendMsgUtil.send(fromPhone,smsRecommend);
			SendMsgUtil.send(toPhone,smsRecommended);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap( data,mapBusi);
		}

	}
	
	
	@ResponseBody
	@RequestMapping("/jiakeke_vip_msg.do")
	public Object jiakekeVipMsg(HttpServletRequest request) {
		Map<String, Object> data=new HashMap<String, Object>();
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			//如果判断异常，则退出不做业务处理
			if(!ApiCommonUtil.dealCommonBuget(mapBusi,map, request))
			{				
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			
			String toPhone = (String) map.get("toPhone");
			String content = Sysconfig.CONFIG_PARAM.get(ConfigConstant.SMS_JIAKEKE_VIP_SUCC);
			SendMsgUtil.send(toPhone,content);
			data.put("smsCode", String.valueOf(0));
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap( data,mapBusi);
		}

	}
	
	@ResponseBody
	@RequestMapping("/take_quan.do")
	public Object takeQuan(HttpServletRequest request) {
		Map<String, Object> data=new HashMap<String, Object>();
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			//如果判断异常，则退出不做业务处理
			if(!ApiCommonUtil.dealCommonBuget(mapBusi,map, request))
			{				
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			
			String toPhone = (String) map.get("toPhone");
			String price = String.valueOf(map.get("price"));
			String content = Sysconfig.CONFIG_PARAM.get(ConfigConstant.MSG_ACTIVITY_TAKE_QUAN);
			content = content.replace("price", price);
			SendMsgUtil.send(toPhone,content);
			data.put("smsCode", String.valueOf(0));
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
			
		} finally {
			return commonJsonMap.autoMap( data,mapBusi);
		}
		
	}
	
	@ResponseBody
	@RequestMapping("/up_vip.do")
	public Object upVip(HttpServletRequest request) {
		Map<String, Object> data=new HashMap<String, Object>();
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		try {
			//如果判断异常，则退出不做业务处理
			if(!ApiCommonUtil.dealCommonBuget(mapBusi,map, request))
			{				
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			
			String toPhone = (String) map.get("toPhone");
			String content = Sysconfig.CONFIG_PARAM.get(ConfigConstant.MSG_ACTIVITY_UP_VIP);
			SendMsgUtil.send(toPhone,content);
			data.put("smsCode", String.valueOf(0));
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());
			
		} finally {
			return commonJsonMap.autoMap( data,mapBusi);
		}
		
	}
	
	
}

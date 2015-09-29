package com.jkkp.pc.apply.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.appapi.common.jsonmap.ApiResponse;
import com.jkkp.appapi.common.jsonmap.SendMsgUtil;
import com.jkkp.common.BaseController;
import com.jkkp.pc.apply.model.EcshopHomepageApply;
import com.jkkp.pc.apply.service.IEcshopHomepageApplyService;
import com.jkkp.utils.CommonUtil;


@RequestMapping("/homepageApply")
@Controller
public class EcshopHomepageApplyController extends BaseController {
	
	@Autowired
	private IEcshopHomepageApplyService ecshopHomepageApplyService;
	
	@ResponseBody
	@RequestMapping("/save.do")
	public Object aj_save(HttpServletRequest request,EcshopHomepageApply ecshopHomepageApply) {
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			ecshopHomepageApply.setCreateTime(new Date());
			ecshopHomepageApplyService.insertHomepageApply(ecshopHomepageApply);
			resultMap.put("code", String.valueOf(0));
			resultMap.put("msg", "保存首页预约成功！");
			resultMap.put("id", ecshopHomepageApply.getId()+"");
			return new ApiResponse<Map<String, String>>(resultMap);
		} catch (Exception e) {
			logger.error("保存首頁预约出现异常！", e);
			resultMap.put("code", String.valueOf(999));
			resultMap.put("msg", "保存首頁预约出现异常！");
			return new ApiResponse<Map<String, String>>(resultMap);
		}
	}
	//客服回复
	@ResponseBody
	@RequestMapping("/serReply.do")
	public Object serReply(HttpServletRequest request,EcshopHomepageApply ecshopHomepageApply) {
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			Integer id = ecshopHomepageApply.getId();
			EcshopHomepageApply  homepageApply = ecshopHomepageApplyService.findById(id);
			if(homepageApply != null){
				homepageApply.setSerReplyTime(new Date());
				homepageApply.setSerReplyContent(ecshopHomepageApply.getSerReplyContent());
				homepageApply.setSerHasReply(1);
				ecshopHomepageApplyService.update(homepageApply);
			}
			resultMap.put("code", String.valueOf(0));
			resultMap.put("msg", "客服回复预约成功");
			return new ApiResponse<Map<String, String>>(resultMap);
		} catch (Exception e) {
			logger.error("客服回复預約出现异常！", e);
			resultMap.put("code", String.valueOf(999));
			resultMap.put("msg", "客服回复預約出现异常！");
			return new ApiResponse<Map<String, String>>(resultMap);
		}
	}

	//获取短信验证码
	@ResponseBody
	@RequestMapping("/getApplySmsCode.do")
	public Object aj_getApplySmsCode(@RequestParam String phone, HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			String smsCode = CommonUtil.getRandomStr(6);
			req.getSession().setAttribute("smsCode", smsCode);
			String content = "尊敬的用户您好！您的预约验证码为:" + smsCode + "【家可可】";
			SendMsgUtil.send(phone, content);
			resultMap.put("code", String.valueOf(0));
			resultMap.put("msg", "获取短信验证码成功");
			return new ApiResponse<Map<String, String>>(resultMap);
		} catch (Exception e) {
			logger.error("获取短信验证码出现异常！", e);
			resultMap.put("code", String.valueOf(999));
			resultMap.put("msg", "获取短信验证码出现异常！");
			return new ApiResponse<Map<String, String>>(resultMap);
		}
	}
	//短信验证码校验
	@ResponseBody
	@RequestMapping("/validateApplySmsCode.do")
	public Object aj_validateApplySmsCode(@RequestParam String smsCode,@RequestParam Integer id,
			HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			String sessionSmsCode = (String) req.getSession().getAttribute("smsCode");
			if(smsCode != null && smsCode.equals(sessionSmsCode)){
				EcshopHomepageApply  homepageApply = ecshopHomepageApplyService.findById(id);
				if(homepageApply != null){
					homepageApply.setIsChecked(1);
					ecshopHomepageApplyService.update(homepageApply);
				}
			}
			resultMap.put("code", String.valueOf(0));
			resultMap.put("msg", "短信验证码校验成功");
			return new ApiResponse<Map<String, String>>(resultMap);
		} catch (Exception e) {
			logger.error("短信验证码校验出现异常！", e);
			resultMap.put("code", String.valueOf(999));
			resultMap.put("msg", "短信验证码校验出现异常！");
			return new ApiResponse<Map<String, String>>(resultMap);
		}
	}	
	
}

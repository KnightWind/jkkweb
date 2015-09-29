package com.jkkp.appapi.common.control.system;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkkp.appapi.common.jsonmap.ApiCommonUtil;
import com.jkkp.appapi.common.jsonmap.CommonJsonMap;
import com.jkkp.appapi.common.jsonmap.SendMsgUtil;
import com.jkkp.appapi.common.service.interfaces.IAppointmentMemberSV;
import com.jkkp.appapi.common.service.interfaces.IBaseinf;
import com.jkkp.appapi.common.service.interfaces.ILoginSV;
import com.jkkp.appapi.common.service.interfaces.ISendSmsRecordSV;
import com.jkkp.appapi.common.service.interfaces.ISuppMessagePushSV;
import com.jkkp.common.BaseController;
import com.jkkp.modules.appointment.model.AppointmentMember;
import com.jkkp.modules.basedata.constants.AttachmentConstant;
import com.jkkp.modules.interfaceauth.model.UserTocken;
import com.jkkp.modules.interfaceauth.service.UserTockenService;
import com.jkkp.modules.member.model.Member;
import com.jkkp.modules.member.model.VMember;
import com.jkkp.modules.member.service.IMemberService;
import com.jkkp.modules.supplier.service.ISupplierUserService;
import com.jkkp.modules.system.constants.ConfigConstant;
import com.jkkp.modules.system.model.SendSmsRecord;
import com.jkkp.modules.system.model.Sysconfig;
import com.jkkp.utils.CheckedUtil;
import com.jkkp.utils.CommonUtil;


@SuppressWarnings("finally")
@Controller
@RequestMapping("/")
public class LoginSVController extends BaseController {
	@Autowired  ISuppMessagePushSV suppMessagePushSV;
	@Autowired
	ILoginSV loginSV;
	@Autowired
	ISendSmsRecordSV sendSmsRecordsv;
	@Autowired
	CommonJsonMap commonJsonMap;
	@Autowired	IAppointmentMemberSV appointmentMemberSV;
	@Autowired 
	public UserTockenService userTockenService;
	@Autowired 
	private IMemberService iMemberService;
	@Autowired  
	private ISupplierUserService supplierUserService;
	@Autowired private IBaseinf IBaseinfService;
	
	@ResponseBody
	@RequestMapping("/member_regist.do")
	public Object loginRgist(HttpServletRequest request) {

		Member member = new Member();
		Timestamp time=new Timestamp(System.currentTimeMillis()); 
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
			

			boolean isExists = loginSV.findByBillId(map);
			if(isExists){
				mapBusi.put("mess", "号码已注册！");
				mapBusi.put("doneCode", "0013");
				return -1;
			}

			
			

			String mobile=(String) map.get("memberMobile");
			//增加没有昵称默认写
			String nickname="家可可会员";
			if(map.containsKey("memberNickName")){
				nickname=(String) map.get("memberNickName");	
				if(nickname!=null&nickname!="")
					nickname=(String) map.get("memberNickName");
				else {
					nickname="家可可会员";
				}
			}
			String password=(String) map.get("memberPasswd");
			member.setMobile(mobile);
			member.setNickname(nickname);
			//member.setPassword(ApiCommonUtil.EncoderByMd5(password));
			member.setPassword((password));
			member.setCreateTime(time);
			if(map.containsKey("cityDomain")){
				String cityDomain=(String) map.get("cityDomain");	
				member.setCityDomain(cityDomain);
			}
			loginSV.save(member);
			
			data.put("id", member.getId());
			data.put("mobile" , member.getMobile());
			data.put("nickname" , member.getNickname());
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap(data,mapBusi);
		}

	}
	
	@ResponseBody
	@RequestMapping("/member_getCode.do")
	public Object loginGetCode(HttpServletRequest request) {

		List<VMember> members = null;
		Timestamp time=new Timestamp(System.currentTimeMillis()); 
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
			
			
			String mobile=(String) map.get("memberMobile");
			//c端传 1； b端串2；j端:3  ;新用户注册:0
			String type = (String) map.get("type");
			if(mobile==null||mobile.isEmpty()||mobile.equals("")){
				mapBusi.put("mess", "号码不能为空");
				mapBusi.put("doneCode", "0016");
				return -1;
			}
			if(StringUtils.isBlank(type)){
				mapBusi.put("mess", "type不能为空");
				mapBusi.put("doneCode", "0017");
				return -1;
			}
			//注册
			if("0".equals(type)){
				List list1 = iMemberService.selectByProperty("mobile", mobile);
				if(list1!=null&&list1.size()>0){
					mapBusi.put("mess", "用户号码在c端已存在");
					mapBusi.put("doneCode", "0020");
					return -1;
				}
//				Map pmap =new HashMap();
//				pmap.put("mobile", mobile);
//				List list2 = supplierUserService.queryUserInfo(pmap);
//				if(list2!=null&&list2.size()>0){
//					mapBusi.put("mess", "用户号码在j端或b端已存在");
//					mapBusi.put("doneCode", "0021");
//					return -1;
//				}
			}else if("1".equals(type)){
				List list = iMemberService.selectByProperty("mobile", mobile);
				if(list==null||list.size()==0){
					mapBusi.put("mess", "用户号码不存在");
					mapBusi.put("doneCode", "0018");
					return -1;
				}
			//b端	
			}else if("2".equals(type)){
				Map pmap =new HashMap();
				pmap.put("type", "1");
				pmap.put("mobile", mobile);
				List list = supplierUserService.queryUserInfo(pmap);
				if(list==null||list.size()==0){
					mapBusi.put("mess", "商家用户号码不存在");
					mapBusi.put("doneCode", "0018");
					return -1;
				}
			//监理
			}else if("3".equals(type)){
				Map pmap =new HashMap();
				pmap.put("type", "5");
				pmap.put("mobile", mobile);
				List list = supplierUserService.queryUserInfo(pmap);
				if(list==null||list.size()==0){
					mapBusi.put("mess", "监理用户号码不存在");
					mapBusi.put("doneCode", "0018");
					return -1;
				}
			}
			
			String smsCode=SendMsgUtil.createRandomVcode();
			//暂时屏蔽发送短信接口
			String smsContent=Sysconfig.CONFIG_PARAM.get(ConfigConstant.SMS_SEND_CONTENT);
			smsContent=smsContent.replaceAll("sms_code", smsCode);
			SendMsgUtil.send(mobile,smsContent);
			
			SendSmsRecord sendSmsRecord=null;
			
			sendSmsRecord=sendSmsRecordsv.queryByBillId(map);
			//如窜在则更新对应验证码和下发时间
			if(sendSmsRecord!=null){
				sendSmsRecord.setSmscode(smsCode);
				sendSmsRecord.setSendDate(ApiCommonUtil.getTiem());
				sendSmsRecordsv.update(sendSmsRecord);
			}else{
				//如不存在，新增一条数据
				sendSmsRecord=new SendSmsRecord();
				sendSmsRecord.setMobile(mobile);
				sendSmsRecord.setSmscode(smsCode);
				sendSmsRecord.setSendDate(ApiCommonUtil.getTiem());
				sendSmsRecord.setStatus(0);
				//保存随机验证码，用于验证用户提交验证码是否超时及准确性
				sendSmsRecordsv.save(sendSmsRecord);
			}
			data.put("smsCode", smsCode);
			
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap( data,mapBusi);
		}

	}
	
	
	
	@ResponseBody
	@RequestMapping("/member_getPWDCode.do")
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
			long pwd = System.currentTimeMillis();
	        String pwdStr = String.valueOf(pwd);
	        pwdStr = pwdStr.substring(6);
			String smsCode = "a" + pwdStr;
			String smsContent=Sysconfig.CONFIG_PARAM.get(ConfigConstant.SMS_THANKYOU_USED_JKK);
			//smsContent=smsContent.replaceAll("sms_code", smsCode);
			//smsContent=smsContent.replaceAll("sms_account", mobile);
			SendMsgUtil.send(mobile,smsContent);
			data.put("smsCode", smsCode);
			
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap( data,mapBusi);
		}

	}
	
	@ResponseBody
	@RequestMapping("/visitor_register.do")
	public Object visitor_register(HttpServletRequest request) {
		Map<String, Object> data=new HashMap<String, Object>();
		Map<String, Object> map = null;
		Map<String, Object> mapBusi = new HashMap<String, Object>();
		mapBusi.put("mess", "操作成功");
		mapBusi.put("doneCode", "0000");
		Member member=new Member();
		try {
			//如果判断异常，则退出不做业务处理
			if(!ApiCommonUtil.dealCommonBuget(mapBusi,map, request))
			{				
				return -1;
			}
			map = commonJsonMap.setRequestMap(request);
			
			String mobile = (String) map.get("memberMobile");
			String appointmentId = (String) map.get("appointmentId");
			if(loginSV.findByBillId(map)==false){//没有注册过的手机号码，就注册一个号码，而且还是随机的
				//注册一个会员，发送随机生成的密码给用户
				long pwd = System.currentTimeMillis();
		        String pwdStr = String.valueOf(pwd);
		        pwdStr = pwdStr.substring(6);
				String smsCode = "a" + pwdStr;
				String smsContent=Sysconfig.CONFIG_PARAM.get(ConfigConstant.SMS_THANKYOU_USED_JKK);
				//smsContent=smsContent.replaceAll("sms_code", smsCode);
				//smsContent=smsContent.replaceAll("sms_account", mobile);
				SendMsgUtil.send(mobile,smsContent);
				data.put("smsCode", smsCode);
				
				//注册一个游客
				String nickname="家可可会员";
				
				member.setMobile(mobile);
				member.setNickname(nickname);
				//member.setPassword(ApiCommonUtil.EncoderByMd5(password));
				member.setPassword(CommonUtil.md5(smsCode));
				member.setCreateTime(ApiCommonUtil.getTiem());
				loginSV.save(member);
				

			}else {
				//查找对应的用户
				member = loginSV.findByBill(map);
				data.put("smsCode", "");
			}	
			//保存预约单用户关系表
			List<AppointmentMember> am=appointmentMemberSV.selectByProperty(new String[]{"aid","mid"}, new Object[]{Integer.valueOf(appointmentId),member.getId()});
			if(am.size()<=0){
				AppointmentMember appointmentMember =new AppointmentMember();
				appointmentMember.setMid(member.getId());
				appointmentMember.setAid(Integer.valueOf(appointmentId));
				appointmentMember.setCreateTime(ApiCommonUtil.getTiem());
				appointmentMemberSV.save(appointmentMember);
			}
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap( data,mapBusi);
		}

	}
	
	
	@ResponseBody
	@RequestMapping("/sendThankMSG.do")
	public Object sendThankMSG(HttpServletRequest request) {
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
			String smsContent=Sysconfig.CONFIG_PARAM.get(ConfigConstant.SMS_THANKYOU_USED_JKK);
			//smsContent=smsContent.replaceAll("sms_code", smsCode);
			//smsContent=smsContent.replaceAll("sms_account", mobile);
			SendMsgUtil.send(mobile,smsContent);
			data.put("msg", smsContent);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap( data,mapBusi);
		}
	
	}
	
	@ResponseBody
	@RequestMapping("/check_Code_isValid.do")
	public Object checkCodeIsValid(HttpServletRequest request) {
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
			
			
			String mobile=(String) map.get("memberMobile");
			if(mobile==null||mobile.isEmpty()||mobile.equals("")){
				mapBusi.put("mess", "号码不能为空");
				mapBusi.put("doneCode", "9999");
				return -1;
			}
			String code=(String) map.get("smsCode");
			if(code==null||code.isEmpty()||code.equals("")){
				mapBusi.put("mess", "验证码不能为空");
				mapBusi.put("doneCode", "9999");
				return -1;
			}
			
			SendSmsRecord sendSmsRecord=null;
			sendSmsRecord=sendSmsRecordsv.queryByBillId(map);
			long nowDate=ApiCommonUtil.getTiem().getTime()/1000;
			if(sendSmsRecord!=null){
				String smsCode=sendSmsRecord.getSmscode();
				
				if(!smsCode.equals(code)){
					mapBusi.put("mess", "验证码错误");
					mapBusi.put("doneCode", "9999");
					return -1;
				}
				
				long validTime = Long.valueOf(Sysconfig.CONFIG_PARAM.get(ConfigConstant.SEND_SMSCODE_VALID_TIME));
				long sendDate=sendSmsRecord.getSendDate().getTime()/1000;
				if((nowDate-sendDate)>validTime){
					mapBusi.put("mess", "验证码已失效");
					mapBusi.put("doneCode", "9999");
					return -1;
				}

			}else{
				mapBusi.put("mess", "未找到下发验证码");
				mapBusi.put("doneCode", "9999");
				return -1;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap( data,mapBusi);
		}

	}
	
	@ResponseBody
	@RequestMapping("/member_resetPw.do")
	public Object loginResetPw(HttpServletRequest request) {
		Map<String, Object> data=new HashMap<String, Object>();
		Member member =null;
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
			
			String memberMobile=(String) map.get("memberMobile");
			if(memberMobile==null||memberMobile.isEmpty()||memberMobile.equals("")){
				mapBusi.put("mess", "号码不能为空");
				mapBusi.put("doneCode", "9999");
				return -1;
			}

			boolean isExists = loginSV.findByBillId(map);
			if(!isExists){
				mapBusi.put("mess", "号码未注册！");
				mapBusi.put("doneCode", "0015");
				return -1;
			}
			
			member = loginSV.findByBill(map);
			String memberPasswd=(String) map.get("memberPasswd");			
			//member.setPassword(ApiCommonUtil.EncoderByMd5(memberPasswd));	
			member.setPassword((memberPasswd));	
			loginSV.update(member);
			data.put("data", member);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap(data,mapBusi);
		}

	}
	
	@ResponseBody
	@RequestMapping("/member_changePw.do")
	public Object changePw(HttpServletRequest request) {
		Map<String, Object> data=new HashMap<String, Object>();
		Member member =null;
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
			
			data.put("result", false);
			String memberId=(String) map.get("memberId");
			if(memberId==null||memberId.isEmpty()||memberId.equals("")){
				mapBusi.put("mess", "用户ID不能为空");
				mapBusi.put("doneCode", "9999");
				return -1;
			}

			member = loginSV.findById(Integer.valueOf(memberId));
			if(member==null){
				mapBusi.put("mess", "用户未注册！");
				mapBusi.put("doneCode", "0015");
				return -1;
			}
			
			String memberPasswd = (String) map.get("memberPasswd");
			String memberNewPasswd = (String) map.get("memberNewPasswd");
			if (CheckedUtil.isNotEmpty(memberPasswd)
					&& CheckedUtil.isNotEmpty(memberNewPasswd)) {
				if(memberPasswd==memberNewPasswd){
					mapBusi.put("mess", "新旧密码不能一样");
					mapBusi.put("doneCode", "0018");
					return -1;
				}else if (member.getPassword().equals(memberPasswd)) {
					member.setPassword((memberNewPasswd));
					loginSV.update(member);
					data.put("result", true);
				} else {
					mapBusi.put("mess", "密码错误");
					mapBusi.put("doneCode", "0004");
					return -1;
				}
			} else {
				mapBusi.put("mess", "密码不能为空");
				mapBusi.put("doneCode", "0017");
				return -1;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap(data,mapBusi);
		}

	}

	
	@ResponseBody
	@RequestMapping("/member_login.do")
	public Object loginMember(HttpServletRequest request) {

//		VMember vmember = new VMember();
		Member member =null;
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

			boolean isExists = loginSV.findByBillId(map);
			if(!isExists){
				mapBusi.put("mess", "号码未注册！");
						mapBusi.put("doneCode", "0015");
				return -1;
			}
			String memberMobile=(String) map.get("memberMobile");
			if(memberMobile==null||memberMobile.isEmpty()||memberMobile.equals("")){
				mapBusi.put("mess", "号码不能为空");
				mapBusi.put("doneCode", "9999");
				return -1;
			}
			
			member = loginSV.findByBill(map);
			//String memberPasswd=ApiCommonUtil.EncoderByMd5((String) map.get("memberPasswd"));	
			String memberPasswd=((String) map.get("memberPasswd"));	
			String qryPasswd=member.getPassword();			


			
			if(memberPasswd.equals(qryPasswd)){
				mapBusi.put("mess", "登录成功！");
				data.put("id", member.getId());
				data.put("cityDomain", member.getCityDomain());
				data.put("mobile", member.getMobile());
				data.put("nickname", member.getNickname());
				data.put("reallName", member.getReallName());
				data.put("headImg", IBaseinfService.getHeadimg(member.getId(), AttachmentConstant.MEMBER_TYPE));
//				BaseTools.CopyBean(member, vmember);
				UserTocken tocken = userTockenService.createNewTocken(member);
//				vmember.setTockenId(tocken.getTockenId());
				data.put("tockenId", tocken.getTockenId());
//				vmember.setHeadImg(IBaseinfService.getHeadimg(member.getId(), AttachmentConstant.MEMBER_TYPE));
				//C端登录时，验证登录名、cid是否存在supp_message_push，如存在跳过，无则新增
				//C端用手机号码
				if(map.containsKey("cid")){
					Map<String, Object> usemap=new HashMap<String, Object>();
					usemap.put("cid", (String) map.get("cid"));
					usemap.put("userName", member.getNickname());
					//usemap.put("spId", member.getMobile());
					usemap.put("spId", member.getId());
					suppMessagePushSV.dealPushLoadMember(usemap);
				}
			}
			else{
				member=null;
				mapBusi.put("doneCode", "0004");
				mapBusi.put("mess", "密码错误！");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap(data, mapBusi);
		}

	}
	
	@ResponseBody
	@RequestMapping("/member_check.do")
	public Object member_check(HttpServletRequest request) {

		List<VMember> members = null;
		Map<String, Integer> ret=new HashMap<String, Integer>();
		Member member =null;
		Timestamp time=new Timestamp(System.currentTimeMillis()); 
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

			boolean isExists = loginSV.findByBillId(map);
			if(isExists){
				ret.put("result", 1);
			}
			else {
				ret.put("result", 0);
			}
		
			
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap( ret,mapBusi);
		}

	}
	
	
	@ResponseBody
	@RequestMapping("/getmemberbymobile.do")
	public Object getmemberbymobile(HttpServletRequest request) {
		Member member =null;
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
			member = loginSV.findByBill(map);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap(member,mapBusi);
		}

	}

	@ResponseBody
	@RequestMapping("/getmemberbyid.do")
	public Object getmemberbyid(HttpServletRequest request) {
		Member member =null;
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
			member = loginSV.findByBill(map);
		} catch (Exception e) {
			e.printStackTrace();
			mapBusi.put("doneCode", "9999");
			mapBusi.put("mess", e.getMessage());

		} finally {
			return commonJsonMap.autoMap(member,mapBusi);
		}

	}
	
	public static void main(String[] args){
		System.out.println(StringUtils.isBlank(""));
		System.out.println(StringUtils.isEmpty(""));
	}

}

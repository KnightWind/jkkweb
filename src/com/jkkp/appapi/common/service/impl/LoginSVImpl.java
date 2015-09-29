package com.jkkp.appapi.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.internal.compiler.ast.ArrayAllocationExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.common.jsonmap.ApiCommonUtil;
import com.jkkp.appapi.common.jsonmap.SendMsgUtil;
import com.jkkp.appapi.common.service.interfaces.IAppointmentMemberSV;
import com.jkkp.appapi.common.service.interfaces.ILoginSV;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.appointment.model.AppointmentMember;
import com.jkkp.modules.member.mapper.MemberMapper;
import com.jkkp.modules.member.model.Member;
import com.jkkp.modules.member.model.VMember;
import com.jkkp.modules.system.constants.ConfigConstant;
import com.jkkp.modules.system.model.Sysconfig;
import com.jkkp.utils.CommonUtil;

@Service("loginSV")
public class LoginSVImpl extends ServiceSupport<Member, VMember, Integer> implements ILoginSV{
	
	@Autowired
	MemberMapper memberMapper;
	@Autowired	IAppointmentMemberSV appointmentMemberSV;

	@Override
	public boolean findByBillId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Integer countSize = memberMapper.findByBillId(map);
		if(countSize>0)
			return true;
		else 
			return false;
	}

	@Override
	protected Mapper<Member> getMapper() {
		// TODO Auto-generated method stub
		return memberMapper;
	}

	@Override
	public Member findByBill(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return memberMapper.findByBill(map);
	}

	@Override
	public Map<String, Object> visitorRegister(String Mobile,int appointment) {
		// TODO Auto-generated method stub
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("memberMobile", Mobile);
		map.put("appointmentId", String.valueOf(appointment));
		String mobile = (String) map.get("memberMobile");
		String appointmentId = (String) map.get("appointmentId");
		Map<String, Object> data = new HashMap<String, Object>();
		Member member = new Member();
		if(this.findByBillId(map)==false){//没有注册过的手机号码，就注册一个号码，而且还是随机的
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
			this.save(member);
			data.put("newregister", "1");
			data.put("mobile", Mobile);

		}else {
			//查找对应的用户
			member = this.findByBill(map);
			data.put("smsCode", "");
			data.put("newregister", "0");
			data.put("mobile", Mobile);
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
		return data;
	}

}

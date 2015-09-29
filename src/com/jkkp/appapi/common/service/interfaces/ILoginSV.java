package com.jkkp.appapi.common.service.interfaces;

import java.util.List;
import java.util.Map;

import com.jkkp.common.IService;
import com.jkkp.modules.member.model.Member;
import com.jkkp.modules.member.model.VMember;

public interface ILoginSV extends IService<Member, VMember, Integer>{
	/**
	 * @param memberMobile 用户手机
	 * @return Member 会员
	 */
	boolean findByBillId(Map<String, Object> map);
	/**
	 * @param memberMobile 用户手机
	 * @return Member 会员
	 */
	Member findByBill(Map<String, Object> map);
	
	/**
	 * 
	 */
	Map<String, Object>visitorRegister(String Mobile,int appointment);
}

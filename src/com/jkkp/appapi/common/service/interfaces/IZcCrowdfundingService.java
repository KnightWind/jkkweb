package com.jkkp.appapi.common.service.interfaces;

import java.util.List;
import java.util.Map;

import com.jkkp.modules.crowdfunding.model.JlService;

public interface IZcCrowdfundingService {
    
	/**
	 *  保存一条监理交易记录
	 * @param phone 电话号码
	 * @param cprice  金额
	 * @param ctype  类型
	 * @param source 客户端来源
	 * @param payType 支付途径 1：微信端支付 2：Android支付 3：IOS支付
	 * @param payway 支付类型 1：支付宝 2：易宝 3微信支付
	 * @return  
	 */
	public Map<String, Object> saveOneJlService(String phone,Float cprice,String ctype,Integer source, Integer payType, Integer payway);
	
	public Map<String, Object> updateInfo(Integer id);
	
	public JlService getByOrderCode(String orderCode);
	
	public List<JlService> findByPhone(String phone);
}

package com.jkkp.appapi.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jkkp.appapi.common.service.interfaces.IZcCrowdfundingService;
import com.jkkp.modules.crowdfunding.model.JlService;
import com.jkkp.modules.crowdfunding.service.IJlService;
import com.jkkp.modules.order.model.PaymentRecord;
import com.jkkp.modules.order.service.IPaymentRecordService;

@Service("zcCrowdfundingService")
public class ZcCrowdfundingServiceImpl implements IZcCrowdfundingService {

	@Autowired
	private IJlService jlService;
	@Autowired
	private IPaymentRecordService paymentRecordService;
	
	@Override
	public Map<String, Object> saveOneJlService(String phone, Float cprice,String ctype,Integer source, Integer payType, Integer payway) {
		List<JlService> haveList = jlService.hasBuyJLService(phone);
		Map<String, Object> map=new HashMap<String, Object>();
		if(haveList.size()>0){
			map.put("type", 0);
			map.put("massage", "您已购买套餐,无需重复购买");
			return map;
		}
		JlService bean=new JlService();
		bean.setPhone(phone);
		bean.setCprice(cprice);
		bean.setCtype(ctype);
		jlService.addOne(bean);
		
		PaymentRecord record = paymentRecordService.saveJlService(bean, source, payType, payway);
		map.put("type", 1);
		map.put("massage", "购买成功");
		map.put("id", bean.getId());
		map.put("orderCode", bean.getOrderCode());
		map.put("serialCode", record.getSerialCode());
		map.put("ctype", ctype);
		map.put("cprice", cprice);
	    return map;
	}

	@Override
	public Map<String, Object> updateInfo(Integer id) {
		Map<String, Object> map=new HashMap<String, Object>();
	    try {
	    	jlService.updateInfo(id);
	    	map.put("status", 1);
	    	map.put("message", "状态更改成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", 0);
	    	map.put("message", "状态更改失败");
		}
	    return map;
	}

	@Override
	public JlService getByOrderCode(String orderCode) {
		return jlService.getByOrderCode(orderCode);
	}

	@Override
	public List<JlService> findByPhone(String phone) {
		return jlService.hasBuyJLService(phone);
	}

}

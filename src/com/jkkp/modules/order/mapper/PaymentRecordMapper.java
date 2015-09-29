package com.jkkp.modules.order.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.modules.mapper.VPaymentRecordApi;
import com.jkkp.modules.basedata.view.VExpenditure;
import com.jkkp.modules.order.model.PaymentRecord;

public interface PaymentRecordMapper extends Mapper<PaymentRecord> {
	/**
	 * @param uid
	 * @param curpage
	 * @param pageSize
	 * @return VPaymentRecordApi
	 */
	List<VPaymentRecordApi> qryBillByUid(Map<String, Object> map);
	
	
	VPaymentRecordApi queryDepositPayRecordByPushId(Integer pushId);
	
	
	List<VExpenditure> expenditureList(Map<String, Object> map);
	long expenditureListCount(Map<String, Object> map);
	List<VExpenditure> memberExpenList(Map<String, Object> map);
	long memberExpenListCount(Map<String, Object> map);
	
}

package com.jkkp.modules.order.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.order.mapper.RefundMapper;
import com.jkkp.modules.order.model.Refund;
import com.jkkp.modules.order.service.IRefundService;
import com.jkkp.modules.order.view.VRefund;


@Service("refundService")
public class RefundServiceImpl extends ServiceSupport<Refund, VRefund, Integer> implements
		IRefundService {

	@Autowired
	private RefundMapper refundMapper;
	
	@Override
	protected Mapper<Refund> getMapper() {
		return refundMapper;
	}

	

}

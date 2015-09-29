package com.jkkp.modules.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.order.mapper.OrderIdMapper;
import com.jkkp.modules.order.model.OrderId;
import com.jkkp.modules.order.service.IOrderIdService;
import com.jkkp.modules.order.view.VOrderId;

@Service("orderIdService")
public class OrderIdServiceImpl extends ServiceSupport<OrderId, VOrderId, Integer> implements
		IOrderIdService {

	@Autowired
	private OrderIdMapper  orderIdMapper;
	
	@Override
	protected Mapper<OrderId> getMapper() {
		return orderIdMapper;
	}

	@Override
	public VOrderId findOneById(int id) {
		return this.orderIdMapper.findOneById(id);
	}

	
}

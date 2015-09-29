package com.jkkp.modules.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.order.mapper.OrderQuanMapper;
import com.jkkp.modules.order.model.OrderQuan;
import com.jkkp.modules.order.service.IOrderQuanService;
import com.jkkp.modules.order.view.VOrderQuan;

@Service("orderQuanService")
public class OrderQuanServiceImpl extends ServiceSupport<OrderQuan, VOrderQuan, Integer> implements
		IOrderQuanService {

	@Autowired
	private OrderQuanMapper orderQuanMapper;

	@Override
	protected Mapper<OrderQuan> getMapper() {
		return orderQuanMapper;
	}


}

package com.jkkp.modules.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.order.mapper.OrderFreeMapper;
import com.jkkp.modules.order.model.OrderFree;
import com.jkkp.modules.order.service.IOrderFreeService;
import com.jkkp.modules.order.view.VOrderFree;


@Service("orderFreeService")
public class OrderFreeServiceImpl extends ServiceSupport<OrderFree, VOrderFree, Integer> implements
		IOrderFreeService {
 
	@Autowired
	private OrderFreeMapper orderFreeMapper;
	
	@Override
	protected Mapper<OrderFree> getMapper() {
		return this.orderFreeMapper;
	}

	@Override
	public List<VOrderFree> selectUserOrderFree(int id) {
		return orderFreeMapper.selectUserOrderFree(id);
	}

}

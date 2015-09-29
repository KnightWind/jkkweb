package com.jkkp.modules.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.order.mapper.OrderPackageItemMapper;
import com.jkkp.modules.order.model.OrderPackageItem;
import com.jkkp.modules.order.service.IOrderPackageItemService;
import com.jkkp.modules.order.view.VOrderPackageItem;

@Service("orderPackageItemService")
public class OrderPackageItemServiceImpl extends ServiceSupport<OrderPackageItem, VOrderPackageItem, Integer>
		implements IOrderPackageItemService {

	@Autowired
	private OrderPackageItemMapper orderPackageItemMapper;
	
	@Override
	public List<VOrderPackageItem> orderItemDetail(int id) {		
		return orderPackageItemMapper.orderItemDetail(id);
	}

	@Override
	protected Mapper<OrderPackageItem> getMapper() {
		// TODO Auto-generated method stub
		return orderPackageItemMapper;
	}



}

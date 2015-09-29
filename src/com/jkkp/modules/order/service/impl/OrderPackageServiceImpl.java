package com.jkkp.modules.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.order.mapper.OrderPackageMapper;
import com.jkkp.modules.order.model.OrderPackage;
import com.jkkp.modules.order.service.IOrderPackageService;
import com.jkkp.modules.order.view.VOrderPackage;

@Service("orderPackageService")
public class OrderPackageServiceImpl extends ServiceSupport<OrderPackage, VOrderPackage, Integer> implements
		IOrderPackageService {

	@Autowired
	private OrderPackageMapper orderPackageMapper;
	
	@Override
	public List<VOrderPackage> selectAllUserOrderPackage(int id) {		
		return orderPackageMapper.selectAllUserOrderPackage(id);
	}

	@Override
	protected Mapper<OrderPackage> getMapper() {		
		return orderPackageMapper;
	}

	@Override
	public List<VOrderPackage> selectAllUserOrderPackageItem(int id) {
		return orderPackageMapper.selectAllUserOrderPackageItem(id);
	}

	

}

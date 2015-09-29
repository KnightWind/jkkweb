package com.jkkp.modules.supplier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.supplier.mapper.SupplierPositionMapper;
import com.jkkp.modules.supplier.model.SupplierPosition;
import com.jkkp.modules.supplier.service.ISupplierPositionService;

@Service("supplierPositionService")
public class SupplierPositionServiceImpl extends ServiceSupport<SupplierPosition,SupplierPosition,Integer> implements ISupplierPositionService {

	@Autowired
	private SupplierPositionMapper supplierPositionMapper;
	
	@Override
	protected Mapper<SupplierPosition> getMapper() {
		return supplierPositionMapper;
	}

	

}

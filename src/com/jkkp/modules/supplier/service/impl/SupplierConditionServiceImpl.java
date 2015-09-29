package com.jkkp.modules.supplier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.supplier.controller.ISupplierConditionService;
import com.jkkp.modules.supplier.mapper.SupplierConditionMapper;
import com.jkkp.modules.supplier.model.SupplierCondition;
import com.jkkp.modules.supplier.view.VSupplierCondition;

@Service("supplierConditionService")
public class SupplierConditionServiceImpl extends ServiceSupport<SupplierCondition, VSupplierCondition, Integer>
		implements ISupplierConditionService {

	@Autowired
	private SupplierConditionMapper supplierConditionMapper;
	
	
	@Override
	protected Mapper<SupplierCondition> getMapper() {
		return supplierConditionMapper;
	}

	

}

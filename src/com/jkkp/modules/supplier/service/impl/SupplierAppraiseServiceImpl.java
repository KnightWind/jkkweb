package com.jkkp.modules.supplier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.supplier.mapper.SupplierAppraiseMapper;
import com.jkkp.modules.supplier.model.SupplierAppraise;
import com.jkkp.modules.supplier.service.ISupplierAppraiseService;
import com.jkkp.modules.supplier.view.VSupplierAppraise;

@Service("supplierAppraiseService")
public class SupplierAppraiseServiceImpl extends
		ServiceSupport<SupplierAppraise, VSupplierAppraise, Integer> implements
		ISupplierAppraiseService {
	@Autowired
	private SupplierAppraiseMapper supplierAppraiseMapper;
	@Override
	protected Mapper<SupplierAppraise> getMapper() {
		return supplierAppraiseMapper;
	}

	

}

package com.jkkp.modules.supplier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.supplier.mapper.SupplierComplainMapper;
import com.jkkp.modules.supplier.model.SupplierComplain;
import com.jkkp.modules.supplier.service.ISupplierComplainService;
import com.jkkp.modules.supplier.view.VSupplierComplain;

@Service("supplierComplainService")
public class SupplierComplainServiceImpl extends
		ServiceSupport<SupplierComplain, VSupplierComplain, Integer> implements
		ISupplierComplainService {

	@Autowired
	private SupplierComplainMapper supplierComplainMapper;

	@Override
	protected Mapper<SupplierComplain> getMapper() {
		return supplierComplainMapper;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void close(int id) {
		supplierComplainMapper.close(id);
	}

	public VSupplierComplain complainDetail(int id) {
		return supplierComplainMapper.complainDetail(id);
	}

}

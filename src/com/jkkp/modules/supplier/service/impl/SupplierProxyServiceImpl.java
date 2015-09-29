package com.jkkp.modules.supplier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.supplier.mapper.SupplierProxyMapper;
import com.jkkp.modules.supplier.model.SupplierProxy;
import com.jkkp.modules.supplier.service.ISupplierProxyService;
import com.jkkp.modules.supplier.view.VSupplierProxy;
@Service("supplierProxyService")
public class SupplierProxyServiceImpl extends ServiceSupport<SupplierProxy,VSupplierProxy,Integer> implements ISupplierProxyService{
	@Autowired
	private SupplierProxyMapper supplierProxyMapper;
	@Override
	protected Mapper<SupplierProxy> getMapper() {
		return supplierProxyMapper;
	}

}

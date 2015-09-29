package com.jkkp.pc.supervisor.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.supplier.mapper.SupplierMapper;
import com.jkkp.modules.supplier.model.Supplier;
import com.jkkp.pc.supervisor.service.ISupervisorService;
import com.jkkp.pc.supervisor.view.SupplierPC;

@Component("supervisorServicePC")
public class SupervisorServiceImpl extends
		ServiceSupport<Supplier, SupplierPC, Integer> implements
		ISupervisorService {

	@Autowired
	private SupplierMapper supplierMapper;

	@Override
	protected Mapper<Supplier> getMapper() {
		return supplierMapper;
	}

}

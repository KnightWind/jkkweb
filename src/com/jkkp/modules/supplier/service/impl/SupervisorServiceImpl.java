package com.jkkp.modules.supplier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.supplier.mapper.SupervisorMapper;
import com.jkkp.modules.supplier.model.Supervisor;
import com.jkkp.modules.supplier.service.ISupervisorService;
import com.jkkp.modules.supplier.view.VSupervisorWeb;

@Service("SupervisorService")
public class SupervisorServiceImpl extends
		ServiceSupport<Supervisor, VSupervisorWeb, Integer> implements
		ISupervisorService {

	@Autowired
	private SupervisorMapper supervisorMapper;

	@Override
	protected Mapper<Supervisor> getMapper() {
		return supervisorMapper;
	}

	@Override
	public VSupervisorWeb selectSupervisorDetail(int id) {
		return supervisorMapper.selectSupervisorDetail(id);
	}

	
}

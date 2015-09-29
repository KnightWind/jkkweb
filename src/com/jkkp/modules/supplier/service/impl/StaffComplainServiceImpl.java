package com.jkkp.modules.supplier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.supplier.mapper.StaffComplainMapper;
import com.jkkp.modules.supplier.model.StaffComplain;
import com.jkkp.modules.supplier.service.IStaffComplainService;
import com.jkkp.modules.supplier.view.VStaffComplain;

@Service("staffComplainService")
public class StaffComplainServiceImpl extends
		ServiceSupport<StaffComplain, VStaffComplain, Integer> implements
		IStaffComplainService {

	@Autowired
	private StaffComplainMapper staffComplainMapper;

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void close(int id) {
		staffComplainMapper.close(id);
	}

	@Override
	protected Mapper<StaffComplain> getMapper() {
		return staffComplainMapper;
	}

	@Override
	public VStaffComplain detail(int id) {
		return staffComplainMapper.detail(id);
	}

}

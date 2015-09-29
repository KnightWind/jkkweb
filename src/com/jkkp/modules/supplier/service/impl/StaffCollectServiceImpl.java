package com.jkkp.modules.supplier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.supplier.mapper.StaffCollectMapper;
import com.jkkp.modules.supplier.model.StaffCollect;
import com.jkkp.modules.supplier.service.IStaffCollectService;
import com.jkkp.modules.supplier.view.VStaffCollect;

@Service("staffCollectService")
public class StaffCollectServiceImpl extends
		ServiceSupport<StaffCollect, VStaffCollect, Integer> implements
		IStaffCollectService {
	@Autowired
	private StaffCollectMapper staffCollectMapper;

	@Override
	protected Mapper<StaffCollect> getMapper() {
		return staffCollectMapper;
	}

	
}

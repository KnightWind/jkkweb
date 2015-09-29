package com.jkkp.modules.supplier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.supplier.mapper.StaffAppraiseMapper;
import com.jkkp.modules.supplier.model.StaffAppraise;
import com.jkkp.modules.supplier.service.IStaffAppraiseService;
import com.jkkp.modules.supplier.view.VStaffAppraise;

@Service("staffAppraiseService")
public class StaffAppraiseServiceImpl extends
		ServiceSupport<StaffAppraise, VStaffAppraise, Integer> implements
		IStaffAppraiseService {
	@Autowired
	private StaffAppraiseMapper staffAppraiseMapper;

	@Override
	protected Mapper<StaffAppraise> getMapper() {
		return staffAppraiseMapper;
	}

	

}

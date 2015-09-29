package com.jkkp.modules.supplier.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.supplier.mapper.StaffComplainDetailsMapper;
import com.jkkp.modules.supplier.model.StaffComplainDetails;
import com.jkkp.modules.supplier.service.IStaffComplaintsDetailsService;
import com.jkkp.modules.supplier.view.VStaffComplaintsDetails;

@Service("staffComplaintsDetailsService")
public class StaffComplaintsDetailsServiceImpl
		extends
		ServiceSupport<StaffComplainDetails, VStaffComplaintsDetails, Integer>
		implements IStaffComplaintsDetailsService {

	@Autowired
	private StaffComplainDetailsMapper staffComplaintsDetailsMapper;

	@Override
	protected Mapper<StaffComplainDetails> getMapper() {
		return staffComplaintsDetailsMapper;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveOne(StaffComplainDetails staff) {
		staff.setTypeId(3);
		staff.setTypeName("客服");
		staff.setCreateTime(new Date());
		staff.setUserName("孙小彩");
		staff.setUserId(23);
		this.save(staff);
	}

}

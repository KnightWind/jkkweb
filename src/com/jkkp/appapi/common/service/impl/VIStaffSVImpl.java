package com.jkkp.appapi.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.common.service.interfaces.VIStaffSV;
import com.jkkp.appapi.modules.mapper.VIStaff;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.supplier.mapper.SupplierCompanyStaffMapper;
import com.jkkp.modules.supplier.model.SupplierCompanyStaff;

@Service("vIStaffSV")

public class VIStaffSVImpl extends ServiceSupport<SupplierCompanyStaff,VIStaff, Integer> implements VIStaffSV {


	@Autowired SupplierCompanyStaffMapper supplierCompanyStaffMapper;

	@Override
	protected Mapper<SupplierCompanyStaff> getMapper() {
		return supplierCompanyStaffMapper;
	}

	@Override
	public VIStaff getById(Integer sid) {
		return supplierCompanyStaffMapper.getById(sid);
	}
	
	
	
}

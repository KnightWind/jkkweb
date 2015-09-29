package com.jkkp.appapi.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.common.service.interfaces.ISuppComStaffSV;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.supplier.mapper.SupplierCompanyStaffMapper;
import com.jkkp.modules.supplier.model.SupplierCompanyStaff;
import com.jkkp.modules.supplier.view.VSupplierCompanyStaff;

@Service("SuppComStaffSV")
public class SuppComStaffSVImpl  extends ServiceSupport<SupplierCompanyStaff,VSupplierCompanyStaff,Integer> implements ISuppComStaffSV {

	@Autowired  SupplierCompanyStaffMapper mapper;
 
	protected Mapper<SupplierCompanyStaff> getMapper() {
		return mapper;
	}

	@Override
	public List<SupplierCompanyStaff> queryDesignStaffBySpId(Map<String, Object> map) {
		
		return mapper.queryDesignStaffBySpId(map);
		
	}

}

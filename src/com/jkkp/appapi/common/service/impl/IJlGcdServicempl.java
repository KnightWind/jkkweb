package com.jkkp.appapi.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.common.service.interfaces.IJlGcdSV;
import com.jkkp.appapi.common.service.interfaces.VIStaffSV;
import com.jkkp.appapi.modules.mapper.VIStaff;
import com.jkkp.appapi.modules.mapper.VJlGcd;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.supplier.mapper.JlGcdMapper;
import com.jkkp.modules.supplier.mapper.SgtopicMapper;
import com.jkkp.modules.supplier.mapper.SupplierCompanyStaffMapper;
import com.jkkp.modules.supplier.model.JlGcd;
import com.jkkp.modules.supplier.model.Sgtopic;
import com.jkkp.modules.supplier.model.SupplierCompanyStaff;

@Service("IJlGcd")

public class IJlGcdServicempl extends ServiceSupport<JlGcd,VJlGcd, Integer> implements IJlGcdSV {
	@Autowired 
	JlGcdMapper Mapper;
	@Override
	protected Mapper<JlGcd> getMapper() {
		return Mapper;
	}
}

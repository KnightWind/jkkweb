package com.jkkp.modules.supplier.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.supplier.mapper.SupplierConditionCityMapper;
import com.jkkp.modules.supplier.model.SupplierConditionCity;
import com.jkkp.modules.supplier.service.ISupplierConditionCityService;
import com.jkkp.modules.supplier.view.VSimpleConditionCity;
import com.jkkp.modules.supplier.view.VSupplierConditionCity;

@Service("supplierConditionCityService")
public class SupplierConditionCityServiceImpl extends 
		ServiceSupport<SupplierConditionCity, VSupplierConditionCity, Integer>
		implements ISupplierConditionCityService {

	@Autowired
	private SupplierConditionCityMapper supplierConditionCityMapper;
	
	@Override
	protected Mapper<SupplierConditionCity> getMapper() {
		return supplierConditionCityMapper;
	}

	@Override
	public List<VSimpleConditionCity> getCityBySpId(Integer spId) {
		return supplierConditionCityMapper.getCityBySpId(spId);
	}

	@Override
	public List<SupplierConditionCity> getConditionCityBySpId(Integer spId) {
		// TODO Auto-generated method stub
		return supplierConditionCityMapper.getConditionCityBySpId(spId);
	}

	
}

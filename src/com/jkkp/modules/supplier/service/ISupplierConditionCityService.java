package com.jkkp.modules.supplier.service;

import java.util.List;

import com.jkkp.common.IService;
import com.jkkp.modules.supplier.model.SupplierConditionCity;
import com.jkkp.modules.supplier.view.VSimpleConditionCity;
import com.jkkp.modules.supplier.view.VSupplierConditionCity;

public interface ISupplierConditionCityService extends 
	IService<SupplierConditionCity, VSupplierConditionCity, Integer> {

	List<VSimpleConditionCity> getCityBySpId(Integer spId);

	List<SupplierConditionCity> getConditionCityBySpId(Integer spId);

}

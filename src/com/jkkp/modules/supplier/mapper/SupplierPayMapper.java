package com.jkkp.modules.supplier.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.supplier.model.SupplierPay;
import com.jkkp.modules.system.model.Admin;

public interface SupplierPayMapper extends Mapper<SupplierPay> {
	public List<Admin> findPage(Map<String, Object> params);
	public long countPage(Map<String, Object> params);

}
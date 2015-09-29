package com.jkkp.modules.supplier.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.supplier.model.SupplierDomain;
import com.jkkp.modules.supplier.view.VSupplierDomain;


public interface SupplierDomainMapper extends Mapper<SupplierDomain> {
	public List<VSupplierDomain> findPage(Map<String, Object> params);
	public long countPage(Map<String, Object> params);
}
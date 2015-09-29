package com.jkkp.modules.supplier.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.supplier.model.SupplierProxy;
import com.jkkp.modules.supplier.view.VSupplierProxy;

public interface SupplierProxyMapper extends Mapper<SupplierProxy> {
	List<VSupplierProxy> findAll();
	public List<VSupplierProxy> findPage(Map<String, Object> params);

	public long countPage(Map<String, Object> params);
}
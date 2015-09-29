package com.jkkp.modules.supplier.mapper;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.supplier.model.SupplierCompany;

public interface SupplierCompanyMapper extends Mapper<SupplierCompany> {
	public SupplierCompany  fin(@Param("id") Integer id);
}
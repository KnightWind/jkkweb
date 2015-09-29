package com.jkkp.modules.supplier.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.supplier.model.SupplierComplain;
import com.jkkp.modules.supplier.view.VSupplierComplain;

public interface SupplierComplainMapper extends Mapper<SupplierComplain> {
	public List<VSupplierComplain> selectAllAboutSupplierComplain(
			Map<String, Object> params);

	public long selectAllAboutSCCount(Map<String, Object> params);
	
	public void close(@Param("id") int id);
	
	public VSupplierComplain complainDetail(@Param("id")int id);
}

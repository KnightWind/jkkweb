package com.jkkp.modules.supplier.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.supplier.model.QuanO2o;
import com.jkkp.modules.supplier.view.VQuan;
import com.jkkp.modules.supplier.view.VQuanO2o;

public interface QuanO2oMapper extends Mapper<QuanO2o> {
	public List<VQuanO2o> findPage(Map<String, Object> params);

	public long countPage(Map<String, Object> params);
	
	//商家 代金券列表
	public List<VQuan> supplierQuanList(Map<String, Object> params);

	public long supplierQuanCount(Map<String, Object> params);
	
}
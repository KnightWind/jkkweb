package com.jkkp.modules.supplier.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.supplier.model.Quan;
import com.jkkp.modules.supplier.view.VQuan;

public interface QuanMapper extends Mapper<Quan> {
	List<VQuan> finAll();

	public List<VQuan> findPage(Map<String, Object> params);

	public long countPage(Map<String, Object> params);

	// 代金券列表
	public List<VQuan> daiJinQuanList(Map<String, Object> params);

	public long daiJinQuanCount(Map<String, Object> params);
	

}
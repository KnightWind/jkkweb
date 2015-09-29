package com.jkkp.modules.basedata.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.basedata.model.AdSearch;
import com.jkkp.modules.basedata.view.VAdSearch;

public interface AdSearchMapper extends Mapper<AdSearch> {
	List<VAdSearch> findAll();
	List<VAdSearch> findPage(Map<String, Object> params);
	public long countPage(Map<String, Object> params);
}
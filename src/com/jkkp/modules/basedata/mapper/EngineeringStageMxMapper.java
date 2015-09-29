package com.jkkp.modules.basedata.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.basedata.model.EngineeringStageMx;
import com.jkkp.modules.basedata.view.VEngineeringStageMx;

public interface EngineeringStageMxMapper extends Mapper<EngineeringStageMx> {
	// public void show(@Param("id") int id);
	//
	// public void hide(@Param("id") int id);

	public List<VEngineeringStageMx> selectAllEngineeringStageMx(
			Map<String, Object> params);

	public long selectAllEngineeringStageMxCount(Map<String, Object> params);
}
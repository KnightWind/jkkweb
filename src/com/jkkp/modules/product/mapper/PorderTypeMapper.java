package com.jkkp.modules.product.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.basedata.view.VEngineeringStage;
import com.jkkp.modules.product.model.PorderType;

public interface PorderTypeMapper extends Mapper<PorderType> {
	public long selectPorderTypeCount(Map<String, Object> params);

	public List<VEngineeringStage> selectAllPorderType(
			Map<String, Object> params);

	public void hide(@Param("id") int id);

	public void show(@Param("id") int id);
}
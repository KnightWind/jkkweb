package com.jkkp.modules.basedata.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.basedata.model.EngineeringStageMxInst;
import com.jkkp.modules.basedata.view.VEngineeringStageMxInst;

public interface EngineeringStageMxInstMapper extends
		Mapper<EngineeringStageMxInst> {
	public List<VEngineeringStageMxInst> selectEngineeringStageMxInsts(
			@Param("id") int id);
}
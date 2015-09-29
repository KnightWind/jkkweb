package com.jkkp.modules.basedata.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.basedata.model.Help;
import com.jkkp.modules.basedata.view.VHelp;

public interface HelpMapper extends Mapper<Help> {
	public List<VHelp> findPage(Map<String, Object> params);

	public long countPage(Map<String, Object> params);

	public VHelp selectOneHelpCata(@Param("id") int id);

	public void show(@Param("id") int id);

	public void hidden(@Param("id") int id);
}
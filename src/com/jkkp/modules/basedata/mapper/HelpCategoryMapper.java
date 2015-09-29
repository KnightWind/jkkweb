package com.jkkp.modules.basedata.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.basedata.model.HelpCategory;

public interface HelpCategoryMapper extends Mapper<HelpCategory> {
	public List<HelpCategory> selectAllParents();
	public List<HelpCategory> selectAllSubclass(@Param("pid") int pid);
}
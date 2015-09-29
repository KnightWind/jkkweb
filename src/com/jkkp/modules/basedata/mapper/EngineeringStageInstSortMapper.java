package com.jkkp.modules.basedata.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.basedata.model.EngineeringStageInstSort;

public interface EngineeringStageInstSortMapper extends Mapper<EngineeringStageInstSort> {

	List<EngineeringStageInstSort> findParentSort(@Param("instanceId") Integer instanceId, 
			@Param("pid") Integer parentId);
}
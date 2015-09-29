package com.jkkp.modules.basedata.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.basedata.model.EngineeringStage;
import com.jkkp.modules.basedata.view.VEngineeringStage;
import com.jkkp.utils.Pager;

public interface EngineeringStageMapper extends Mapper<EngineeringStage> {
	// --------web管理后台------
	public List<VEngineeringStage> selectAllEngineeringStage(
			Map<String, Object> params);

	public long selectAllEngineeringStageCount(Map<String, Object> params);

	public List<VEngineeringStage> selectAllOTEngineeringStage(
			Map<String, Object> params);

	public long selectAllOTEngineeringStageCount(Map<String, Object> params);

	public List<VEngineeringStage> selectAllParentStages(
			Map<String, Object> params);

	public long selectAllParentStagesCount(Map<String, Object> params);

	public List<EngineeringStage> selectAllParentStage();

	// ------------web------------

	public List<EngineeringStage> findStageList(@Param("pid") Integer pid,
			@Param("excludeId") Integer excludeId);

	// ysc=========================================
	public List<VEngineeringStage> getList(String and);

	public List<VEngineeringStage> getPageList(Pager pager);

	public long getCount(String and);

	public VEngineeringStage getBeanById(Integer id);

	public int deleteByAnd(String and);
	// ysc=========================================
}
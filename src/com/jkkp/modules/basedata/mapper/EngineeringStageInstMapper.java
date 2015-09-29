package com.jkkp.modules.basedata.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.basedata.model.EngineeringStageInst;
import com.jkkp.modules.basedata.view.VEngineeringStageInst;
import com.jkkp.modules.basedata.view.VEngineeringStageInstWeb;
import com.jkkp.utils.Pager;

public interface EngineeringStageInstMapper extends Mapper<EngineeringStageInst> {
	//ysc=========================================
	public List<VEngineeringStageInst> getList(String and);
	public List<VEngineeringStageInst> getPageList(Pager pager);
	public long getCount(String and);
	public VEngineeringStageInst getBeanById(Integer id);
	public int deleteByAnd(String and);
	//ysc=========================================
	
	
	//web
	public List<VEngineeringStageInstWeb> selectAllEngineeringStageInsts(Map<String, Object> params);
	
	public Long selectAllEngineeringStageInstCount(Map<String, Object> params);
	
	//-----------web----------
}
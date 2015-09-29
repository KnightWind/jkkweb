package com.jkkp.modules.basedata.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.basedata.model.Case;
import com.jkkp.modules.basedata.view.VCase;
import com.jkkp.pc.cases.view.VCases;
import com.jkkp.utils.Pager;
public interface CaseMapper extends Mapper<Case> {
	
	public List<VCase> findPage(Map<String,Object> params);
	
	public long countPage (Map<String,Object> params);
	
	//ysc=========================================
	public List<VCase> getList(String and);
	public List<VCase> getPageList(Pager pager);
	public long getCount(String and);
	public VCase getBeanById(Integer id);
	public int deleteByAnd(String and);
	public List<VCase> getListBySpId(Integer spId);
	public List<VCase> getKgdFlag1ListBySpId(Integer spId);
	public List<VCase> getKgdFlag0ListBySpId(Integer spId);
	//ysc=========================================

	
	//pc web say
	public List<VCases> queryXCases(@Param("id")Integer id,@Param("type")Integer type,@Param("count")Integer count);
}
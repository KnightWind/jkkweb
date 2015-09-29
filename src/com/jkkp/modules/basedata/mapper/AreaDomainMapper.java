package com.jkkp.modules.basedata.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.basedata.model.AreaDomain;

public interface AreaDomainMapper extends Mapper<AreaDomain> {
	public List<AreaDomain> findAll();
	public List<AreaDomain> findName(@Param("name") String name);
	List<AreaDomain> findPage(Map<String, Object> params);
	public long countPage(Map<String, Object> params);
}
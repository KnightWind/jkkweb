package com.jkkp.modules.design.mapper;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.design.model.DesignerCollect;

public interface DesignerCollectMapper extends Mapper<DesignerCollect> {
	public long  get(@Param("spid") Integer spid);
}
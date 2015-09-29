package com.jkkp.modules.product.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.product.model.PorderList;
import com.jkkp.modules.product.view.VPorderList;

public interface PorderListMapper extends Mapper<PorderList> {

	public List<PorderList> findPage(Map<String,Object> params);
	public long pageCount(Map<String,Object> params);
	public VPorderList findPordeerById(@Param(value="id")Integer id);
	
}
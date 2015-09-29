package com.jkkp.modules.supplier.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.supplier.model.StaffOpus;
import com.jkkp.modules.supplier.view.VStaffOpus;

public interface StaffOpusMapper extends Mapper<StaffOpus>{
	
	public List<VStaffOpus> findPage(Map<String,Object> params);
	public long countPage(Map<String,Object> params);
	public VStaffOpus getVStaffOpusById(@Param(value="id") Integer id);
	
}

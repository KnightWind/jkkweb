package com.jkkp.modules.supplier.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.supplier.model.StaffComplain;
import com.jkkp.modules.supplier.view.VStaffComplain;

public interface StaffComplainMapper extends Mapper<StaffComplain> {
	public List<VStaffComplain> selectAllStaffcomplains(
			Map<String, Object> param);

	public long selectAllStaffcomplainCount(Map<String, Object> param);
	
	public void close(@Param("id")int id);
	
	public VStaffComplain detail(@Param("id") int id);
}

package com.jkkp.modules.system.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.system.model.Role;
import com.jkkp.modules.system.view.VRole;

public interface RoleMapper extends Mapper<Role> {

	public List<VRole> findPage(Map<String, Object> params);
	
	public long countPage(Map<String, Object> params);

	public List<VRole> selectAllGD();
	
	List<Role> findPagination(@Param("rowStart") int rowStart, @Param("limit") int limit);
}
package com.jkkp.modules.system.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.system.model.Admin;
import com.jkkp.modules.system.view.VAdmin;

public interface AdminMapper extends Mapper<Admin> {
	public List<VAdmin> findPage(Map<String, Object> params);

	public long countPage(Map<String, Object> params);

	public void saveResourceRole(@Param("username") String username, @Param("role") String role);

	public void deleteResourceRole(@Param("username") String username);
}
package com.jkkp.modules.system.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.system.model.Sysconfig;
public interface SysconfigMapper extends Mapper<Sysconfig> {
	public List<Sysconfig> findPage(Map<String, Object> params);
	public long countPage(Map<String, Object> params);
	public Sysconfig selectByMap(Map<String, Object> map);
}

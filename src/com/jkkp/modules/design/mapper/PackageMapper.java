package com.jkkp.modules.design.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.design.view.VPackage;

public interface PackageMapper extends Mapper<com.jkkp.modules.design.model.Package> {

	public List<VPackage> findPage(Map<String, Object> params);

	public long countPage(Map<String, Object> params);
}
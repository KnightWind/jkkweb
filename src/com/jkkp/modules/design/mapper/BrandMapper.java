package com.jkkp.modules.design.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.design.model.Brand;
import com.jkkp.modules.order.view.VOrderQuan;

public interface BrandMapper extends Mapper<Brand> {
	  public List<Brand> findAll();
	  public List<VOrderQuan> findPage(Map<String, Object> params);	
	  public long countPage(Map<String, Object> params);

}
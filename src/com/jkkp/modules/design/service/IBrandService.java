package com.jkkp.modules.design.service;

import java.util.List;

import com.jkkp.common.IService;
import com.jkkp.modules.design.model.Brand;
import com.jkkp.modules.design.view.VBrand;

public interface IBrandService extends IService<Brand, VBrand, Integer> {

	void offline(Integer id);

	void online(Integer id);
  
     public List<Brand> findAll();
}

package com.jkkp.modules.design.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.design.mapper.BrandMapper;
import com.jkkp.modules.design.model.Brand;
import com.jkkp.modules.design.service.IBrandService;
import com.jkkp.modules.design.view.VBrand;

@Service("brandService")
public class BrandServiceImpl extends ServiceSupport<Brand, VBrand, Integer> implements IBrandService {

	@Autowired
	private BrandMapper brandMapper;
	
	@Override
	protected Mapper<Brand> getMapper() {
		return brandMapper;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void offline(Integer id) {
		Brand entity = this.findById(id);
		entity.setStatus(1);
		this.update(entity);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void online(Integer id) {
		Brand entity = this.findById(id);
		entity.setStatus(0);
		this.update(entity);
	}

	@Override
	public List<Brand> findAll() {
		return brandMapper.findAll();
	}
}

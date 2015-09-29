package com.jkkp.modules.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.product.mapper.PorderListMapper;
import com.jkkp.modules.product.model.PorderList;
import com.jkkp.modules.product.service.IPorderListService;
import com.jkkp.modules.product.view.VPorderList;

@Service("porderListService")
public class PorderListServiceImpl extends ServiceSupport<PorderList, VPorderList, Integer> implements
		IPorderListService {

	@Autowired
	private PorderListMapper porderListMapper;
	
	protected Mapper<PorderList> getMapper() {
		return porderListMapper;
	}

	@Override
	public VPorderList findPordeerById(Integer id) {
		return porderListMapper.findPordeerById(id);
	}


}

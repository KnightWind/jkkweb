package com.jkkp.modules.supplier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.supplier.mapper.QuanO2oMapper;
import com.jkkp.modules.supplier.model.QuanO2o;
import com.jkkp.modules.supplier.service.IQuanO2oService;
import com.jkkp.modules.supplier.view.VQuanO2o;

@Service("quanO2oService")
public class QuanO2oServiceImpl extends ServiceSupport<QuanO2o, VQuanO2o, Integer> implements IQuanO2oService {

	@Autowired
	private QuanO2oMapper quanO2oMapper;
	
	@Override
	protected Mapper<QuanO2o> getMapper() {
		return quanO2oMapper;
	}

}

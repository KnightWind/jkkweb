package com.jkkp.modules.supplier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.supplier.mapper.QuanCardMapper;
import com.jkkp.modules.supplier.model.QuanCard;
import com.jkkp.modules.supplier.service.IQuanCardService;
import com.jkkp.modules.supplier.view.VQuanCard;

@Service("quanCardService")
public class QuanCardServiceImpl extends ServiceSupport<QuanCard, VQuanCard, Integer> implements IQuanCardService {

	@Autowired
	private QuanCardMapper quanCardMapper;

	@Override
	protected Mapper<QuanCard> getMapper() {
		return quanCardMapper;
	}

}

package com.jkkp.modules.supplier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.supplier.mapper.JlComplainMapper;
import com.jkkp.modules.supplier.model.JlComplain;
import com.jkkp.modules.supplier.service.IJlComplainService;
import com.jkkp.modules.supplier.view.VJlComplain;

@Service("jlComplainService")
public class JlComplainServiceImpl extends
		ServiceSupport<JlComplain, VJlComplain, Integer> implements
		IJlComplainService {

	@Autowired
	private JlComplainMapper jlComplainMapper;

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void close(int id) {
		jlComplainMapper.close(id);
	}

	public VJlComplain complainDetail(int id) {
		return jlComplainMapper.complainDetail(id);
	}

	@Override
	protected Mapper<JlComplain> getMapper() {
		return jlComplainMapper;
	}

}

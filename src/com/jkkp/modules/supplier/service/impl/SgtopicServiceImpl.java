package com.jkkp.modules.supplier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.supplier.mapper.SgtopicMapper;
import com.jkkp.modules.supplier.model.Sgtopic;
import com.jkkp.modules.supplier.service.ISgtopicService;
import com.jkkp.modules.supplier.view.VSgtopic;

@Service("sgtopicService")
public class SgtopicServiceImpl extends
		ServiceSupport<Sgtopic, VSgtopic, Integer> implements ISgtopicService {

	@Autowired
	private SgtopicMapper sgtopicMapper;

	@Override
	protected Mapper<Sgtopic> getMapper() {
		return sgtopicMapper;
	}

	@Override
	public VSgtopic sgtopicDetail(int id) {
		return sgtopicMapper.sgtopicDetail(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void noPass(int id) {
		sgtopicMapper.noPass(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void pass(int id) {
		sgtopicMapper.pass(id);
	}

}

package com.jkkp.modules.supplier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.supplier.mapper.JtopicMapper;
import com.jkkp.modules.supplier.model.Jtopic;
import com.jkkp.modules.supplier.service.IJtopicService;
import com.jkkp.modules.supplier.view.VJtopic;

@Service("jtopicService")
public class JtopicServiceImpl extends ServiceSupport<Jtopic, VJtopic, Integer>
		implements IJtopicService {

	@Autowired
	private JtopicMapper jtopicMapper;

	@Override
	protected Mapper<Jtopic> getMapper() {
		return jtopicMapper;
	}

	public VJtopic findVJtopicById(Integer id) {
		return jtopicMapper.findVJtopicById(id);
	}

	public VJtopic businessDetail(int id) {
		return jtopicMapper.businessDetail(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void noPass(int id) {
		jtopicMapper.noPass(id);

	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void pass(int id) {
		jtopicMapper.pass(id);

	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteOneTopic(int id) {
		jtopicMapper.deleteOneTopic(id);
	}

}
package com.jkkp.appapi.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.common.service.interfaces.IJtopicSV;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.supplier.mapper.JtopicMapper;
import com.jkkp.modules.supplier.model.Jtopic;
import com.jkkp.modules.supplier.view.VJtopic;

@Service("jtopicSV")
public class JtopicSVImpl extends ServiceSupport<Jtopic, Jtopic, Integer> implements IJtopicSV{

	@Autowired
	JtopicMapper mapper;
	protected Mapper<Jtopic> getMapper() {
		return mapper;
	}
	@Override
	public List<VJtopic> queryJtopicById(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.queryJtopicById(map);
	}

}

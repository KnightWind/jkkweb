package com.jkkp.appapi.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.common.service.interfaces.ILabelSV;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.system.mapper.LabelMapper;
import com.jkkp.modules.system.model.Label;
import com.jkkp.modules.system.view.VLabel;

@Service("labelSV")

public class LabelSVImpl extends ServiceSupport<Label,VLabel,Integer> implements ILabelSV {



	@Autowired LabelMapper labelMapper;

	@Override
	protected Mapper<Label> getMapper() {	
		return labelMapper;
	}

	@Override
	public List<Label> getByName() {
		return labelMapper.getByName();
	}

	@Override
	public List<Label> queryLabelById(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return labelMapper.queryLabelById(map);
	}

	@Override
	public List<Label> queryAllLabelById(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return labelMapper.queryAllLabelById(map);
	}

	

	
}

package com.jkkp.appapi.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.common.service.interfaces.IDesignSV;
import com.jkkp.appapi.modules.mapper.VDesign3img;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.design.mapper.DesignMapper;
import com.jkkp.modules.design.model.Design;
import com.jkkp.modules.design.view.VDesign;

@Service("designSV")
public class DesignSVImpl extends ServiceSupport<Design, VDesign, Integer> implements IDesignSV{

	@Autowired
	DesignMapper designMapper;
	@Override
	protected Mapper<Design> getMapper() {
		// TODO Auto-generated method stub
		return designMapper;
	}
	@Override
	public List<VDesign> pagin(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return designMapper.pagin(params);
	}
	@Override
	public List<VDesign3img> qrydesigns(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return designMapper.qrydesigns(map);
	}

}

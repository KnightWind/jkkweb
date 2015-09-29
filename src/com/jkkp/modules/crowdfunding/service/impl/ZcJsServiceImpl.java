package com.jkkp.modules.crowdfunding.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.crowdfunding.mapper.ZcjsMapper;
import com.jkkp.modules.crowdfunding.model.Zcjs;
import com.jkkp.modules.crowdfunding.service.IZcJsService;
import com.jkkp.modules.crowdfunding.view.VZcJs;

@Service("zcJsService")
public class ZcJsServiceImpl extends ServiceSupport<Zcjs, VZcJs, Integer> implements
		IZcJsService {

	@Autowired
	private ZcjsMapper zcjsMapper;
	@Override
	public Double selectJSTotal(int spId) {
		return zcjsMapper.selectJSTotal(spId);
	}

	@Override
	protected Mapper<Zcjs> getMapper() {
		return zcjsMapper;
	}

	
}

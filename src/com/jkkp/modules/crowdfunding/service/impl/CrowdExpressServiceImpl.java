package com.jkkp.modules.crowdfunding.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.crowdfunding.mapper.CrowdExpressMapper;
import com.jkkp.modules.crowdfunding.model.CrowdExpress;
import com.jkkp.modules.crowdfunding.service.ICrowdExpressService;
import com.jkkp.modules.crowdfunding.view.VCrowdExpress;

@Service("crowdExpressService")
public class CrowdExpressServiceImpl extends ServiceSupport<CrowdExpress, VCrowdExpress, Integer> implements ICrowdExpressService {

	@Autowired
	private CrowdExpressMapper crowdExpressMapper;
	
	@Override
	protected Mapper<CrowdExpress> getMapper() {
		return crowdExpressMapper;
	}

}

package com.jkkp.modules.crowdfunding.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.crowdfunding.mapper.CrowdOrderMapper;
import com.jkkp.modules.crowdfunding.model.CrowdOrder;
import com.jkkp.modules.crowdfunding.service.ICrowdOrderService;
import com.jkkp.modules.crowdfunding.view.VCrowdOrder;

@Service("crowdOrderService")
public class CrowdOrderServiceImpl extends ServiceSupport<CrowdOrder, VCrowdOrder, Integer> implements ICrowdOrderService {

	@Autowired
	private CrowdOrderMapper crowdOrderMapper;
	
	@Override
	protected Mapper<CrowdOrder> getMapper() {
		return crowdOrderMapper;
	}

}

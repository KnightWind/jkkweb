package com.jkkp.modules.activities.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.activities.mapper.PrizeResultMapper;
import com.jkkp.modules.activities.model.PrizeResult;
import com.jkkp.modules.activities.service.IPrizeResultService;
import com.jkkp.modules.activities.view.VPrizeResult;

@Service("prizeResultService")
public class PrizeResultServiceImpl extends ServiceSupport<PrizeResult, VPrizeResult, Integer> implements IPrizeResultService {

	@Autowired
	private PrizeResultMapper prizeResultMapper;
	
	
	@Override
	protected Mapper<PrizeResult> getMapper() {
		return prizeResultMapper;
	}

}

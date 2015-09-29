package com.jkkp.modules.activities.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.activities.mapper.ActiveAwardsMapper;
import com.jkkp.modules.activities.model.ActiveAwards;
import com.jkkp.modules.activities.service.IActiveAwardsService;
import com.jkkp.modules.activities.view.VActiveAwards;
@Service("activeAwardsService")
public class ActiveAwardsServiceImpl extends
		ServiceSupport<ActiveAwards, VActiveAwards, Integer> implements
		IActiveAwardsService {

	@Autowired
	private ActiveAwardsMapper activeAwardsMapper;
	
	
	@Override
	protected Mapper<ActiveAwards> getMapper() {
		return activeAwardsMapper;
	}

	

}

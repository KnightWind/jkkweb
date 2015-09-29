package com.jkkp.modules.activities.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.activities.mapper.AwardsMapper;
import com.jkkp.modules.activities.model.Awards;
import com.jkkp.modules.activities.service.IAwardsService;
import com.jkkp.modules.activities.view.VAwards;

@Service("awardsService")
public class AwardsServiceImpl extends ServiceSupport<Awards, VAwards, Integer> implements IAwardsService {

	@Autowired
	private AwardsMapper awardsMapper;
	
	@Override
	protected Mapper<Awards> getMapper() {
		return awardsMapper;
	}

}

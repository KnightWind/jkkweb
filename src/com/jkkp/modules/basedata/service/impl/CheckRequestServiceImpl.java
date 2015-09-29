package com.jkkp.modules.basedata.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.basedata.mapper.CheckRequestMapper;
import com.jkkp.modules.basedata.model.CheckRequest;
import com.jkkp.modules.basedata.service.ICheckRequestService;
import com.jkkp.modules.basedata.view.VCheckRequest;

@Service("checkRequestService")
public class CheckRequestServiceImpl extends ServiceSupport<CheckRequest, VCheckRequest, Integer> implements ICheckRequestService {

	@Autowired
	private CheckRequestMapper checkRequestMapper;

	@Override
	protected Mapper<CheckRequest> getMapper() {
		return checkRequestMapper;
	}

	@Override
	public List<CheckRequest> queryCheck(Integer engineerId, Integer stageId) {
		return checkRequestMapper.queryCheck(engineerId, stageId);
	}

	@Override
	public CheckRequest queryCreate(Integer engineerId, Integer stageId) {
		
		return checkRequestMapper.queryCreate(engineerId, stageId);
	}

}

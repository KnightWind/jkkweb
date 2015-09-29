package com.jkkp.modules.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.modules.mapper.VAgreement;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.order.mapper.AgreementMapper;
import com.jkkp.modules.order.model.Agreement;
import com.jkkp.modules.order.service.IAgreementService;

@Service("agreementService")
public class AgreementServiceImpl extends ServiceSupport<Agreement, VAgreement, Integer> implements IAgreementService {

	
	@Autowired
	private AgreementMapper agreementMapper;
	
	protected Mapper<Agreement> getMapper() {
		return agreementMapper;
	}

	@Override
	public Agreement selectByPushId(Integer pushId) {
		return agreementMapper.selectByPushId(pushId);
	}

}

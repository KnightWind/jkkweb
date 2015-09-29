package com.jkkp.pc.apply.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.apply.mapper.EcshopHomepageApplyMapper;
import com.jkkp.pc.apply.model.EcshopHomepageApply;
import com.jkkp.pc.apply.service.IEcshopHomepageApplyService;
import com.jkkp.pc.apply.view.VEcshopHomepageApply;

@Component("ecshopHomepageApplyService")
public class EcshopHomepageApplyServiceImpl extends ServiceSupport<EcshopHomepageApply, VEcshopHomepageApply, Integer> implements IEcshopHomepageApplyService {

	@Autowired
	private EcshopHomepageApplyMapper ecshopHomepageApplyMapper;
	
	@Override
	protected Mapper<EcshopHomepageApply> getMapper() {
		return ecshopHomepageApplyMapper;
	}

	@Override
	public void insertHomepageApply(EcshopHomepageApply ecshopHomepageApply) {
		// TODO Auto-generated method stub
		ecshopHomepageApplyMapper.insertSelective(ecshopHomepageApply);
	}
	
	

}

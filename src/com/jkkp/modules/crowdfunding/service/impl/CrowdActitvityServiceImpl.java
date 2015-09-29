package com.jkkp.modules.crowdfunding.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.crowdfunding.mapper.CrowdActitvityMapper;
import com.jkkp.modules.crowdfunding.model.CrowdActitvity;
import com.jkkp.modules.crowdfunding.service.ICrowdActitvityService;
import com.jkkp.modules.crowdfunding.view.VCrowdActitvity;

@Service("crowdActitvityService")
public class CrowdActitvityServiceImpl extends ServiceSupport<CrowdActitvity, VCrowdActitvity, Integer>  implements ICrowdActitvityService{

	@Autowired
	private CrowdActitvityMapper crowdActitvityMapper;
	
	@Override
	protected Mapper<CrowdActitvity> getMapper() {
		// TODO Auto-generated method stub
		return crowdActitvityMapper;
	}

}

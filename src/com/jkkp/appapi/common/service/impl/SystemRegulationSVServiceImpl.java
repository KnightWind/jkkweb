package com.jkkp.appapi.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.appapi.common.service.interfaces.ISystemRegulationSVService;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.system.mapper.SystemRegulationMapper;
import com.jkkp.modules.system.model.SystemRegulation;
import com.jkkp.modules.system.view.VSystemRegulation;

@Service("systemRegulationSVService")

public class SystemRegulationSVServiceImpl extends ServiceSupport<SystemRegulation,VSystemRegulation,Integer> implements ISystemRegulationSVService {
    
	@Autowired SystemRegulationMapper systemRegulationMapper;
	
	@Override
	public SystemRegulation fin() {
		
		return systemRegulationMapper.fin();
	}

	@Override
	protected Mapper<SystemRegulation> getMapper() {
		
		return systemRegulationMapper;
	}

	@Override
	public String findtxt(int type) {
		// TODO Auto-generated method stub
		List<SystemRegulation> systemRegulation=this.selectByProperty("type",type);
		if(!systemRegulation.isEmpty())
			return(systemRegulation.get(0).getContent());
		return "";
	}
}

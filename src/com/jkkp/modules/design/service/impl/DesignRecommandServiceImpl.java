package com.jkkp.modules.design.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.design.mapper.DesignRecommandMapper;
import com.jkkp.modules.design.model.DesignRecommand;
import com.jkkp.modules.design.service.IDesignRecommandService;
import com.jkkp.modules.design.view.VDesignRecommand;

@Service("designRecommandService")
public class DesignRecommandServiceImpl extends ServiceSupport<DesignRecommand,VDesignRecommand,Integer> implements IDesignRecommandService {
	@Autowired
	private DesignRecommandMapper designRecommandMapper;
	@Override
	protected Mapper<DesignRecommand> getMapper() {
		return designRecommandMapper;
	}
	@Override
	public List<VDesignRecommand> fin(String name) {
		return designRecommandMapper.fin(name);
	}

}

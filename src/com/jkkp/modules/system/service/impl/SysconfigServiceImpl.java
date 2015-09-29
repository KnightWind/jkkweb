package com.jkkp.modules.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.system.mapper.SysconfigMapper;
import com.jkkp.modules.system.model.Sysconfig;
import com.jkkp.modules.system.service.ISysconfigService;
import com.jkkp.modules.system.view.VSysconfig;

@Service("sysconfigService")
public class SysconfigServiceImpl extends ServiceSupport<Sysconfig, VSysconfig, Integer> implements ISysconfigService {
	@Autowired
	private SysconfigMapper sysconfigMapper;

	@Override
	protected Mapper<Sysconfig> getMapper() {
		return sysconfigMapper;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveOrUpdate(Sysconfig sysconfig) {
		if (sysconfig.getParaCode() != null && sysconfig.getParaCode() > 0) {
			this.update(sysconfig);
		} else {
			this.save(sysconfig);
		}
	}

	public void initConfiguration() {
		List<Sysconfig> datalist = sysconfigMapper.select(null);
		for (Sysconfig entity : datalist) {
			Sysconfig.CONFIG_PARAM.put(entity.getParaName(), entity.getParaValue());
		}
	}
}

package com.jkkp.modules.system.service;

import com.jkkp.common.IService;
import com.jkkp.modules.system.model.Sysconfig;
import com.jkkp.modules.system.view.VSysconfig;

public interface ISysconfigService extends IService<Sysconfig, VSysconfig, Integer> {
	public void saveOrUpdate(Sysconfig sysconfig);

	public void initConfiguration();
}

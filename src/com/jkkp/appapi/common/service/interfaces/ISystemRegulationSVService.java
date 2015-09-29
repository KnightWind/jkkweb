package com.jkkp.appapi.common.service.interfaces;

import com.jkkp.common.IService;
import com.jkkp.modules.system.model.SystemRegulation;
import com.jkkp.modules.system.view.VSystemRegulation;

public interface ISystemRegulationSVService extends IService<SystemRegulation,VSystemRegulation,Integer> {
	public SystemRegulation fin();
	String findtxt(int type);
}

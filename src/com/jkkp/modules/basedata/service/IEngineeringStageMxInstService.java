package com.jkkp.modules.basedata.service;

import java.util.List;

import com.jkkp.common.IService;
import com.jkkp.modules.basedata.model.EngineeringStageMxInst;
import com.jkkp.modules.basedata.view.VEngineeringStageMxInst;

public interface IEngineeringStageMxInstService extends
		IService<EngineeringStageMxInst, VEngineeringStageMxInst, Integer> {
	
	public List<VEngineeringStageMxInst> selectEngineeringStageMxInsts(int id);
	public List<EngineeringStageMxInst> findBySortId(Integer id);
}

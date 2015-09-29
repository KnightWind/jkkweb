package com.jkkp.modules.basedata.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.basedata.mapper.EngineeringStageMxInstMapper;
import com.jkkp.modules.basedata.model.EngineeringStageMxInst;
import com.jkkp.modules.basedata.service.IEngineeringStageMxInstService;
import com.jkkp.modules.basedata.view.VEngineeringStageMxInst;

@Service("engineeringStageMxInstService")
public class EngineeringStageMxInstServiceImpl
		extends
		ServiceSupport<EngineeringStageMxInst, VEngineeringStageMxInst, Integer>
		implements IEngineeringStageMxInstService {

	@Autowired
	private EngineeringStageMxInstMapper engineeringStageMxInstMapper;

	@Override
	public List<VEngineeringStageMxInst> selectEngineeringStageMxInsts(int id) {
		return engineeringStageMxInstMapper.selectEngineeringStageMxInsts(id);
	}

	@Override
	protected Mapper<EngineeringStageMxInst> getMapper() {
		return engineeringStageMxInstMapper;
	}

	@Override
	public List<EngineeringStageMxInst> findBySortId(Integer pid) {
		return this.selectByProperty("pid", pid, "order_by", true);
	}

}

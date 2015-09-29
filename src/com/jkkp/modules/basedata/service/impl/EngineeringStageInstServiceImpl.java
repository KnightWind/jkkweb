package com.jkkp.modules.basedata.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.basedata.mapper.EngineeringStageInstMapper;
import com.jkkp.modules.basedata.model.EngineeringStageInst;
import com.jkkp.modules.basedata.service.IEngineeringStageInstService;
import com.jkkp.modules.basedata.view.VEngineeringStageInst;
import com.jkkp.modules.supplier.service.ISupplierService;

@Service("engineeringStageInstService")
public class EngineeringStageInstServiceImpl extends
		ServiceSupport<EngineeringStageInst, VEngineeringStageInst, Integer>
		implements IEngineeringStageInstService {

	@Autowired
	private ISupplierService supplierService;
	@Autowired
	private EngineeringStageInstMapper engineeringStageInstMapper;

	@Override
	protected Mapper<EngineeringStageInst> getMapper() {
		return engineeringStageInstMapper;
	}

	@Override
	public EngineeringStageInst findInstance(Integer engineerId, Integer id) {
		List<EngineeringStageInst> instList = this.selectByProperty(
				new String[] { "gcdId", "pid" },
				new Object[] { engineerId, id });
		return instList != null && !instList.isEmpty() ? instList.get(0) : null;
	}

	@Override
	@Transactional
	public EngineeringStageInst saveAcceptance(Integer projectId, Date accepanceDate,
			Integer supervisorId, Integer stageId, String stagName) {
		EngineeringStageInst inst = new EngineeringStageInst();
		inst.setGcdId(projectId);
		inst.setStagName(stagName);
		// inst.setFinishFlag(finishFlag);
		inst.setCreateTime(accepanceDate);
		inst.setCreateUser(supplierService.findSupplierName(supervisorId));
		inst.setJlId(supervisorId);
		// inst.setNumber(1);
		// inst.setLastNumber(1);
		inst.setPid(stageId);
		this.save(inst);
		return inst;
	}

}

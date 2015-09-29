package com.jkkp.modules.basedata.service;

import java.util.Date;

import com.jkkp.common.IService;
import com.jkkp.modules.basedata.model.EngineeringStageInst;
import com.jkkp.modules.basedata.view.VEngineeringStageInst;

public interface IEngineeringStageInstService extends IService<EngineeringStageInst, VEngineeringStageInst, Integer> {

	EngineeringStageInst findInstance(Integer engineerId, Integer id);

	EngineeringStageInst saveAcceptance(Integer projectId, Date accepanceDate, Integer supervisorId, Integer stageId, String stagName);

}

package com.jkkp.modules.basedata.service;

import java.util.List;

import com.jkkp.common.IService;
import com.jkkp.modules.basedata.model.CheckRequest;
import com.jkkp.modules.basedata.view.VCheckRequest;

public interface ICheckRequestService extends IService<CheckRequest, VCheckRequest, Integer> {
	List<CheckRequest> queryCheck(Integer engineerId,Integer stageId);
	CheckRequest queryCreate(Integer engineerId,Integer stageId);
}

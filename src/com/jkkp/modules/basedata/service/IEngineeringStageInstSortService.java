package com.jkkp.modules.basedata.service;

import java.util.List;

import com.jkkp.common.IService;
import com.jkkp.modules.basedata.model.EngineeringStageInstSort;
import com.jkkp.modules.basedata.view.VEngineeringStageInstSort;

public interface IEngineeringStageInstSortService extends
		IService<EngineeringStageInstSort, VEngineeringStageInstSort, Integer> {

	List<EngineeringStageInstSort> findParentSort(Integer instanceId);

	List<EngineeringStageInstSort> findByParentId(Integer id);

	void updateContent(Integer id, String content);

}

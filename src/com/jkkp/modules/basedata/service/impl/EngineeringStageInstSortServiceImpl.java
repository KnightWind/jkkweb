package com.jkkp.modules.basedata.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.basedata.mapper.EngineeringStageInstSortMapper;
import com.jkkp.modules.basedata.model.EngineeringStageInstSort;
import com.jkkp.modules.basedata.service.IEngineeringStageInstSortService;
import com.jkkp.modules.basedata.view.VEngineeringStageInstSort;

@Service("engineeringStageInstSortService")
public class EngineeringStageInstSortServiceImpl
		extends
		ServiceSupport<EngineeringStageInstSort, VEngineeringStageInstSort, Integer>
		implements IEngineeringStageInstSortService {

	@Autowired
	private EngineeringStageInstSortMapper engineeringStageInstSortMapper;

	@Override
	protected Mapper<EngineeringStageInstSort> getMapper() {
		return engineeringStageInstSortMapper;
	}

	@Override
	public List<EngineeringStageInstSort> findParentSort(Integer instanceId) {
		return engineeringStageInstSortMapper.findParentSort(instanceId, 0);
	}

	@Override
	public List<EngineeringStageInstSort> findByParentId(Integer parentId) {
		return this.selectByProperty("pid", parentId, "order_by", true);
	}

	@Override
	public void updateContent(Integer id, String content) {
		EngineeringStageInstSort entity = this.findById(id);
		entity.setRemarks(content);
		this.update(entity);
	}

}

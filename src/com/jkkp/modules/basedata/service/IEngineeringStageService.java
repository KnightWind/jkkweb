package com.jkkp.modules.basedata.service;

import java.util.List;

import com.jkkp.common.IService;
import com.jkkp.modules.basedata.model.EngineeringStage;
import com.jkkp.modules.basedata.view.VEngineeringStage;

public interface IEngineeringStageService extends
		IService<EngineeringStage, VEngineeringStage, Integer> {
	//public void saveOrUpdate(EngineeringStage eng);

//	/**
//	 * 改变节点状态    隐藏操作
//	 * 
//	 * @param id
//	 */
//	public void hide(int id);
//	
//	/**
//	 * 改变节点状态       显示操作
//	 * 
//	 * @param id
//	 */
//	public void show(int id);

	public List<EngineeringStage> findStageList();

	public List<EngineeringStage> findByParentId(Integer stageId);
	
	public List<EngineeringStage> selectAllParentStage();
	
	public void saveOrUpdate(EngineeringStage stage);
	
	public void deleteOneStage(int id);
}

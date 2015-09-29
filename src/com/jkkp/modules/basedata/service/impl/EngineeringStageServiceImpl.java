package com.jkkp.modules.basedata.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.basedata.mapper.EngineeringStageMapper;
import com.jkkp.modules.basedata.model.EngineeringStage;
import com.jkkp.modules.basedata.service.IEngineeringStageService;
import com.jkkp.modules.basedata.view.VEngineeringStage;

@Service("engineeringStageService")
public class EngineeringStageServiceImpl extends ServiceSupport<EngineeringStage, VEngineeringStage, Integer> implements
		IEngineeringStageService {

	@Autowired
	private EngineeringStageMapper engineeringStageMapper;

	protected Mapper<EngineeringStage> getMapper() {
		return engineeringStageMapper;
	}

//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
//	public void saveOrUpdate(EngineeringStage eng) {
//		if (eng.getId() != null) {
//			EngineeringStage engHandle = this.findById(eng.getId());
//			engHandle.setCreateTime(new Date());
//			engHandle.setRemark(eng.getRemark());
//			engHandle.setStagDesc(eng.getStagDesc());
//			engHandle.setStagName(eng.getStagName());
//			this.update(engHandle);
//		} else {
//			eng.setCreateTime(new Date());
//			this.save(eng);
//		}
//	}
//
//	public void hide(int id) {
//		engineeringStageMapper.hide(id);
//	}
//
//	public void show(int id) {
//		engineeringStageMapper.show(id);
//	}
//
	@Override
	public List<EngineeringStage> findStageList() {
		return engineeringStageMapper.findStageList(0, 10);
	}

	@Override
	public List<EngineeringStage> findByParentId(Integer parentId) {
		return this.selectByProperty("pid", parentId, "ordr_by", true);
	}

	@Override
	public List<EngineeringStage> selectAllParentStage() {
		return engineeringStageMapper.selectAllParentStage();
	}

	@Transactional
	public void saveOrUpdate(EngineeringStage stage) {
		if(stage.getId()!=null){
			EngineeringStage bean=this.findById(stage.getId());
			bean.setStagName(stage.getStagName());
			bean.setOrdrBy(stage.getOrdrBy());
			bean.setAbbreviation(stage.getAbbreviation());
			this.update(bean);
		}else{
			stage.setRemark(stage.getStagName());
			stage.setStagDesc(stage.getStagName());
			stage.setCreateTime(new Date());
			stage.setStatus(1);
			this.save(stage);
		}
	}

	@Transactional
	public void deleteOneStage(int id) {
		this.deleteById(id);
	}
	
}

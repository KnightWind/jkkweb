package com.jkkp.modules.basedata.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.basedata.mapper.EngineeringStageMxMapper;
import com.jkkp.modules.basedata.model.EngineeringStageMx;
import com.jkkp.modules.basedata.service.IEngineeringStageMxService;
import com.jkkp.modules.basedata.view.VEngineeringStageMx;

@Service("engineeringStageMxService")
public class EngineeringStageMxServiceImpl extends
		ServiceSupport<EngineeringStageMx, VEngineeringStageMx, Integer>
		implements IEngineeringStageMxService {

	@Autowired
	private EngineeringStageMxMapper engineeringStageMxMapper;

	@Override
	protected Mapper<EngineeringStageMx> getMapper() {
		return engineeringStageMxMapper;
	}

	// @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	// public void saveOrUpdate(EngineeringStageMx eng) {
	// if (eng.getId() != null) {
	// EngineeringStageMx engHandle = this.findById(eng.getId());
	// engHandle.setCreateTime(new Date());
	// engHandle.setCreateUser(eng.getCreateUser());
	// engHandle.setMxDesc(eng.getMxDesc());
	// engHandle.setRemark(eng.getRemark());
	// this.update(engHandle);
	// } else {
	// eng.setCreateTime(new Date());
	// this.save(eng);
	// }
	//
	// }

	// public void show(int id) {
	// engineeringStageMxMapper.show(id);
	// }
	//
	// public void hide(int id) {
	// engineeringStageMxMapper.hide(id);
	// }

	@Override
	public List<EngineeringStageMx> findByStageId(Integer id) {
		return this.selectByProperty("pid", id, "ordr_by", true);
	}

	@Transactional
	public void deleteOne(int id) {
		this.deleteById(id);
	}

	@Transactional
	public void saveOrUpdate(EngineeringStageMx stage,
			HttpServletRequest request) {
		if (stage.getId() != null) {
              EngineeringStageMx bean=this.findById(stage.getId());
              bean.setMxDesc(stage.getMxDesc());
              bean.setOrdrBy(stage.getOrdrBy());
              bean.setCheckRule(stage.getCheckRule());
              this.update(bean);
		} else {
			stage.setRemark(stage.getMxDesc());
			stage.setCreateTime(new Date());
			stage.setCreateUser(request.getRemoteUser());
			stage.setStatus(1);
			this.save(stage);
		}
	}

}

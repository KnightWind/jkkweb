package com.jkkp.modules.product.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.product.mapper.PorderTypeMapper;
import com.jkkp.modules.product.model.PorderType;
import com.jkkp.modules.product.service.IPorderTypeService;
import com.jkkp.modules.product.view.VPorderType;

@Service("porderTypeService")
public class PorderTypeServiceImpl extends
		ServiceSupport<PorderType, VPorderType, Integer> implements
		IPorderTypeService {

	@Autowired
	private PorderTypeMapper porderTypeMapper;

	protected Mapper<PorderType> getMapper() {
		return porderTypeMapper;
	}

	@Override
	public void hide(int id) {
		porderTypeMapper.hide(id);

	}

	@Override
	public void show(int id) {
		porderTypeMapper.show(id);

	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveOrUpdate(PorderType pt) {
		if (pt.getId() != null) {
			PorderType ptHandle = this.findById(pt.getId());
			ptHandle.setCreateTime(new Date());
			ptHandle.setCreateUser(pt.getCreateUser());
			ptHandle.setRemark(pt.getRemark());
			ptHandle.setTypeName(pt.getTypeName());
			this.update(ptHandle);
		} else {
			pt.setCreateTime(new Date());
			this.save(pt);
		}

	}

}

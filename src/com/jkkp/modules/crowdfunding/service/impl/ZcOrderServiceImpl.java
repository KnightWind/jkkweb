package com.jkkp.modules.crowdfunding.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.jkkp.common.ServiceSupport;
import com.jkkp.modules.crowdfunding.mapper.ActivtyOrderMapper;
import com.jkkp.modules.crowdfunding.model.ActiivityOrder;
import com.jkkp.modules.crowdfunding.service.IZcOrderService;
import com.jkkp.modules.crowdfunding.view.VItemOrder;

@Service("zcItemOrderService")
public class ZcOrderServiceImpl extends ServiceSupport<ActiivityOrder, VItemOrder, Integer> implements
		IZcOrderService {

	@Autowired
	private ActivtyOrderMapper activtyOrderMapper;
	
	@Override
	protected Mapper<ActiivityOrder> getMapper() {
		return activtyOrderMapper;
	}

	@Override
	public Double selectJSTotal(int spId) {
		return activtyOrderMapper.selectJSTotal(spId);
	}

	@Override
	public void updateZcOrderExpressToOne(Integer id) {
		activtyOrderMapper.updateZcOrderExpressToOne(id);
	}

	@Override
	public void updateZcOrderExpressToTwo(Integer id) {
		activtyOrderMapper.updateZcOrderExpressToTwo(id);		
	}

	
}

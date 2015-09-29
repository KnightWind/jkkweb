package com.jkkp.modules.crowdfunding.service;

import java.util.List;

import com.jkkp.common.IService;
import com.jkkp.modules.crowdfunding.model.JlService;
import com.jkkp.modules.crowdfunding.view.VJlService;

public interface IJlService extends IService<JlService, VJlService, Integer> {
	public void addOne(JlService bean);

	public List<JlService> hasBuyJLService(String phone);
	
	public JlService getByOrderCode(String orderCode);
	
	//发货
	public void updateInfo(Integer id);
}

package com.jkkp.modules.crowdfunding.service;

import com.jkkp.common.IService;
import com.jkkp.modules.crowdfunding.model.ActiivityOrder;
import com.jkkp.modules.crowdfunding.view.VItemOrder;

public interface IZcOrderService extends IService<ActiivityOrder, VItemOrder, Integer> {
	public Double selectJSTotal(int spId);
	
	public void updateZcOrderExpressToOne(Integer id);
	
	public void updateZcOrderExpressToTwo(Integer id);
}

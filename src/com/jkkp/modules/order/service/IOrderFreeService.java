package com.jkkp.modules.order.service;

import java.util.List;

import com.jkkp.common.IService;
import com.jkkp.modules.order.model.OrderFree;
import com.jkkp.modules.order.view.VOrderFree;

public interface IOrderFreeService extends
		IService<OrderFree, VOrderFree, Integer> {
	public List<VOrderFree> selectUserOrderFree(int id);
}

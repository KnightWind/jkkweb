package com.jkkp.modules.order.service;

import com.jkkp.common.IService;
import com.jkkp.modules.order.model.OrderId;
import com.jkkp.modules.order.view.VOrderId;

public interface IOrderIdService extends IService<OrderId, VOrderId, Integer> {
	public VOrderId findOneById(int id);
}

package com.jkkp.modules.order.service;

import java.util.List;

import com.jkkp.common.IService;
import com.jkkp.modules.order.model.OrderPackageItem;
import com.jkkp.modules.order.view.VOrderPackageItem;

public interface IOrderPackageItemService extends IService<OrderPackageItem, VOrderPackageItem, Integer> {
	public List<VOrderPackageItem> orderItemDetail(int id);
}

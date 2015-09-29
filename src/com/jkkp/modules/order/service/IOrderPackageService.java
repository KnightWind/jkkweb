package com.jkkp.modules.order.service;

import java.util.List;

import com.jkkp.common.IService;
import com.jkkp.modules.order.model.OrderPackage;
import com.jkkp.modules.order.view.VOrderPackage;

public interface IOrderPackageService extends IService<OrderPackage, VOrderPackage, Integer> {
	public List<VOrderPackage> selectAllUserOrderPackage(int id);
	public List<VOrderPackage> selectAllUserOrderPackageItem(int id);
}

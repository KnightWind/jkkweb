package com.jkkp.modules.supplier.service;

import java.util.List;

import com.jkkp.common.IService;
import com.jkkp.modules.supplier.model.SupplierLevel;
import com.jkkp.modules.supplier.view.VSupplierLevel;

public interface ISupplierLevelService extends IService<SupplierLevel, VSupplierLevel,Integer> {
	 public List<SupplierLevel> findAll();
	 public SupplierLevel saveOne(Integer levelMoney);
}

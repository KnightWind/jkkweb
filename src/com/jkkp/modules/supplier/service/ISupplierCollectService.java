package com.jkkp.modules.supplier.service;

import com.jkkp.common.IService;
import com.jkkp.modules.supplier.model.SupplierCollect;
import com.jkkp.modules.supplier.view.VSupplierCollect;

public interface ISupplierCollectService extends IService<SupplierCollect,VSupplierCollect,Integer>{
	public void saveOrUpdate(SupplierCollect supplierCollect);
}

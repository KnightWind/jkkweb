package com.jkkp.modules.supplier.service;

import com.jkkp.common.IService;
import com.jkkp.modules.supplier.model.SupplierComplain;
import com.jkkp.modules.supplier.view.VSupplierComplain;

public interface ISupplierComplainService extends
		IService<SupplierComplain, VSupplierComplain, Integer> {
	public void close(int id);

	public VSupplierComplain complainDetail(int id);
}

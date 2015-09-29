package com.jkkp.modules.supplier.service;

import com.jkkp.common.IService;
import com.jkkp.modules.supplier.model.SupplierCompany;
import com.jkkp.modules.supplier.view.VSupplierCompany;

public interface ISupplierCompanyService extends IService<SupplierCompany,VSupplierCompany,Integer>{
	public SupplierCompany fin(Integer id);
	public void saveUpdate(SupplierCompany supplierCompany);
}

package com.jkkp.modules.supplier.service;

import com.jkkp.common.IService;
import com.jkkp.modules.supplier.model.SupplierComplainDetails;
import com.jkkp.modules.supplier.view.VSupplierComplaintsDetails;

public interface ISupplierComplaintsDetailsService
		extends
		IService<SupplierComplainDetails, VSupplierComplaintsDetails, Integer> {
    public void saveOne(SupplierComplainDetails s);
    public void saveOneSU(SupplierComplainDetails s);
}

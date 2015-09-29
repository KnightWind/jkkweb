package com.jkkp.appapi.common.service.interfaces;

import java.util.Map;

import com.jkkp.common.IService;
import com.jkkp.modules.supplier.model.Supplier;
import com.jkkp.modules.supplier.model.SupplierUser;
import com.jkkp.modules.supplier.view.VSupplierUser;

public interface ISupplierUserSV extends IService<SupplierUser, VSupplierUser, Integer>{

	SupplierUser findByUserName(Map<String, Object> map);

}

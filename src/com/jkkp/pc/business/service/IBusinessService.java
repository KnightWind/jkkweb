package com.jkkp.pc.business.service;

import com.jkkp.common.IService;
import com.jkkp.modules.supplier.model.Supplier;
import com.jkkp.pc.business.view.VBusiness;

public interface IBusinessService extends IService<Supplier, VBusiness, Integer>{

	//List<VBusiness> selectBusinessList();

	VBusiness selectBusiness(Integer id);

}

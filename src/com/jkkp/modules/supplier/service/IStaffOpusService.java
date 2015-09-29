package com.jkkp.modules.supplier.service;
import javax.servlet.http.HttpServletRequest;

import com.jkkp.common.IService;
import com.jkkp.modules.supplier.model.StaffOpus;
import com.jkkp.modules.supplier.view.VStaffOpus;
import com.jkkp.modules.supplier.view.VSupplierUser;

public interface IStaffOpusService extends IService<StaffOpus,VStaffOpus,Integer>{

	VStaffOpus getVStaffOpusById(Integer id);

	void savaOrUpdate(VSupplierUser su, StaffOpus opus, Integer[] imgId,
			HttpServletRequest request);
}

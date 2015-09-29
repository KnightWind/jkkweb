package com.jkkp.pc.staff.service;

import java.util.List;

import com.jkkp.common.IService;
import com.jkkp.modules.supplier.model.SupplierCompanyStaff;
import com.jkkp.pc.staff.view.VStaff;

public interface IStaffService extends IService<SupplierCompanyStaff, VStaff, Integer> {

	List<VStaff> query4Staff(Integer id);

}

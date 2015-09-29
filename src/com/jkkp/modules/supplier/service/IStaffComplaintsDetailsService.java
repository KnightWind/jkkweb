package com.jkkp.modules.supplier.service;

import com.jkkp.common.IService;
import com.jkkp.modules.supplier.model.StaffComplainDetails;
import com.jkkp.modules.supplier.view.VStaffComplaintsDetails;

public interface IStaffComplaintsDetailsService extends
		IService<StaffComplainDetails, VStaffComplaintsDetails, Integer> {
   public void saveOne(StaffComplainDetails staff);
}

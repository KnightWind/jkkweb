package com.jkkp.modules.supplier.service;

import com.jkkp.common.IService;
import com.jkkp.modules.supplier.model.StaffComplain;
import com.jkkp.modules.supplier.view.VStaffComplain;

public interface IStaffComplainService extends
		IService<StaffComplain, VStaffComplain, Integer> {
   public void close(int id);
   public VStaffComplain detail(int id);
}

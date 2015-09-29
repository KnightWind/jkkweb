package com.jkkp.appapi.common.service.interfaces;

import com.jkkp.appapi.modules.mapper.VIStaff;
import com.jkkp.common.IService;
import com.jkkp.modules.supplier.model.SupplierCompanyStaff;


public interface VIStaffSV  extends IService<SupplierCompanyStaff,VIStaff,Integer>{
    public VIStaff  getById(Integer sid);
    
}

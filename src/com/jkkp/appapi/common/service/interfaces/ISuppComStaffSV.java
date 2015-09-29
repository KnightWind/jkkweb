package com.jkkp.appapi.common.service.interfaces;

import java.util.List;
import java.util.Map;

import com.jkkp.common.IService;
import com.jkkp.modules.supplier.model.SupplierCompanyStaff;
import com.jkkp.modules.supplier.view.VSupplierCompanyStaff;

public interface ISuppComStaffSV  extends IService<SupplierCompanyStaff,VSupplierCompanyStaff,Integer> {

	List<SupplierCompanyStaff> queryDesignStaffBySpId(Map<String, Object> map);

}

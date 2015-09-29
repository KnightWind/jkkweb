package com.jkkp.modules.supplier.view;

import com.jkkp.modules.supplier.model.SupplierCompanyStaff;
import com.jkkp.utils.DateUtil;

public class SupplierCompanyStaffInfo extends SupplierCompanyStaff {
		
	private String supplierPosition;
	private String spName;
	public String getSpName() {
		return spName;
	}

	public void setSpName(String spName) {
		this.spName = spName;
	}

	public String getSupplierPosition() {
		return supplierPosition;
	}

	public void setSupplierPosition(String supplierPosition) {
		this.supplierPosition = supplierPosition;
	}
	
	public String getCreateTimeString(){
		if(createTime != null)
			return DateUtil.formatDateTime(createTime);
		return "";
	}
	
}

package com.jkkp.modules.supplier.view;

import com.jkkp.modules.supplier.model.SupplierUser;

public class VSupplierUser extends SupplierUser {
	public VSupplier supplier=null;
	public String spname;
	public int type;
	
	
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getSpname() {
		return spname;
	}

	public void setSpname(String spname) {
		this.spname = spname;
	}

	public VSupplier getSupplier() {
		return supplier;
	}

	public void setSupplier(VSupplier supplier) {
		this.supplier = supplier;
	}
	

}

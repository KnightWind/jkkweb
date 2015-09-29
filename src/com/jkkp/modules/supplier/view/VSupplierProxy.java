package com.jkkp.modules.supplier.view;

import com.jkkp.modules.supplier.model.SupplierProxy;

public class VSupplierProxy extends SupplierProxy{
	public String sname;
	public String uname;
	public String phot;
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPhot() {
		return phot;
	}
	public void setPhot(String phot) {
		this.phot = phot;
	}
}

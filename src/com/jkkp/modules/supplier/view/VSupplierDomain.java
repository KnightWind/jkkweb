package com.jkkp.modules.supplier.view;

import com.jkkp.modules.supplier.model.SupplierDomain;

public class VSupplierDomain extends SupplierDomain {
	public String sname;
	public String uname;
	public String type;
	public String phto;
	public String name;
	public Integer splid;

	public Integer getSplid() {
		return splid;
	}
	public void setSplid(Integer splid) {
		this.splid = splid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPhto() {
		return phto;
	}
	public void setPhto(String phto) {
		this.phto = phto;
	}
}

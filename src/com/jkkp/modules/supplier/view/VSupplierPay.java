package com.jkkp.modules.supplier.view;

import com.jkkp.modules.supplier.model.SupplierPay;

public class VSupplierPay extends SupplierPay {
     private String spName;
     private String contactUser;
     private String contactMobile;
	public String getSpName() {
		return spName;
	}
	public void setSpName(String spName) {
		this.spName = spName;
	}
	public String getContactUser() {
		return contactUser;
	}
	public void setContactUser(String contactUser) {
		this.contactUser = contactUser;
	}
	public String getContactMobile() {
		return contactMobile;
	}
	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}
}

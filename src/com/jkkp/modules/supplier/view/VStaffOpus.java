package com.jkkp.modules.supplier.view;

import com.jkkp.modules.supplier.model.StaffOpus;

public class VStaffOpus extends StaffOpus {
	
	/** 设计师名  **/
	private String designerName;
	/** 装修公司名称 **/
	private String spName;
	
	public String getDesignerName() {
		return designerName;
	}
	public void setDesignerName(String designerName) {
		this.designerName = designerName;
	}
	public String getSpName() {
		return spName;
	}
	public void setSpName(String spName) {
		this.spName = spName;
	}
	
	
}

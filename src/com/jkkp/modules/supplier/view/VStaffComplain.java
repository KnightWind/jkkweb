package com.jkkp.modules.supplier.view;

import com.jkkp.modules.supplier.model.StaffComplain;

public class VStaffComplain extends StaffComplain {
	private String mnickName;
	private String spName;
	private String anickName;
	private String smobile;
	private String mmobile;

	public String getSmobile() {
		return smobile;
	}

	public void setSmobile(String smobile) {
		this.smobile = smobile;
	}

	public String getMmobile() {
		return mmobile;
	}

	public void setMmobile(String mmobile) {
		this.mmobile = mmobile;
	}

	public String getMnickName() {
		return mnickName;
	}

	public void setMnickName(String mnickName) {
		this.mnickName = mnickName;
	}

	public String getSpName() {
		return spName;
	}

	public void setSpName(String spName) {
		this.spName = spName;
	}

	public String getAnickName() {
		return anickName;
	}

	public void setAnickName(String anickName) {
		this.anickName = anickName;
	}
}

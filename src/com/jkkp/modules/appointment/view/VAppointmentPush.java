package com.jkkp.modules.appointment.view;

import com.jkkp.modules.appointment.model.AppointmentPush;

public class VAppointmentPush extends AppointmentPush {
	public String name;
	public String uname;

	private String spName;
	private String nickName;

	private String community;
	private Float space;
	private String method;
	private Float budget;
	
	
	private Integer descnt;
	
	
	public Integer getDescnt() {
		return descnt;
	}

	public void setDescnt(Integer descnt) {
		this.descnt = descnt;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public Float getSpace() {
		return space;
	}

	public void setSpace(Float space) {
		this.space = space;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Float getBudget() {
		return budget;
	}

	public void setBudget(Float budget) {
		this.budget = budget;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getSpName() {
		return spName;
	}

	public void setSpName(String spName) {
		this.spName = spName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}
}

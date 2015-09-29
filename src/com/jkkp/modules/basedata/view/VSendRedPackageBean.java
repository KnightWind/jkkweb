package com.jkkp.modules.basedata.view;

public class VSendRedPackageBean {
	
	private Integer mid;
	private Integer rpid;
	private Integer gender;
	private String nickname;
	private String phone;
	private String title;
	private String decorate;
	private String type;
	private Integer takeCount;
	private Double takeMoney;
	private String adminName;
	
	
	public String getGenderString(){
		if(gender != null){
			switch (gender) {
			case 1:
				return "先生";
			case 2:
				return "女士";
			default:
				return "保密";
			}
		}
		return "保密";
	}
	
	
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	public Integer getRpid() {
		return rpid;
	}
	public void setRpid(Integer rpid) {
		this.rpid = rpid;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDecorate() {
		return decorate;
	}
	public void setDecorate(String decorate) {
		this.decorate = decorate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getTakeCount() {
		return takeCount;
	}
	public void setTakeCount(Integer takeCount) {
		this.takeCount = takeCount;
	}
	public Double getTakeMoney() {
		return takeMoney;
	}
	public void setTakeMoney(Double takeMoney) {
		this.takeMoney = takeMoney;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	
	
}

package com.jkkp.modules.supplier.view;

public class VSupplierBaseInfo {
	
	private String spName;
	private String address;
	private String primaryBusiness;
	private String abbreviation;
	private Integer personNum;
	private String introduction;
	private String headimg;
	public VSupplierBaseInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VSupplierBaseInfo(String spName, String address,
			String primaryBusiness, String abbreviation, Integer personNum,String introduction,String img) {
		super();
		this.spName = spName;
		this.address = address;
		this.primaryBusiness = primaryBusiness;
		this.abbreviation = abbreviation;
		this.personNum = personNum;
		this.introduction=introduction;
		this.headimg=img;
	}
	public Integer getPersonNum() {
		return personNum;
	}
	public void setPersonNum(Integer personNum) {
		this.personNum = personNum;
	}
	public String getSpName() {
		return spName;
	}
	public void setSpName(String spName) {
		this.spName = spName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPrimaryBusiness() {
		return primaryBusiness;
	}
	public void setPrimaryBusiness(String primaryBusiness) {
		this.primaryBusiness = primaryBusiness;
	}
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getHeadimg() {
		return headimg;
	}
	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}
	
	
	
}	

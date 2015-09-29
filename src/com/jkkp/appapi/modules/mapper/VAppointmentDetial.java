package com.jkkp.appapi.modules.mapper;

import com.jkkp.modules.appointment.model.Appointment;

public class VAppointmentDetial extends Appointment{
	private String RegionName;//地区名字 广东广州番禺
	private String ServiceMethonName;//服务方式 全包半包
	private String HouseStatus;//房屋状态 新房旧房
	private String FitmentRange;//装修范围 整装局部
	private String HousePurpose;//装修用途 儿童房
	private String houseStyleS;//装修风格 简欧
	private String zxTimestring;//装修风格 简欧
	private String reviewTimestring;//装修风格 简欧
	private int ungrabno;//
	
	public int getUngrabno() {
		return ungrabno;
	}
	public void setUngrabno(int ungrabno) {
		this.ungrabno = ungrabno;
	}
	public String getZxTimestring() {
		return zxTimestring;
	}
	public void setZxTimestring(String zxTimestring) {
		this.zxTimestring = zxTimestring;
	}
	public String getReviewTimestring() {
		return reviewTimestring;
	}
	public void setReviewTimestring(String reviewTimestring) {
		this.reviewTimestring = reviewTimestring;
	}
	public String getRegionName() {
		return RegionName;
	}
	public void setRegionName(String regionName) {
		RegionName = regionName;
	}
	public String getServiceMethonName() {
		return ServiceMethonName;
	}
	public void setServiceMethonName(String serviceMethonName) {
		ServiceMethonName = serviceMethonName;
	}
	public String getHouseStatus() {
		return HouseStatus;
	}
	public void setHouseStatus(String houseStatus) {
		HouseStatus = houseStatus;
	}
	public String getFitmentRange() {
		return FitmentRange;
	}
	public void setFitmentRange(String fitmentRange) {
		FitmentRange = fitmentRange;
	}
	public String getHousePurpose() {
		return HousePurpose;
	}
	public void setHousePurpose(String housePurpose) {
		HousePurpose = housePurpose;
	}
	public String getHouseStyleS() {
		return houseStyleS;
	}
	public void setHouseStyleS(String houseStyleS) {
		this.houseStyleS = houseStyleS;
	}
	
}

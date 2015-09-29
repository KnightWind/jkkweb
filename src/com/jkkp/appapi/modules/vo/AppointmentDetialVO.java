package com.jkkp.appapi.modules.vo;

import java.util.Date;


public class AppointmentDetialVO{
	
	public Integer id;
	
	/** 联系电话*/
	public String mobile;

	/** 预约地址*/
	public String address;
	
	/** 建筑面积:1.60平以下，2.60-90，3.90-120，4.120-150，5.150-200，6.200以上 */
	public Float space;
	
	/** 装修预算：10万以下，2.10-20，3.20-30，4.30-50，5.50-100，6.100以上*/
	public Float budget;
	
	/** 装修方式1.半包2.全包*/
	public Integer method;
	
	/*** 所在小区*/
	public String community;
	
	/*** 房屋用途 1出租2自用3婚房4儿童房5会所6工装(可以多选)7其他*/
	public String houseType;
	
	/** 房子户型*/
	public Integer housestyle;
	
	/** 地区ID*/
	public Integer regionid;
	
	/** 地区名称*/
	private String regionName;
	
	/** 浏览预约时间*/
	public Date reviewTime;
	
	/**用户提交的量房参考时间*/
	private String reviewTimestring;
	/**
	 * 预约状态：0流单1草稿10待抢单20待量房30已量房待报价40已提交方案待签约
	 */
	private String statusName;
	/**
	 * 新/旧房 1新房 2老房
	 */
	private Integer suType;
	/**
	 * 预约类型1预约量房2预约看工地3:微信活动 5.微信引流活动提交的预约单 6.pc web提交预约
	 */
	private Integer  type;
	
	/*** 水路改造：0不改造1局部改造2全面改*/
	private Integer water;
	
	/** 整装/局部  1整装 2 局部*/
	private Integer wholeHouse; 
	/**装修风格 */
	private String houseStyleS;
	/**装修时间*/
	private String zxTimestring;

	private int ungrabno;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Float getSpace() {
		return space;
	}
	public void setSpace(Float space) {
		this.space = space;
	}
	public Float getBudget() {
		return budget;
	}
	public void setBudget(Float budget) {
		this.budget = budget;
	}
	public Integer getMethod() {
		return method;
	}
	public void setMethod(Integer method) {
		this.method = method;
	}
	public String getCommunity() {
		return community;
	}
	public void setCommunity(String community) {
		this.community = community;
	}
	public String getHouseType() {
		return houseType;
	}
	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}
	public Integer getHousestyle() {
		return housestyle;
	}
	public void setHousestyle(Integer housestyle) {
		this.housestyle = housestyle;
	}
	public Integer getRegionid() {
		return regionid;
	}
	public void setRegionid(Integer regionid) {
		this.regionid = regionid;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public Date getReviewTime() {
		return reviewTime;
	}
	public void setReviewTime(Date reviewTime) {
		this.reviewTime = reviewTime;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public Integer getSuType() {
		return suType;
	}
	public void setSuType(Integer suType) {
		this.suType = suType;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getWater() {
		return water;
	}
	public void setWater(Integer water) {
		this.water = water;
	}
	public Integer getWholeHouse() {
		return wholeHouse;
	}
	public void setWholeHouse(Integer wholeHouse) {
		this.wholeHouse = wholeHouse;
	}
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
	public String getHouseStyleS() {
		return houseStyleS;
	}
	public void setHouseStyleS(String houseStyleS) {
		this.houseStyleS = houseStyleS;
	}
	
}

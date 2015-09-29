package com.jkkp.appapi.modules.vo;

public class EngineeringsListVO {
	public Integer id;							//主键ID
	public String community;					//所在小区
	public Float space;                         //面积
	public Integer appPushId;					//推送ID
	public String spName;						//公司名称
	public String huxing;						//户型
	public String baojia;						//报价
	public String address;						//地址详情  
	public String method2Val;					//半包全包1半包或2全包
	public String pointx;						//横坐标
	public String pointy;						//纵坐标
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Integer getAppPushId() {
		return appPushId;
	}
	public void setAppPushId(Integer appPushId) {
		this.appPushId = appPushId;
	}
	public String getSpName() {
		return spName;
	}
	public void setSpName(String spName) {
		this.spName = spName;
	}
	public String getHuxing() {
		return huxing;
	}
	public void setHuxing(String huxing) {
		this.huxing = huxing;
	}
	public String getBaojia() {
		return baojia;
	}
	public void setBaojia(String baojia) {
		this.baojia = baojia;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMethod2Val() {
		if("1".equals(method2Val)) method2Val="半包";
		else if("2".equals(method2Val)) method2Val="全包";
		else method2Val=method2Val==null?"":method2Val.trim();
		return method2Val;
	}
	public void setMethod2Val(String method2Val) {
		this.method2Val = method2Val;
	}
	public String getPointx() {
		return pointx;
	}
	public void setPointx(String pointx) {
		this.pointx = pointx;
	}
	public String getPointy() {
		return pointy;
	}
	public void setPointy(String pointy) {
		this.pointy = pointy;
	}
	
}

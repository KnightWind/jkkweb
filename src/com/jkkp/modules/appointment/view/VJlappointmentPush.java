package com.jkkp.modules.appointment.view;

import java.util.Date;

import com.jkkp.modules.appointment.model.JlappointmentPush;
import com.jkkp.modules.supplier.view.VSupplier;

public class VJlappointmentPush extends JlappointmentPush {
	public VJlappointment jlappointment=null;
	public VSupplier supplier=null;
	public String name;
	public String uname;
	public String spName;
	public String nickName;
	public Integer wholeHouse,houseType,method,space;
	public String community;		//小区名称【金科观天下】
	public String user;				//装修业主
	public String cateName;			//风格【装修需求】
	public String payment;			//报价【装修需求】
	public String address;			//装修地址
	public Date zxTime;				//装修时间
	public String suType,spaceVal;	//面积【装修需求】
	public String suTypeVal;		//新旧房【房屋属性】
	public String wholeHouseVal;	//整装/局部【房屋属性】
	public String houseTypeVal;		//房屋用途【房屋属性】
	public String methodVal;		//装修方式【装修需求】
	public String statusName;
	public String pointx;
	public String pointy;
	public Date smTime;       //上门时间
	   
    public Date getSmTime() {
		return smTime;
	}
	public void setSmTime(Date smTime) {
		this.smTime = smTime;
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
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getStatusName() {
    	if(status==null) return "";
    	switch (status) {
			case 0:statusName="待应单";break;
			case 10:statusName="已应单";break;
			case 11:statusName="取消预约";break;
			case 20:statusName="待选定";break;
			default:statusName=status.toString();break;
		}return statusName;
	}
	public String getHouseTypeVal() {
		if(houseType==null) return "";
		switch (houseType) {
			case 1:houseTypeVal="出租";break;
			case 2:houseTypeVal="自用";break;
			case 3:houseTypeVal="婚房";break;
			case 4:houseTypeVal="儿童房";break;
			case 5:houseTypeVal="会所";break;
			case 6:houseTypeVal="工装";break;
			case 7:houseTypeVal="其他";break;
			default:houseTypeVal=houseType.toString();break;
		}
		return houseTypeVal;
	}
	public String getWholeHouseVal() {
		if(wholeHouse==null) return "";
		switch (wholeHouse) {
			case 1:wholeHouseVal="整装";break;
			case 2:wholeHouseVal="局部";break;
			default:wholeHouseVal=wholeHouse.toString();break;
		}
		return wholeHouseVal;
	}
	public VJlappointment getJlappointment() {
		return jlappointment;
	}
	public void setJlappointment(VJlappointment jlappointment) {
		this.jlappointment = jlappointment;
	}
	public VSupplier getSupplier() {
		return supplier;
	}
	public void setSupplier(VSupplier supplier) {
		this.supplier = supplier;
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
	public String getSpName() {
		return spName;
	}
	public void setSpName(String spName) {
		this.spName = spName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Integer getWholeHouse() {
		return wholeHouse;
	}
	public void setWholeHouse(Integer wholeHouse) {
		this.wholeHouse = wholeHouse;
	}
	public Integer getHouseType() {
		return houseType;
	}
	public void setHouseType(Integer houseType) {
		this.houseType = houseType;
	}
	public String getSuType() {
		return suType;
	}
	public void setSuType(String suType) {
		this.suType = suType;
	}
	public Integer getMethod() {
		return method;
	}
	public void setMethod(Integer method) {
		this.method = method;
	}
	public Integer getSpace() {
		return space;
	}
	public void setSpace(Integer space) {
		this.space = space;
	}
	public String getCommunity() {
		return community;
	}
	public void setCommunity(String community) {
		this.community = community;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getZxTime() {
		return zxTime;
	}
	public void setZxTime(Date zxTime) {
		this.zxTime = zxTime;
	}
	public void setSpaceVal(String spaceVal) {
		this.spaceVal = spaceVal;
	}
	public void setSuTypeVal(String suTypeVal) {
		this.suTypeVal = suTypeVal;
	}
	public void setWholeHouseVal(String wholeHouseVal) {
		this.wholeHouseVal = wholeHouseVal;
	}
	public void setMethodVal(String methodVal) {
		this.methodVal = methodVal;
	}
	public void setHouseTypeVal(String houseTypeVal) {
		this.houseTypeVal = houseTypeVal;
	}

	public String getSpaceVal() {
		if(space==null) return "";
		return space.toString();
	}
	public String getSuTypeVal() {
		if("1".equals(suType))suTypeVal="新房";
		else if("2".equals(suType))suTypeVal="老房";
		else suTypeVal=suType==null?"":suType.trim();
		return suTypeVal;
	}

	public String getMethodVal() {
		if(method==null) return "";
		switch (method) {
			case 1:methodVal="半包";break;
			case 2:methodVal="全包";break;
			default:methodVal=method.toString();break;
		}return methodVal;
	}
}

package com.jkkp.modules.appointment.view;

import com.jkkp.modules.appointment.model.*;
import com.jkkp.modules.basedata.view.*;

public class VJlappointment extends Jlappointment {
	public String vCommunity;
	public String nickname;
	public String cateName;			//装修风格
	public String methodVal;		//装修方式
	public String spaceVal;			//面积
	public String suTypeVal;		//新/旧房 1新房 2老房
	public String wholeHouseVal;	//整装/局部  1整装 2 局部
	public String houseTypeVal;		//房屋用途 1出租2自用3婚房4儿童房5会所6工装
	public VEngineerings engineerings = null;
	
	public String getHouseTypeVal() {
		if(houseType==null) return "";
		switch (houseType) {
			case 1:houseTypeVal="出租";break;
			case 2:houseTypeVal="自用";break;
			case 3:houseTypeVal="婚房";break;
			case 4:houseTypeVal="儿童房";break;
			case 5:houseTypeVal="会所";break;
			case 6:houseTypeVal="工装";break;
			default:houseTypeVal=houseType.toString();break;
		}return houseTypeVal;
	}
	public void setHouseTypeVal(String houseTypeVal) {
		this.houseTypeVal = houseTypeVal;
	}
	public String getWholeHouseVal() {
		if(wholeHouse==null) return "";
		switch (wholeHouse) {
			case 1:wholeHouseVal="整装";break;
			case 2:wholeHouseVal="局部";break;
			default:wholeHouseVal=wholeHouse.toString();break;
		}return wholeHouseVal;
	}
	public void setWholeHouseVal(String wholeHouseVal) {
		this.wholeHouseVal = wholeHouseVal;
	}
	public String getSuTypeVal() {
		if(suType==null) return "";
		switch (suType) {
			case 1:suTypeVal="新房";break;
			case 2:suTypeVal="老房";break;
			default:suTypeVal=suType.toString();break;
		}return suTypeVal;
	}
	public void setSuTypeVal(String suTypeVal) {
		this.suTypeVal = suTypeVal;
	}

	public String getSpaceVal() {
		if(space==null) return "";
		switch (space.intValue()) {
			case 1:spaceVal="60平以下";break;
			case 2:spaceVal="60-90";break;
			case 3:spaceVal="90-120";break;
			case 4:spaceVal="120-150";break;
			case 5:spaceVal="150-200";break;
			case 6:spaceVal="200以上";break;
			default:spaceVal=space.toString();break;
		}return spaceVal;
	}

	public void setSpaceVal(String spaceVal) {
		this.spaceVal = spaceVal;
	}

	public String getMethodVal() {
		if(method==null) return "";
		switch (method) {
			case 1:methodVal="半包";break;
			case 2:methodVal="全包";break;
			default:methodVal=method.toString();break;
		}return methodVal;
	}

	public void setMethodVal(String methodVal) {
		this.methodVal = methodVal;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getvCommunity() {
		return vCommunity;
	}

	public void setvCommunity(String vCommunity) {
		this.vCommunity = vCommunity;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public VEngineerings getEngineerings() {
		return engineerings;
	}

	public void setEngineerings(VEngineerings engineerings) {
		this.engineerings = engineerings;
	}

}

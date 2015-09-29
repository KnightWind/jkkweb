package com.jkkp.modules.appointment.view;

import java.util.Date;

import com.jkkp.appapi.common.control.common.CommonDicData;
import com.jkkp.modules.appointment.model.Appointment;
import com.jkkp.modules.basedata.view.VEngineerings;
import com.jkkp.modules.design.view.VDesign;
import com.jkkp.utils.DateUtil;

public class VAppointment extends Appointment {
	public String admin;
	public Integer zt;
	public String nickname;
	public String cateName;		//装修风格
	public String wholeHouseVal;//整部局部
	public String regionname;
	public String spId;			//商家id
	public String spName;		//商家名称
	public String statusName;
	public String createDate;
	public String createUser;
	public String spaceVal;			//面积
	public String methodVal;		//装修方式【1.半包2.全包】
	public String suTypeVal;		//预约类型：1新房装修2旧房翻新
	public String houseTypeVal;		//房屋用途 1出租2自用3婚房4儿童房5会所6工装
	public Double jgamt;			//
	public Date endTime;			//竣工时间
	public Integer designId;			//装修方案ID
	public VEngineerings engineerings=null;
	public VDesign design;

	public VDesign getDesign() {
		return design;
	}
	public void setDesign(VDesign design) {
		this.design = design;
	}
	public VEngineerings getEngineerings() {
		return engineerings;
	}
	public void setEngineerings(VEngineerings engineerings) {
		this.engineerings = engineerings;
	}
	public Integer getDesignId() {
		return designId;
	}
	public void setDesignId(Integer designId) {
		this.designId = designId;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Double getJgamt() {
		if(budget==null) return null;
		jgamt=budget*0.2;
//		format(aa.budget*0.2,2) as jgamt,
		return jgamt;
	}
	public void setJgamt(Double jgamt) {
		this.jgamt = jgamt;
	}
	public String getHouseTypeVal() {
		if(houseType==null) return "";
		switch (Integer.parseInt(houseType)) {
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
	public String getSuTypeVal() {
		if(suType==null) return "";
		switch (suType) {
			case 1:suTypeVal="新房装修";break;
			case 2:suTypeVal="旧房翻新";break;
			default:suTypeVal=suType.toString();break;
		}return suTypeVal;
	}

	public void setSuTypeVal(String suTypeVal) {
		this.suTypeVal = suTypeVal;
	}

	public void setMethodVal(String methodVal) {
		this.methodVal = methodVal;
	}

	public String getMethodVal() {
		if(method==null) return "";
		switch (method) {
			case 1:methodVal="半包";break;
			case 2:methodVal="全包";break;
			default:methodVal=method.toString();break;
		}return methodVal;
	}
	public String getCreateUser() {
		return createUser;
	}

	public String getCreateUserVal(){
		if(this.getCreateUser()==null){
			return "暂无客服跟进";
		}
		return this.getCreateUser();
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

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getRegionname() {
		return regionname;
	}

	public void setRegionname(String regionname) {
		this.regionname = regionname;
	}

	public String getCreateDate() {
		if(this.getCreateTime()!=null){
			return DateUtil.formatDate(this.getCreateTime());
		}else{
			return null;
		}
		
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getStatusName() {
		return CommonDicData.getAppointmentStatusName(this.getStatus());
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getSpName() {
		return spName;
	}

	public void setSpName(String spName) {
		this.spName = spName;
	}

	public String getSpId() {
		return spId;
	}

	public void setSpId(String spId) {
		this.spId = spId;
	}

	public String getWholeHouseVal() {
		return wholeHouseVal;
	}

	public void setWholeHouseVal(String wholeHouseVal) {
		this.wholeHouseVal = wholeHouseVal;
	}

	public String getCateName() {
		return cateName==null?"(未选)":cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getZt() {
		return zt;
	}

	public void setZt(Integer zt) {
		this.zt = zt;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String city;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
}

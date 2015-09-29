/**
 * 
 */
package com.jkkp.modules.appointment.view;

import java.sql.Date;

import com.jkkp.appapi.common.control.common.CommonDicData;
import com.jkkp.modules.appointment.model.AppointmentVwWorksite;
import com.jkkp.utils.DateUtil;

/**
 * @author Administrator
 *
 */
public class VAppointmentVwWorksite extends AppointmentVwWorksite{
	private String community;//小区
	private String userName;//预约用户姓名
	private String statusName;
	private Integer space;
	private Float budget;
	private Integer dcateId;
	private String dcateName;
	private Integer method;
	private String methodName;
	private Integer suType;
	private String suTypeName;
	private Integer houseType;
	private String houseTypeName;
	private String address;
	private Date zxTime;
	private String zxTimeStr;
	private String appointTimeStr;
	private String createTimeStr;
	private String respondTimeStr;
	private Integer preStatus;
	
	

	public Integer getPreStatus() {
		return preStatus;
	}

	public void setPreStatus(Integer preStatus) {
		this.preStatus = preStatus;
	}

	public String getRespondTimeStr() {
		return super.getRespondTime()==null?"":DateUtil.formatDateTime(super.getRespondTime());
	}

	public void setRespondTimeStr(String respondTimeStr) {
		this.respondTimeStr = respondTimeStr;
	}


	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}


	public String getCreateTimeStr() {
		return super.getCreateTime()==null?"":DateUtil.formatDateTime(super.getCreateTime());
	}


	public String getAppointTimeStr() {
		return super.getAppointTime()==null?"":DateUtil.formatDateTime(super.getAppointTime());
	}

	public void setAppointTimeStr(String appointTimeStr) {
		this.appointTimeStr = appointTimeStr;
	}

	public String getZxTimeStr() {
		return zxTime==null?"":DateUtil.formatDateTime(zxTime);
	}

	public void setZxTimeStr(String zxTimeStr) {
		this.zxTimeStr = zxTimeStr;
	}

	public Integer getSpace() {
		return space;
	}

	public void setSpace(Integer space) {
		this.space = space;
	}

	public Float getBudget() {
		return budget;
	}

	public void setBudget(Float budget) {
		this.budget = budget;
	}

	public Integer getDcateId() {
		return dcateId;
	}

	public void setDcateId(Integer dcateId) {
		this.dcateId = dcateId;
	}

	public Integer getMethod() {
		return method;
	}

	public void setMethod(Integer method) {
		this.method = method;
	}

	public Integer getSuType() {
		return suType;
	}

	public void setSuType(Integer suType) {
		this.suType = suType;
	}

	public Integer getHouseType() {
		return houseType;
	}

	public void setHouseType(Integer houseType) {
		this.houseType = houseType;
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

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStatusName() {
		return CommonDicData.getAppointmentVWorksiteStatusName(super.getStatus());
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getDcateName() {
		return dcateName;
	}

	public void setDcateName(String dcateName) {
		this.dcateName = dcateName;
	}

	public String getMethodName() {
		if(method==null){
			return "";
		}
		if(this.method>0&&this.method<CommonDicData.getMethodnames().length){
			return CommonDicData.getMethodnames()[this.method];
		}else{
			return "";
		}
		
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getSuTypeName() {
		if(suType==null){
			return "";
		}
		if(this.suType>0&&this.suType<CommonDicData.getSutypes().length){
			return CommonDicData.getSutypes()[this.suType];
		}else{
			return "";
		}
	}

	public void setSuTypeName(String suTypeName) {
		this.suTypeName = suTypeName;
	}

	public String getHouseTypeName() {
		if(houseType==null){
			return "";
		}
		if(this.houseType>0&&this.houseType<CommonDicData.getHousetypes().length){
			return CommonDicData.getHousetypes()[this.houseType];
		}else{
			return "";
		}
	}

	public void setHouseTypeName(String houseTypeName) {
		this.houseTypeName = houseTypeName;
	}
	
	
	
}

package com.jkkp.appapi.modules.mapper;

import java.util.Date;

import com.jkkp.appapi.common.utils.StringAndDate;
import com.jkkp.utils.DateUtil;

public class VIAppointmentPush {

	private String appPushStatus;
	private String suppName;
	private String suppJkbFlag;
	private String suppLevelMoney;
	private String suppEstimat;
	private String spplierId;
	private String grabNum;
	private String appCommunity;
	private String appUser;
	private String appSpace;
	private String appBudget;
	private String appSubType;
	private String appointmentPushId;
	private String appointmentZxTime;
	private Date appointmentZxTimeDate;
	private String appointmentCateName;
	private String appointmentAddress;
	private String appointmentAmountTime;
	private Date appointmentAmountTimeDate;
    private String quxiao;
    private String qu;
    private String sheng;
    private String city;
    private String random;
    private String mobile;
    private String headimg;
    private String scspid;
    
    
	public String getScspid() {
		return scspid;
	}

	public void setScspid(String scspid) {
		this.scspid = scspid;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRandom() {
		if(this.random==null||this.random=="")
			return "0";
		return random;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public String getQu() {
		return qu;
	}

	public void setQu(String qu) {
		this.qu = qu;
	}

	public String getSheng() {
		return sheng;
	}

	public void setSheng(String sheng) {
		this.sheng = sheng;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getQuxiao() {
		return quxiao;
	}

	public void setQuxiao(String quxiao) {
		this.quxiao = quxiao;
	}

	public String getAppPushStatus() {
		return appPushStatus;
	}

	public String getSuppName() {
		return suppName;
	}

	public String getSuppJkbFlag() {
		return suppJkbFlag;
	}

	public String getSuppLevelMoney() {
		return suppLevelMoney;
	}

	public String getSuppEstimat() {
		return suppEstimat;
	}

	public String getSpplierId() {
		return spplierId;
	}

	public String getGrabNum() {
		return grabNum;
	}

	public void setAppPushStatus(String appPushStatus) {
		this.appPushStatus = appPushStatus;
	}

	public void setSuppName(String suppName) {
		this.suppName = suppName;
	}

	public void setSuppJkbFlag(String suppJkbFlag) {
		this.suppJkbFlag = suppJkbFlag;
	}

	public void setSuppLevelMoney(String suppLevelMoney) {
		this.suppLevelMoney = suppLevelMoney;
	}

	public void setSuppEstimat(String suppEstimat) {
		this.suppEstimat = suppEstimat;
	}

	public void setSpplierId(String spplierId) {
		this.spplierId = spplierId;
	}

	public void setGrabNum(String grabNum) {
		this.grabNum = grabNum;
	}

	public void setAppCommunity(String appCommunity) {
		this.appCommunity = appCommunity;
	}

	public void setAppUser(String appUser) {
		this.appUser = appUser;
	}

	public void setAppSpace(String appSpace) {
		this.appSpace = appSpace;
	}

	public void setAppBudget(String appBudget) {
		this.appBudget = appBudget;
	}

	public void setAppSubType(String appSubType) {
		this.appSubType = appSubType;
	}

	public void setAppointmentPushId(String appointmentPushId) {
		this.appointmentPushId = appointmentPushId;
	}

	public String getAppCommunity() {
		return appCommunity;
	}

	public String getAppUser() {
		return appUser;
	}

	public String getAppSpace() {
		return appSpace;
	}

	public String getAppBudget() {
		return appBudget;
	}

	public String getAppSubType() {
		return appSubType;
	}

	public String getAppointmentPushId() {
		return appointmentPushId;
	}

	public void setAppointmentZxTime(String appointmentZxTime) {
		this.appointmentZxTime = appointmentZxTime;
	}

	public String getAppointmentZxTime() {
		if(this.appointmentZxTimeDate!=null)
			return DateUtil.formatDateTime(this.appointmentZxTimeDate);
		else
			return null;
	}
	
	public void setAppointmentCateName(String appointmentCateName) {
		this.appointmentCateName = appointmentCateName;
	}

	public String getAppointmentCateName() {
		return appointmentCateName;
	}
	
	public void setAppointmentAddress(String appointmentAddress) {
		this.appointmentAddress = appointmentAddress;
	}

	public String getAppointmentAddress() {
		return appointmentAddress;
	}
	
	public void setAppointmentAmountTime(String appointmentAmountTime) {
		this.appointmentAmountTime = appointmentAmountTime;
	}

	public String getAppointmentAmountTime() {
		if(this.appointmentAmountTimeDate!=null)
			return DateUtil.formatDateTime(this.appointmentAmountTimeDate);
		else {
			return null;
		}
	}

	/*public Date getAppointmentZxTimeDate() {
		//return appointmentZxTimeDate;
		return null;
		date数据为空json转换错误
	}*/

	public void setAppointmentZxTimeDate(Date appointmentZxTimeDate) {
		this.appointmentZxTimeDate = appointmentZxTimeDate;
	}

	/*public Date getAppointmentAmountTimeDate() {
		return null;
		date数据为空json转换错误
	}*/

	public void setAppointmentAmountTimeDate(Date appointmentAmountTimeDate) {
		this.appointmentAmountTimeDate = appointmentAmountTimeDate;
	}

	
}

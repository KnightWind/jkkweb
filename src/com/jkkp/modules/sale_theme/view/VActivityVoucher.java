package com.jkkp.modules.sale_theme.view;

import java.util.Date;

import com.jkkp.modules.sale_theme.model.ActivityVoucher;
import com.jkkp.utils.DateUtil;

public class VActivityVoucher extends ActivityVoucher {
	private String filepath;
	private String activityName;
	
	private String phone;
	private Integer isUse;
	private Integer relId;
	private Date userTime;

	public Date getUserTime() {
		return userTime;
	}

	public void setUserTime(Date userTime) {
		this.userTime = userTime;
	}

	public Integer getRelId() {
		return relId;
	}

	public void setRelId(Integer relId) {
		this.relId = relId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getIsUse() {
		return isUse;
	}

	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
	public String getCreateTimeString(){
		return createTime == null ? "" : DateUtil.formatDate(createTime);
	}
	public String getActiveTimeString(){
		return activeTime == null ? "" : DateUtil.formatDate(activeTime);
	}
	public String getUpdateTimeString(){
		return updateTime == null ? "" : DateUtil.formatDate(updateTime);
	}
	public String getStatusString(){
		return status == true ? "有效" : "失效";
	}
	
	public String getUserTimeHandle(){
		  return DateUtil.formatDateTime(userTime);
	}
	
	public String getIsUseHandle(){
		 if(this.getIsUse()!=null){
			 return this.getIsUse()==0?"未用":"已用";
		 }
		 return "未用";
	}
}

package com.jkkp.modules.sale_theme.view;

import com.jkkp.modules.sale_theme.model.ActivityCard;
import com.jkkp.utils.CheckedUtil;
import com.jkkp.utils.DateUtil;

public class VActivityCard extends ActivityCard {
	
	private String adminName;
	private String activityName;
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	
	
	public String getDescrSplit(){
		return CheckedUtil.splitString(descr, 20);
	}
	
	public String getUpdateTimeString(){
		return DateUtil.formatDateTime(updateTime);
	}
	
}

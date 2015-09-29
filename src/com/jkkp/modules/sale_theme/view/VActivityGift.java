package com.jkkp.modules.sale_theme.view;

import com.jkkp.modules.sale_theme.model.ActivityGift;
import com.jkkp.utils.DateUtil;

public class VActivityGift extends ActivityGift {

	private String adminName;
	private String activityName;
	private String cardName;
	
	
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
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
	
	public String getStartTimeString(){
		return startTime == null ? "" : DateUtil.formatDate(startTime);
	}
	public String getEndTimeString(){
		return endTime == null ? "" : DateUtil.formatDate(endTime);
	}
	public String getUpdateTimeString(){
		return updateTime == null ? "" : DateUtil.formatDate(updateTime);
	}
	
}

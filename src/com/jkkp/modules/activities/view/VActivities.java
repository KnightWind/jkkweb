package com.jkkp.modules.activities.view;

import com.jkkp.modules.activities.model.Activities;
import com.jkkp.utils.DateUtil;

public class VActivities extends Activities {

	private Integer awardsCount;

	public Integer getAwardsCount() {
		return awardsCount;
	}

	public void setAwardsCount(Integer awardsCount) {
		this.awardsCount = awardsCount;
	}
	
	
	public String getStartTimeString(){
		return startTime == null ? "" : DateUtil.formatDateTime(startTime);
	}
	
	public String getEndTimeString(){
		return endTime == null ? "" : DateUtil.formatDateTime(endTime);
	}
	
	public String getCreateTimeString(){
		return createTime == null ? "" : DateUtil.formatDateTime(createTime);
	}
	
	public String getDisply(){
		return displayNum == 1 ? "显示" : "不显示";
	}
}

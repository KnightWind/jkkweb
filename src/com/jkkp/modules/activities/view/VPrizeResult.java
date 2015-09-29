package com.jkkp.modules.activities.view;

import com.jkkp.modules.activities.model.PrizeResult;
import com.jkkp.utils.DateUtil;

public class VPrizeResult extends PrizeResult {

	private String activeName;
	private String prizeName;
	
	public String getActiveName() {
		return activeName;
	}

	public void setActiveName(String activeName) {
		this.activeName = activeName;
	}

	public String getPrizeName() {
		return prizeName;
	}

	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}

	public String getActiveNameString() {
		return activeName;
	}

	public void setActiveNameString(String activeNameString) {
		this.activeName = activeNameString;
	}
	
	public String getCreateTimeString(){
		return createTime == null ? "" : DateUtil.formatDateTime(createTime);
	}
	
	public String getStauts(){
		return isUnclaimed == 1 ? "已领取" : "未领取";
	}
	
	
}

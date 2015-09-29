package com.jkkp.modules.crowdfunding.view;

import com.jkkp.modules.crowdfunding.model.CrowdActitvity;
import com.jkkp.utils.DateUtil;

public class VCrowdActitvity extends CrowdActitvity {

	private String status;

	public String getStartTimeString(){
		return startTime != null ? DateUtil.formatDate(startTime) : "";
	}
	public String getEndTimeString(){
		return endTime != null ? DateUtil.formatDate(endTime) : "";
	}
	

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}

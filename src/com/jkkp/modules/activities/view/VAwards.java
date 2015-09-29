package com.jkkp.modules.activities.view;

import com.jkkp.modules.activities.model.Awards;
import com.jkkp.utils.DateUtil;

public class VAwards extends Awards {

	private String activeName;

	public String getActiveName() {
		return activeName;
	}

	public void setActiveName(String activeName) {
		this.activeName = activeName;
	}
	
	public String getCreateTimeString(){
		return creatTime == null ? "" : DateUtil.formatDateTime(creatTime);
	}
	
}

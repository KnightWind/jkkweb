package com.jkkp.modules.basedata.view;

import com.jkkp.modules.basedata.model.Settlement;
import com.jkkp.utils.DateUtil;

public class VSettlement extends Settlement {
	private String mName;
	
	
	
	public String getSettleTimeString(){
		return DateUtil.formatDate(settleTime);
	}
	public String getStatBeginTimeString(){
		return DateUtil.formatDate(statBeginTime);
	}
	public String getStatEndTimeString(){
		return DateUtil.formatDate(statEndTime);
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}
}

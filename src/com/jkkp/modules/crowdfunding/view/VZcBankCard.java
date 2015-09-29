package com.jkkp.modules.crowdfunding.view;

import com.jkkp.modules.crowdfunding.model.ZcBankCard;
import com.jkkp.utils.DateUtil;

public class VZcBankCard extends ZcBankCard {

	private String spName;
	private String regionname;

	public String getSpName() {
		return spName;
	}

	public void setSpName(String spName) {
		this.spName = spName;
	}

	public String getRegionname() {
		return regionname;
	}

	public void setRegionname(String regionname) {
		this.regionname = regionname;
	}
	
	public String getCreateTimeString(){
		return DateUtil.formatDate(createTime);
	}
	
	
}

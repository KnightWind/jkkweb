package com.jkkp.modules.crowdfunding.view;

import com.jkkp.modules.crowdfunding.model.ItemMember;
import com.jkkp.utils.DateUtil;

public class VItemMember extends ItemMember {

	private String userName;
	private String spName;
	private String itemName;
	private String statusString;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSpName() {
		return spName;
	}
	public void setSpName(String spNam) {
		this.spName = spNam;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getStatusString() {
		return statusString;
	}
	public void setStatusString(String statusString) {
		this.statusString = statusString;
	}
	
	public String getCreateTimeString(){
		return createDate == null ? "" : DateUtil.formatDate(createDate);
	}
	
	
}

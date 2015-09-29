package com.jkkp.modules.crowdfunding.view;

import com.jkkp.modules.crowdfunding.model.CrowdOrder;
import com.jkkp.utils.DateUtil;

public class VCrowdOrder extends CrowdOrder {

	private String userName;
	private String address;
	
	
	public String getCreateDateString(){
		return DateUtil.formatDateTime(createDate);
	}
	
	public String getUserName() {
		return userName == null ? "" : userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAddress() {
		return address == null ? "" : address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Float getMoneys(){
		if (moneyState == null) {
			return Float.valueOf(0);
		}
		if(deposit == null || retainage == null){
			return Float.valueOf(0);
		}
		switch (moneyState) {
		case 0:
			return Float.valueOf(0);
		case 1:
			return deposit;
		case 2:
			return deposit + retainage;
		default:
			return Float.valueOf(0);
		}
	}
	
	public String getPayType(){
		switch (moneyState) {
		case 0:
			return "未付款";
		case 1:
			return "付定金";
		case 2:
			return "定金 + 尾款";
		default:
			return "未知类型";
		}
	}
	
}

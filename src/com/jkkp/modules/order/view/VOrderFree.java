package com.jkkp.modules.order.view;

import com.jkkp.modules.order.model.OrderFree;

public class VOrderFree extends OrderFree {
	private String nickName;
	private String title;
    private String spName;
	
	public String getSpName() {
		return spName;
	}

	public void setSpName(String spName) {
		this.spName = spName;
	}
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	

}

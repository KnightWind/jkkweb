package com.jkkp.modules.appointment.view;

import com.jkkp.modules.appointment.model.AppointmentVwWorksite;

public class VAppintmentSimpleVw extends AppointmentVwWorksite {
	
	private String nickName;

	
	
	public String getNickName() {
		return nickName;
	}



	public void setNickName(String nickName) {
		this.nickName = nickName;
	}



	public String getStatusString(){
		switch(status){
		case 0:
			return "待应单";
		case 10:
			return "已应单";
		case 20:
			return "已取消";
		default:
			return "未知";
		}
	}
	
}

package com.jkkp.modules.member.model;

import java.util.Date;
import javax.persistence.*;


public class VMember extends Member{
	String tockenId;//tockenId 登录验证
	String headImg;//个人头像
	

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getTockenId() {
		return tockenId;
	}

	public void setTockenId(String tockenId) {
		this.tockenId = tockenId;
	}
	
	

}
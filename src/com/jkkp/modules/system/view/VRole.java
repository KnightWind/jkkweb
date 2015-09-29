package com.jkkp.modules.system.view;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.jkkp.modules.system.model.Role;

public class VRole extends Role {
    private String username;
    private String nickname;
    private String mobile;
    private String email;
    private int handleSum;
    private int handleSum0;
    private int handleSum5;
    private Date loginTime;
    private int status0;
	
	public int getStatus0() {
		return status0;
	}
	public void setStatus0(int status0) {
		this.status0 = status0;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getHandleSum() {
		return handleSum;
	}
	public void setHandleSum(int handleSum) {
		this.handleSum = handleSum;
	}
	public int getHandleSum0() {
		return handleSum0;
	}
	public void setHandleSum0(int handleSum0) {
		this.handleSum0 = handleSum0;
	}
	public int getHandleSum5() {
		return handleSum5;
	}
	public void setHandleSum5(int handleSum5) {
		this.handleSum5 = handleSum5;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	
	public String getLoginTimeHandle(){
		if(loginTime!=null){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			return sdf.format(loginTime);
		}
		return "";
	}
}

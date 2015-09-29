package com.jkkp.modules.system.view;

import com.jkkp.modules.system.model.Complain;

public class VComplain extends Complain {

	public int appId;
    public int source;
    public String nickname;
    public String mobile;
    public String userName;
    public String spName;
    public String smobile;
    
    public String getSmobile() {
		return smobile;
	}
	public void setSmobile(String smobile) {
		this.smobile = smobile;
	}
	public String getSpName() {
		return spName;
	}
	public void setSpName(String spName) {
		this.spName = spName;
	}
	public int getAppId() {
		return appId;
	}
	public void setAppId(int appId) {
		this.appId = appId;
	}
	public int getSource() {
		return source;
	}
	public void setSource(int source) {
		this.source = source;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	public String getSourceVal(){
		if(source>0){
			if(source==1){
				return "商城网站";
			}
			if(source==2){
				return "400电话";
			}
		}
		return "";
	}
}

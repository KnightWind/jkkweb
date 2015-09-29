package com.jkkp.appapi.modules.mapper;

public class VIEngineeringsV1 {

	private String check_flag="";
	private String content="";
	private String create_time ="";
	
	public String getCheck_flag(){
		return check_flag;
	}
	public String getContent(){
		return content;
	}
	
	public String getCreate_time(){
		return create_time;
	}
	
	
	public void setCheck_flag(String check_flag){
		this.check_flag=check_flag;
	}
	
	public void setContent(String content){
		this.content=content;
	}
	
	public void setCreate_time(String create_time){
		this.create_time=create_time;
	}
}

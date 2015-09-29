package com.jkkp.appapi.modules.mapper;

public class VITopicList {
	
	private String caseId="";
	private String uid="";
	private String engineerId="";
	private String community="";
	private String space="";
	private String budget="";
	private String hostType="";
	private String style="";
	private String create_time="";
	private String comment_count="";
	private String browse_count="";
	private String stag_name="";
	private String user="";
	private String method="";
	private String photo="";
	
	public void setPhoto(String photo){
		this.photo=photo;
	}
	public String getPhoto(){
		return this.photo;
	}
	
	public void setStag_name(String stag_name){
		this.stag_name=stag_name;
	}
	public String getStag_name(){
		return this.stag_name;
	}
	public void setUser(String user){
		this.user=user;
	}
	public String getUser(){
		return this.user;
	}
	public void setMethod(String method){
		this.method=method;
	}
	public String getMethod(){
		return this.method;
	}
	
	public void setBrowse_count(String browse_count){
		this.browse_count=browse_count;
	}	
	public String getBrowse_count(){
		return this.browse_count;
	}
	
	public void setComment_count(String comment_count){
		this.comment_count=comment_count;
	}	
	public String getComment_count(){
		return this.comment_count;
	}
	
	public void setCreate_time(String create_time){
		this.create_time=create_time;
	}	
	public String getCreate_time(){
		return this.create_time;
	}
	
	public void setStyle(String style){
		this.style=style;
	}	
	public String getStyle(){
		return this.style;
	}
	public void setBbudget(String budget){
		this.budget=budget;
	}	
	public String getBudget(){
		return this.budget;
	}
	
	public void setHostType(String hostType){
		this.hostType=hostType;
	}	
	public String getHostType(){
		return this.hostType;
	}
	

	
	public void setCommunity(String community){
		this.community=community;
	}	
	public String getCommunity(){
		return this.community;
	}
	public void setSpace(String space){
		this.space=space;
	}	
	public String getSpace(){
		return this.space;
	}

	public void setCaseId(String caseId){
		this.caseId=caseId;
	}	
	public String getCaseId(){
		return this.caseId;
	}
	public void setUid(String uid){
		this.uid=uid;
	}	
	public String getUuid(){
		return this.uid;
	}
	public String getEngineerId(){
		return this.engineerId;
	}
	public void setEngineerId(String engineerId){
		this.engineerId=engineerId;
	}	


}

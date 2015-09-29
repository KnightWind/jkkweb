package com.jkkp.appapi.modules.mapper;

import java.util.Date;

public class VITopic{
	
	private Integer topicId;
	private String topicSubject;
	private Integer topicBudget;
	private Integer topicSpace;
	private Byte topicStyle;
	private Byte topicHouseType;
	private Integer topicUid;
	private Date topicCreateTime;
	private String topicCityDomain;
	private Byte topicStatus;
	private Date topicCheckTime;
	private Date topicUpdateTime;
	private Date topicCloseTime;
	private String topicCommunity;
	private String topicForman;
	
	public void setTopicCreateTime(Date topicCreateTime){
		this.topicCreateTime=topicCreateTime;
	}
	public Date getTopicCreateTime(){
		return topicCreateTime;
	}
	
	public void setTopicCheckTime(Date topicCheckTime){
		this.topicCheckTime=topicCheckTime;
	}
	public Date getTopicCheckTime(){
		return topicCheckTime;
	}
	public void setTopicUpdateTime(Date topicUpdateTime){
		this.topicUpdateTime=topicUpdateTime;
	}
	public Date getTopicUpdateTime(){
		return topicUpdateTime;
	}
	public void setTopicCloseTime(Date topicCloseTime){
		this.topicCloseTime=topicCloseTime;
	}
	public Date getTopicCloseTime(){
		return topicCloseTime;
	}
	
	
	
	
	
	public void setTopicStyle(Byte topicStyle){
		this.topicStyle=topicStyle;
	}
	public Byte getTopicStyle(){
		return topicStyle;
	}
	
	public void setTopicStatus(Byte topicStatus){
		this.topicStatus=topicStatus;
	}
	public Byte getTopicStatus(){
		return topicStatus;
	}	
	public void setTopicHouseType(Byte topicHouseType){
		this.topicHouseType=topicHouseType;
	}
	public Byte getTopicHouseType(){
		return topicHouseType;
	}
	
	
	public void setTopicCityDomain(String topicCityDomain){
		this.topicCityDomain=topicCityDomain;
	}
	public String getTopicCityDomain(){
		return topicCityDomain;
	}
	
	public void setTopicCommunity(String topicCommunity){
		this.topicCommunity=topicCommunity;
	}
	public String getTopicCommunity(){
		return topicCommunity;
	}
	
	public void setTopicForman(String topicForman){
		this.topicForman=topicForman;
	}
	public String getTopicForman(){
		return topicForman;
	}
	

	
	public void setTopicId(Integer topicId){
		this.topicId=topicId;
	}
	public Integer getTopicId(){
		return topicId;
	}
	public void setTopicBudget(Integer topicBudget){
		this.topicBudget=topicBudget;
	}
	public Integer getTopicBudget(){
		return topicBudget;
	}
	public void setTopicSpace(Integer topicSpace){
		this.topicSpace=topicSpace;
	}
	public Integer getTopicSpace(){
		return topicSpace;
	}
	public void setTopicUid(Integer topicUid){
		this.topicUid=topicUid;
	}
	public Integer getTopicUid(){
		return topicUid;
	}
	
	public void setTopicSubject(String topicSubject){
		this.topicSubject=topicSubject;
	}
	public String getTopicSubject(){
		return topicSubject;
	}
	

}

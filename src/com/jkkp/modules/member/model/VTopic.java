package com.jkkp.modules.member.model;

import java.util.Date;

public class VTopic extends Topic{
	
	public Integer topicId;
	public String topicSubject;
	public Integer topicBudget;
	public Integer topicSpace;
	public Byte topicStyle;
	public Byte topicHouseType;
	public Integer topicUid;
	public Date topicCreateTime;
	public String topicCityDomain;
	public Byte topicStatus;
	public Date topicCheckTime;
	public Date topicUpdateTime;
	public Date topicCloseTime;
	public String topicCommunity;
	public String topicForman;
	
	public String houseTypeVal;
	public String styleVal;
	
	public String getHouseTypeVal() {
		return houseTypeVal==null?"":houseTypeVal.trim();
	}
	public void setHouseTypeVal(String houseTypeVal) {
		this.houseTypeVal = houseTypeVal;
	}
	public String getStyleVal() {
		return styleVal==null?"":styleVal.trim();
	}
	public void setStyleVal(String styleVal) {
		this.styleVal = styleVal;
	}
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

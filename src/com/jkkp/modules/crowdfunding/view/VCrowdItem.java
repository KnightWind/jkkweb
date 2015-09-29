package com.jkkp.modules.crowdfunding.view;

import java.util.Date;

import com.jkkp.modules.crowdfunding.model.CrowdItem;
import com.jkkp.utils.DateUtil;

public class VCrowdItem extends CrowdItem {

	private String activityName;
	private String statusString;
	private String spName;
	private String title;
	private Date endTime;
	private Date startTime;
	private String flagString;
	private String cateName;
	private String detail;
	private Integer stock;
	
	public String getStartTimeString(){
		return startTime != null ? DateUtil.formatDate(startTime) : "";
	}
	public String getEndTimeString(){
		return endTime != null ? DateUtil.formatDate(endTime) : "";
	}
	
	
	
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getStatusString() {
		return statusString;
	}
	public void setStatusString(String statusString) {
		this.statusString = statusString;
	}
	public String getSpName() {
		return spName;
	}
	public void setSpName(String spName) {
		this.spName = spName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public String getFlagString() {
		return flagString;
	}
	public void setFlagString(String flagString) {
		this.flagString = flagString;
	}
	
	
	
	
}

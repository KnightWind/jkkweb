package com.jkkp.modules.crowdfunding.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jkkp.utils.DateUtil;

@Table(name = "zc_activity")
public class CrowdActitvity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "activityName")
	private String activityName; // 活动名称

	@Column(name = "start_time")
	protected Date startTime; // 活动开始时间

	@Column(name = "end_time")
	protected Date endTime; // 活动结束时间

	@Column(name = "deposit")
	private Double deposit; // 定金
	
	
	public String getStartTimeString(){
		return startTime == null ? "" : DateUtil.formatDate(startTime);
	}
	public String getEndTimeString(){
		return endTime == null ? "" : DateUtil.formatDate(endTime);
	}
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Double getDeposit() {
		return deposit;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}
	
	
	
}

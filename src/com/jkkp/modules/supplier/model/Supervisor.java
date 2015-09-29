package com.jkkp.modules.supplier.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jkkp.utils.DateUtil;

@Table(name = "supervisor")
public class Supervisor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	@Column(name = "gcd_id")
	public Integer gcdId;
	@Column(name = "inst_id")
	public Integer instId;
	@Column(name = "check_time")
	public Date checkTime;
	@Column(name = "begin_time")
	public Date beginTime;
	@Column(name = "end_time")
	public Date endTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGcdId() {
		return gcdId;
	}

	public void setGcdId(Integer gcdId) {
		this.gcdId = gcdId;
	}

	public Integer getInstId() {
		return instId;
	}

	public void setInstId(Integer instId) {
		this.instId = instId;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getStageId() {
		return stageId;
	}

	public void setStageId(Integer stageId) {
		this.stageId = stageId;
	}

	@Column(name = "stage_id")
	public Integer stageId;

	// 验收时间
	public String getCheckTimeHandle() {
		if (this.getCheckTime() != null) {
			return DateUtil.formatDateTime(checkTime);
		}
		return "";
	}

	// 开工时间
	public String getBeginTimeHandle() {
		if (this.getBeginTime() != null) {
			return DateUtil.formatDateTime(beginTime);
		}
		return "";
	}

	// 竣工时间
	public String getEndTimeHandle() {
		if (this.getEndTime() != null) {
			return DateUtil.formatDateTime(endTime);
		}
		return "";
	}

}

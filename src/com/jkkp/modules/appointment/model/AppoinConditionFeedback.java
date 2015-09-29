package com.jkkp.modules.appointment.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jkkp.utils.DateUtil;

@Table(name = "appoin_condition_feedback")
public class AppoinConditionFeedback {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "order_by")
	private Integer orderBy;
	@Column(name = "create_time")
	private Date createTime;
	@Column(name = "feedback_time")
	private Date feedbackTime;
	@Column(name = "content")
	private String content;
	@Column(name = "pid")
	private Integer pid;

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getFeedbackTime() {
		return feedbackTime;
	}

	public void setFeedbackTime(Date feedbackTime) {
		this.feedbackTime = feedbackTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	// 创建时间
	public String getCreateTimeHandle() {
		if (this.getCreateTime() != null) {
			return DateUtil.formatDateTime(createTime);
		}
		return "";
	}

	//回访时间
	public String getFeedbackTimeHandle() {
		if (this.getFeedbackTime() != null) {
			return DateUtil.formatDate(feedbackTime);
		}
		return "";
	}
}

package com.jkkp.modules.crowdfunding.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 活动类别关联类
 * 
 * @author xiaozhenyu
 *
 */
@Table(name = "zc_activity_category")
public class ActivityCategory {

	@Column(name = "activity_id")
	private Integer activityId;

	@Column(name = "category_id")
	private Integer categoryId;

	private Integer activityInfoId;

	@Column(name = "create_time")
	private Date createTime;

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getActivityInfoId() {
		return activityInfoId;
	}

	public void setActivityInfoId(Integer activityInfoId) {
		this.activityInfoId = activityInfoId;
	}

}

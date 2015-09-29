package com.jkkp.modules.sale_theme.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/***
 * 活动推荐每日统计结果
 * @author ccn
 *
 */

@Table(name = "activity_recommend_stat")
public class ActivityRecommendStat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "activity_id")
	private Integer activityId; //活动ID 引用ActivityTheme
	
	@Column(name = "from_phone")
	private String fromPhone; //推荐人的手机号码
	
	@Column(name = "recommend_num")
	private Integer recommendNum; // 推荐次数
	
	@Column(name = "recommend_success_num")
	private Integer recommendSuccessNum; // 推荐成功次数
	
	private Date date; //统计的日期

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public String getFromPhone() {
		return fromPhone;
	}

	public void setFromPhone(String fromPhone) {
		this.fromPhone = fromPhone;
	}

	public Integer getRecommendNum() {
		return recommendNum;
	}

	public void setRecommendNum(Integer recommendNum) {
		this.recommendNum = recommendNum;
	}

	public Integer getRecommendSuccessNum() {
		return recommendSuccessNum;
	}

	public void setRecommendSuccessNum(Integer recommendSuccessNum) {
		this.recommendSuccessNum = recommendSuccessNum;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}

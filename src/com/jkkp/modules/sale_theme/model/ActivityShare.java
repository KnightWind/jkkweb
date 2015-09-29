package com.jkkp.modules.sale_theme.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/***
 * 活动分享记录
 * @author ccn
 *
 */

@Table(name = "activity_share")
public class ActivityShare {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "activity_id")
	private Integer activityId; //活动ID 引用ActivityTheme
	
	private String title;
	
	private String url;
	
	private String phone;
	
	private Integer type; //分享类型：1 购卡分享, 2 抽奖成功分享, 3 分享活动
	
	@Column(name = "lottery_chance")
	private Integer lotteryChance; //获得抽奖机会次数
	
	@Column(name = "uesed_chance")
	private Integer uesedChance; //已经使用的抽奖次数
	
	@Column(name = "create_time")
	private Date createTime;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getLotteryChance() {
		return lotteryChance;
	}

	public void setLotteryChance(Integer lotteryChance) {
		this.lotteryChance = lotteryChance;
	}

	public Integer getUesedChance() {
		return uesedChance;
	}

	public void setUesedChance(Integer uesedChance) {
		this.uesedChance = uesedChance;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}

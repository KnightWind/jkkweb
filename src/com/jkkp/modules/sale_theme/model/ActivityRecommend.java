package com.jkkp.modules.sale_theme.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jkkp.utils.DateUtil;

/***
 * 活动推荐记录表
 * @author ccn
 *
 */

@Table(name = "activity_recommend")
public class ActivityRecommend {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "activity_id")
	private Integer activityId; //活动ID 引用ActivityTheme
	
    @Column(name = "to_phone")
	private String toPhone; // 被推荐人的手机号码
	
    @Column(name = "from_phone")
	private String fromPhone; //推荐人的手机号码
	
	private Integer status; // 推荐成功状态：0 未成功 未购卡；1 成功 已购卡
	
	@Column(name = "create_time")
	private Date createTime; //礼包有效开始时间
	
	@Column(name = "success_time")
	private Date successTime; //礼包失效时间

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getToPhone() {
		return toPhone;
	}

	public void setToPhone(String toPhone) {
		this.toPhone = toPhone;
	}

	public String getFromPhone() {
		return fromPhone;
	}

	public void setFromPhone(String fromPhone) {
		this.fromPhone = fromPhone;
	}


	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getSuccessTime() {
		return successTime;
	}

	public void setSuccessTime(Date successTime) {
		this.successTime = successTime;
	}
	
	public String getStatusVal(){
		if(this.getStatus()!=null){
			if(status==1){
				return "推荐成功";
			}
			if(status==0){
				return "用户待报名中";
			}
			return "";
		}
		return "";
	}
	
	public String getCreateTimeHandle(){
		if(this.getCreateTime()!=null){
			return DateUtil.formatDateTime(createTime);
		}
		return "";
	}
}

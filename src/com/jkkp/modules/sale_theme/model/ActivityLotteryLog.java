package com.jkkp.modules.sale_theme.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jkkp.utils.DateUtil;

/***
 * 活动主题抽奖记录表
 * @author ccn
 *
 */

@Table(name = "activity_lottery_log")
public class ActivityLotteryLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "activity_id")
	private Integer activityId; //活动ID 引用ActivityTheme
	
	private String phone; // 礼包名称
	
	
	private Boolean win; //礼包有效开始时间
	
	@Column(name = "award_id")
	private Integer awardId;
	
	@Column(name = "award_content")
	private String awardContent; //奖品内容
	
	private Boolean receive; //是否已经领取
	
	@Column(name = "create_time")
	private Date createTime;
	
	@Column(name = "receive_time")
	private Date receiveTime;

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean getWin() {
		return win;
	}

	public void setWin(Boolean win) {
		this.win = win;
	}

	public Integer getAwardId() {
		return awardId;
	}

	public void setAwardId(Integer awardId) {
		this.awardId = awardId;
	}

	public Boolean getReceive() {
		return receive;
	}

	public void setReceive(Boolean receive) {
		this.receive = receive;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public String getAwardContent() {
		return awardContent;
	}

	public void setAwardContent(String awardContent) {
		this.awardContent = awardContent;
	}

	
	public String getWinVal(){
		if(this.getWin()!=null){
			if(win){
				return "已中奖";
			}else{
				return "未中奖";
			}
		}
		return "";
	}
	
	public String getReceiveVal(){
		if(this.getReceive()!=null){
			if(receive){
				return "已领取";
			}else{
				return "未领取";
			}
		}
		return "未领取";
	}
	
	public String getCreateTimeHandle(){
		if(this.getCreateTime()!=null){
			return DateUtil.formatDateTime(createTime);
		}
		return "";
	}
	
	public String getReceiveTimeHandle(){
		if(this.getReceiveTime()!=null){
			return DateUtil.formatDateTime(receiveTime);
		}
		return "";
	}
}

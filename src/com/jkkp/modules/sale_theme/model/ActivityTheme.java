package com.jkkp.modules.sale_theme.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jkkp.utils.DateUtil;

/***
 * 活动主题
 * @author ccn
 *
 */

@Table(name = "activity_theme")
public class ActivityTheme {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String title;
	
	private String addr;

	@Column(name = "joined_num")
	private Integer joinedNum; // 已经报名人数

	@Column(name = "signed_num")
	private Integer signedNum;

	private String descr;

	private Integer status;

	@Column(name = "lottery_activities_id")
	private Integer lotteryActivitiesId; //抽奖活动ID 关联Activities
	
	public Integer getLotteryActivitiesId() {
		return lotteryActivitiesId;
	}

	public void setLotteryActivitiesId(Integer lotteryActivitiesId) {
		this.lotteryActivitiesId = lotteryActivitiesId;
	}

	@Column(name = "share_chance")
	private Integer shareChance;
	
	@Column(name = "share_card_chance")
	private Integer shareCardChance;
	
	@Column(name = "share_lottery_chance")
	private Integer shareLotteryChance;

	@Column(name = "msg_alarm")
	private Date msgAlarm; //自动发送信息提醒已经报名的用户参加活动的闹钟时间 ,如果不为空,则闹钟时间必需小于等于活动的开始时间beginTime

	@Column(name = "begin_time")
	private Date beginTime;
	
	@Column(name = "end_time")
	private Date endTime;

	private String adminId;
	@Column(name = "update_time")
	private Date updateTime;

	/**是抽奖活动否在有效内*/
	public boolean isEnable() {
		if(status != null && status != 0)
			return false;
		Date now = new Date();
		if(beginTime != null && beginTime.after(now)) 
			return false;
		if(endTime != null && endTime.before(now)) 
			return false;
		return true;
	}
	
	public Integer getId() {
		return id;
	}
	
	public Date getMsgAlarm() {
		return msgAlarm;
	}

	public void setMsgAlarm(Date msgAlarm) {
		this.msgAlarm = msgAlarm;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public Integer getJoinedNum() {
		return joinedNum;
	}

	public void setJoinedNum(Integer joinedNum) {
		this.joinedNum = joinedNum;
	}

	public Integer getSignedNum() {
		return signedNum;
	}

	public void setSignedNum(Integer signedNum) {
		this.signedNum = signedNum;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
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

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Integer getShareChance() {
		return shareChance;
	}

	public void setShareChance(Integer shareChance) {
		this.shareChance = shareChance;
	}

	public Integer getShareCardChance() {
		return shareCardChance;
	}

	public void setShareCardChance(Integer shareCardChance) {
		this.shareCardChance = shareCardChance;
	}

	public Integer getShareLotteryChance() {
		return shareLotteryChance;
	}

	public void setShareLotteryChance(Integer shareLotteryChance) {
		this.shareLotteryChance = shareLotteryChance;
	}
	
	//活动状态
	public String getStatusVal(){
		if(this.getStatus()!=null){
			if(this.getStatus()==1){
				return "活动结束";
			}
			if(this.getStatus()==0){
				return "活动开启";
			}
			return "";
		}
		return "";
	}
	
	//开始时间
	public String getBeginTimeHandle(){
		if(this.getBeginTime()!=null){
			return DateUtil.formatDate(beginTime);
		}
		return "";
	}
	
	//结束时间
	public String getEndTimeHandle(){
		if(this.getEndTime()!=null){
			return DateUtil.formatDate(endTime);
		}
		return "";
	}
	
	//更新时间
	public String getUpdateTimeHandle(){
		if(this.getUpdateTime()!=null){
			return DateUtil.formatDateTime(updateTime);
		}
		return "";
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}

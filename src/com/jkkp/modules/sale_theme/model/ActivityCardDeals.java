package com.jkkp.modules.sale_theme.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jkkp.utils.DateUtil;

/***
 * 活动购卡记录
 * 
 * @author ccn
 * 
 */

@Table(name = "activity_card_deals")
public class ActivityCardDeals {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "card_id")
	private Integer cardId;

	private String phone;

	private String openid; //用户的微信号ID
	
	@Column(name = "decorate_req")
	private String decorateReq; // 装修需求

	@Column(name = "payment_type")
	private Integer paymenType;

	private Boolean shared;

	@Column(name = "gift_id")
	private Integer giftId;
	
	@Column(name = "pay_status")
	private Integer payStatus;
	
	private Float fee; //购卡金额
	
	@Column(name = "serial_code")
	private String serialCode; //家可可平台流水号
	
	@Column(name = "create_time")
	private Date createTime;

	@Column(name = "today_chance")
	private Integer todayChance; //今日剩余的抽奖机会
	
	@Column(name = "lottery_time")
	private Date lotteryTime; //抽奖时间
	
	public ActivityCardDeals(Integer id, Integer cardId, String phone,
			String decorateReq, Integer paymenType, Boolean shared, Integer giftId,
			Date createTime) {
		super();
		this.id = id;
		this.cardId = cardId;
		this.phone = phone;
		this.decorateReq = decorateReq;
		this.paymenType = paymenType;
		this.shared = shared;
		this.giftId = giftId;
		this.createTime = createTime;
	}

	public ActivityCardDeals() {
		super();
	}
	
	public ActivityCardDeals(String serialCode) {
		this.serialCode = serialCode;
	}
	
	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public Integer getCardId() {
		return cardId;
	}

	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDecorateReq() {
		return decorateReq;
	}

	public void setDecorateReq(String decorateReq) {
		this.decorateReq = decorateReq;
	}

	public Integer getPaymenType() {
		return paymenType;
	}

	public void setPaymenType(Integer paymenType) {
		this.paymenType = paymenType;
	}

	public Integer getGiftId() {
		return giftId;
	}

	public void setGiftId(Integer giftId) {
		this.giftId = giftId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getId() {
		return id;
	}

	public Boolean getShared() {
		return shared;
	}

	public void setShared(Boolean shared) {
		this.shared = shared;
	}

	public String getPayMenStatusVal(){
		if(this.getPaymenType()!=null){
			if(paymenType==0){
				return "未支付";
			}
			if(paymenType==1){
				return "已支付";
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

	public Integer getTodayChance() {
		return todayChance;
	}

	public void setTodayChance(Integer todayChance) {
		this.todayChance = todayChance;
	}

	public Date getLotteryTime() {
		return lotteryTime;
	}

	public void setLotteryTime(Date lotteryTime) {
		this.lotteryTime = lotteryTime;
	}

	public String getSerialCode() {
		return serialCode;
	}

	public void setSerialCode(String serialCode) {
		this.serialCode = serialCode;
	}

	public Float getFee() {
		return fee;
	}

	public void setFee(Float fee) {
		this.fee = fee;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	
	
}

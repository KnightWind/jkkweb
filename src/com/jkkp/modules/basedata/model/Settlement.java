package com.jkkp.modules.basedata.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 结算记录表
 * @author bruce
 * 
 */
@Table(name = "settlement")
public class Settlement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 身份：0 业主，1 店员
	 */
	@Column(name = "role")
	private Integer role;

	/**
	 * 手机号码
	 */
	@Column(name = "mobile")
	private String mobile;

	/**
	 * 本期获得的提成金额，单位：元
	 */
	@Column(name = "current_balance")
	private Float currentBalance;

	/**
	 * 本期已经结算的提成金额，单位：元
	 */
	@Column(name = "sttle_balance")
	private Float sttleBalance;

	/**
	 * 结算状态：0未结算；1已结算
	 */
	@Column(name = "status")
	private Integer status;

	/**
	 * 本期统计提成开始时间
	 */
	@Column(name = "stat_begin_time")
	protected Date statBeginTime;

	/**
	 * 本期统计提成开始时间
	 */
	@Column(name = "stat_end_time")
	protected Date statEndTime;
	
	/**
	 * 结算时间
	 */
	@Column(name = "settle_time")
	protected Date settleTime;

	/**
	 * 结算流水号
	 */
	@Column(name = "serial_code")
	private String serialCode;

	/**
	 * 财务出纳名称
	 */
	@Column(name = "admin_name")
	private String adminName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Float getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(Float currentBalance) {
		this.currentBalance = currentBalance;
	}

	public Float getSttleBalance() {
		return sttleBalance;
	}

	public void setSttleBalance(Float sttleBalance) {
		this.sttleBalance = sttleBalance;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getStatBeginTime() {
		return statBeginTime;
	}

	public void setStatBeginTime(Date statBeginTime) {
		this.statBeginTime = statBeginTime;
	}

	public Date getStatEndTime() {
		return statEndTime;
	}

	public void setStatEndTime(Date statEndTime) {
		this.statEndTime = statEndTime;
	}

	public Date getSettleTime() {
		return settleTime;
	}

	public void setSettleTime(Date settleTime) {
		this.settleTime = settleTime;
	}

	public String getSerialCode() {
		return serialCode;
	}

	public void setSerialCode(String serialCode) {
		this.serialCode = serialCode;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 备注
	 */
	@Column(name = "remark")
	private String remark;

}
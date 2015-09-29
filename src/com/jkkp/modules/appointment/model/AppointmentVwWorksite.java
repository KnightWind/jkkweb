package com.jkkp.modules.appointment.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jkkp.utils.DateUtil;

@Table(name = "appointment_vw_worksite")
public class AppointmentVwWorksite {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer aid;	
	@Column(name = "appoint_address")
	private String  appointAddress;
	@Column(name = "appoint_time")
	private Date appointTime;
	@Column(name = "create_time")
	private Date createTime;	
	@Column(name = "user_id")
	private Integer userId;
	@Column(name = "engineering_id")
	private Integer engineeringId;
	protected Integer status;
	@Column(name = "sp_id")
	private Integer spId;
	//应单商家用户id
	@Column(name = "SP_USER_ID")
	private Integer spUserId;
	//商家应答时间
	@Column(name="RESPOND_TIME")
	private Date respondTime;
	
	public AppointmentVwWorksite(){
		
	}
	
	public AppointmentVwWorksite(Integer aid, String appointAddress,
			Date appointTime, Date createTime, Integer userId,
			Integer engineeringId, Integer status,Integer spId) {
		this.aid=aid;
		this.appointAddress=appointAddress;
		this.appointTime=appointTime;
		this.createTime=createTime;
		this.userId=userId;
		this.engineeringId=engineeringId;
		this.status=status;
		this.spId=spId;
	}
	
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public String getAppointAddress() {
		return appointAddress;
	}
	public void setAppointAddress(String appointAddress) {
		this.appointAddress = appointAddress;
	}
	public Date getAppointTime() {
		return appointTime;
	}
	public void setAppointTime(Date appointTime) {
		this.appointTime = appointTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getEngineeringId() {
		return engineeringId;
	}
	public void setEngineeringId(Integer engineeringId) {
		this.engineeringId = engineeringId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getSpId() {
		return spId;
	}
	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public Integer getSpUserId() {
		return spUserId;
	}

	public void setSpUserId(Integer spUserId) {
		this.spUserId = spUserId;
	}

	public Date getRespondTime() {
		return respondTime;
	}

	public void setRespondTime(Date respondTime) {
		this.respondTime = respondTime;
	}
	
	public String getRespondTimeString(){
		return respondTime == null ? "" : DateUtil.formatDateTime(respondTime);
	}
	
	public String getCreateTimeHandle(){
		return createTime == null ? "" : DateUtil.formatDateTime(createTime);
	}
	
	public String getAppointTimeHandle(){
		return DateUtil.formatDateTime(appointTime);
	}
}
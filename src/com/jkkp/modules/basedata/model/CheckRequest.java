package com.jkkp.modules.basedata.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "check_request")
public class CheckRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
	@Column(name = "create_time")
	public Date createTime;
	@Column(name = "check_time")
	public Date checkTime;
	@Column(name = "gcd_id")
	public Integer gcdId;
	@Column(name = "stage_id")
	public Integer stageId;
	@Column(name = "create_user")
	public Integer createUser;
	@Column(name = "request_type")
	public Integer requestType;
	
	public Integer getRequestType() {
		return requestType;
	}
	public void setRequestType(Integer requestType) {
		this.requestType = requestType;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	public Integer getGcdId() {
		return gcdId;
	}
	public void setGcdId(Integer gcdId) {
		this.gcdId = gcdId;
	}
	public Integer getStageId() {
		return stageId;
	}
	public void setStageId(Integer stageId) {
		this.stageId = stageId;
	}
	public Integer getCreateUser() {
		return createUser;
	}
	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}
}
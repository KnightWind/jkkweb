package com.jkkp.modules.basedata.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jkkp.utils.DateUtil;

@Table(name = "engineering_stage_mx")
public class EngineeringStageMx {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer pid;
	private Integer status;
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "mx_desc")
	private String mxDesc;
	private String remark;
	@Column(name = "create_time")
	private Date createTime;
	

	@Column(name = "ordr_by")
	private Integer ordrBy;
	@Column(name = "check_rule")
	private String checkRule;
	
	public Integer getOrdrBy() {
		return ordrBy;
	}
	public void setOrdrBy(Integer ordrBy) {
		this.ordrBy = ordrBy;
	}
	public String getCheckRule() {
		return checkRule;
	}
	public void setCheckRule(String checkRule) {
		this.checkRule = checkRule;
	}

	@Column(name = "create_user")
	private String createUser;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getMxDesc() {
		return mxDesc;
	}
	public void setMxDesc(String mxDesc) {
		this.mxDesc = mxDesc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	
	public String getCreateTimeHandle(){
		if(createTime!=null){
			return DateUtil.formatDateTime(createTime);
		}
		return "";
	}
	
}
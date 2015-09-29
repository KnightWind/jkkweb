package com.jkkp.modules.basedata.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "engineering_stage_mx_inst")
public class EngineeringStageMxInst {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

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

	public Integer getPassFlag() {
		return passFlag;
	}

	public void setPassFlag(Integer passFlag) {
		this.passFlag = passFlag;
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

	private Integer pid;
	@Column(name = "pass_flag")
	private Integer passFlag;
	private String remark;
	@Column(name = "create_time")
	private Date createTime;
	@Column(name = "create_user")
	private String createUser;
	@Column(name = "order_by")
	private Integer orderBy;
	@Column(name = "mx_id")
	private Integer mxId;
	
	@Column(name = "stage_name")
	private String stageName;

	public Integer getMxId() {
		return mxId;
	}

	public void setMxId(Integer mxId) {
		this.mxId = mxId;
	}

	@Column(name = "jl_id")
	private Integer jlId;

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	public String getPassFlagVal() {
		if (this.getPassFlag() != null) {
			if (passFlag == 0) {
				return "不合格";
			}
			if (passFlag == 1) {
				return "合格";
			}
			if (passFlag == 2) {
				return "无此项";
			}
			return "";
		}
		return "";
	}

	public Integer getJlId() {
		return jlId;
	}

	public void setJlId(Integer jlId) {
		this.jlId = jlId;
	}

	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}

	public boolean isPass() {
		return passFlag !=null && (passFlag == 1 || passFlag == 2);
	}
	
	

}
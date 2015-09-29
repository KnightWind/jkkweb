package com.jkkp.modules.basedata.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jkkp.utils.DateUtil;

@Table(name = "engineering_stage")
public class EngineeringStage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	public Integer status;
	@Column(name = "stag_name")
	public String stagName;
	public Integer pid;
	@Column(name = "stag_desc")
	public String stagDesc;
	public String remark;
	@Column(name = "create_time")
	public Date createTime;
	@Column(name = "ordr_by")
	public Integer ordrBy;
	public String abbreviation;

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getStatusHandle() {
		if (this.getStatus() != null) {
			if (status == 0) {
				return "隐藏";
			}
			if (status == 1) {
				return "显示";
			}
			return "";
		}
		return "";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStagName() {
		return stagName;
	}

	public void setStagName(String stagName) {
		this.stagName = stagName;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getStagDesc() {
		return stagDesc;
	}

	public void setStagDesc(String stagDesc) {
		this.stagDesc = stagDesc;
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

	public Integer getOrdrBy() {
		return ordrBy;
	}

	public void setOrdrBy(Integer ordrBy) {
		this.ordrBy = ordrBy;
	}

	public String getCreateTimeHandle() {
		if (createTime != null) {
			return DateUtil.formatDateTime(createTime);
		}
		return "";
	}
}
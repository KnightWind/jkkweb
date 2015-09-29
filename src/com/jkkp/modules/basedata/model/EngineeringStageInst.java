package com.jkkp.modules.basedata.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.jkkp.utils.DateUtil;

@Table(name = "engineering_stage_inst")
public class EngineeringStageInst {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	@Column(name = "gcd_id")
	public Integer gcdId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStagName() {
		return stagName;
	}

	public void setStagName(String stagName) {
		this.stagName = stagName;
	}

	public Integer getFinishFlag() {
		return finishFlag;
	}

	public void setFinishFlag(Integer finishFlag) {
		this.finishFlag = finishFlag;
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

	public Integer getJlId() {
		return jlId;
	}

	public void setJlId(Integer jlId) {
		this.jlId = jlId;
	}

	public Integer getReportPass() {
		return reportPass;
	}

	public void setReportPass(Integer reportPass) {
		this.reportPass = reportPass;
	}

	public Date getPassTime() {
		return passTime;
	}

	public void setPassTime(Date passTime) {
		this.passTime = passTime;
	}

	public Integer getGcdId() {
		return gcdId;
	}

	public void setGcdId(Integer gcdId) {
		this.gcdId = gcdId;
	}

	
	@Column(name = "stag_name")
	public String stagName;
	@Column(name = "finish_flag")
	public Integer finishFlag;
	@Column(name = "create_time")
	public Date createTime;
	@Column(name = "create_user")
	public String createUser;
	@Column(name = "jl_id")
	public Integer jlId;
	@Column(name = "number")
	public Integer number;
	@Column(name = "last_number")
	public Integer lastNumber;
	@Column(name = "report_pass")
	public Integer reportPass;
	@Column(name = "pass_time")
	public Date passTime;
	//20150807新加属性（指向engineering_stage表id）
	public Integer pid;
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}


	@Transient
	public String pass;
     
	public String getPass() {
		return pass;
	}

	public void setPass(boolean isPass) {
		this.finishFlag = isPass ? 1 : 0;
	}

	// 阶段是否已通过
	public String getFinishFlagVal() {
		if (this.getFinishFlag() != null) {
			if (finishFlag == 1) {
				return "已通过";
			}
			if (finishFlag == 0) {
				return "未通过";
			}
			return "";
		}
		return "";
	}

	// 用户验收
	public String getReportPassVal() {
		if (this.getReportPass() != null) {
			if (reportPass == 1) {
				return "已验收";
			}
			if (reportPass == 0) {
				return "尚未验收";
			}
		}
		return "尚未验收";
	}

	// 验收时间
	public String getPassTimeHandle() {
		if (this.getPassTime() != null) {
			return DateUtil.formatDateTime(passTime);
		}
		return "";
	}
	
	//报告创建时间
	public String getCreateTimeHandle(){
		if(this.getCreateTime()!=null){
			return DateUtil.formatDateTime(createTime);
		}
		return "";
	}
}
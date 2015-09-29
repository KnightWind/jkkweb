package com.jkkp.modules.basedata.model;

import java.util.Date;

import javax.persistence.*;

import com.jkkp.utils.DateUtil;

@Table(name = "engineerings")
public class Engineerings {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	public String community;
	public Integer uid;
	public Float space;
	public Float budget;
    private Integer status;
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "app_push_id")
	public Integer appPushId;

	public Integer getAppPushId() {
		return appPushId;
	}

	public void setAppPushId(Integer appPushId) {
		this.appPushId = appPushId;
	}

	public Integer getJlPushId() {
		return jlPushId;
	}

	public void setJlPushId(Integer jlPushId) {
		this.jlPushId = jlPushId;
	}

	@Column(name = "jl_push_id")	public Integer jlPushId;
	@Column(name = "dcate_id")	public Integer dcateId;
	public Integer method;
	@Column(name = "start_time")	public Date startTime;
	@Column(name = "end_time")	public Date endTime;
	@Column(name = "zx_stage")	public Integer zxStage;
	@Column(name = "zx_fund")	public Float zxFund;
	@Column(name = "jg_fund")	public Float jgFund;
	@Column(name = "to_fund")	public Float toFund;
	@Column(name = "sp_id")	public Integer spId;
	@Column(name = "sjs_id")	public Integer sjsId;
	@Column(name = "design_id")	public Integer designId;
	@Column(name = "contract_id")	public Integer contractId;
	@Column(name = "jl_id")	public Integer jlId;
	@Column(name = "jlbg_id")	public Integer jlbgId;
	@Column(name = "comp_id")	public Integer compId;
	@Column(name = "create_time")	public Date createTime;
	@Column(name = "pointx")	public String pointx;
	@Column(name = "pointy")	public String pointy;
	public String creauser;
	public String note;
	public Integer aid;

	

	public String getPointx() {
		return pointx;
	}

	public void setPointx(String pointx) {
		this.pointx = pointx;
	}

	public String getPointy() {
		return pointy;
	}

	public void setPointy(String pointy) {
		this.pointy = pointy;
	}

	public Integer getAid() {
		return aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Float getSpace() {
		return space;
	}

	public void setSpace(Float space) {
		this.space = space;
	}

	public Float getBudget() {
		return budget;
	}

	public void setBudget(Float budget) {
		this.budget = budget;
	}

	public Integer getDcateId() {
		return dcateId;
	}

	public void setDcateId(Integer dcateId) {
		this.dcateId = dcateId;
	}

	public Integer getMethod() {
		return method;
	}

	public void setMethod(Integer method) {
		this.method = method;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getZxStage() {
		return zxStage;
	}

	public void setZxStage(Integer zxStage) {
		this.zxStage = zxStage;
	}

	public Float getZxFund() {
		return zxFund;
	}

	public void setZxFund(Float zxFund) {
		this.zxFund = zxFund;
	}

	public Float getJgFund() {
		return jgFund;
	}

	public void setJgFund(Float jgFund) {
		this.jgFund = jgFund;
	}

	public Float getToFund() {
		return toFund;
	}

	public void setToFund(Float toFund) {
		this.toFund = toFund;
	}

	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public Integer getSjsId() {
		return sjsId;
	}

	public void setSjsId(Integer sjsId) {
		this.sjsId = sjsId;
	}

	public Integer getDesignId() {
		return designId;
	}

	public void setDesignId(Integer designId) {
		this.designId = designId;
	}

	public Integer getContractId() {
		return contractId;
	}

	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}

	public Integer getJlId() {
		return jlId;
	}

	public void setJlId(Integer jlId) {
		this.jlId = jlId;
	}

	public Integer getJlbgId() {
		return jlbgId;
	}

	public void setJlbgId(Integer jlbgId) {
		this.jlbgId = jlbgId;
	}

	public Integer getCompId() {
		return compId;
	}

	public void setCompId(Integer compId) {
		this.compId = compId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreauser() {
		return creauser;
	}

	public void setCreauser(String creauser) {
		this.creauser = creauser;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	// 获取装修阶段的值
	public String getStageVal() {
		if (this.getZxStage() != null) {
			if (zxStage == 10) {
				return "待开工";
			}
			if (zxStage == 20) {
				return "开工";
			}
			if (zxStage == 30) {
				return "水电";
			}
			if (zxStage == 40) {
				return "瓦工";
			}
			if (zxStage == 50) {
				return "竣工";
			}
			return "";
		}
		return "";
	}

	// 处理开工日期
	public String getStartTimeHandle() {
		if (this.getStartTime() != null) {
			return DateUtil.formatDate(startTime);
		}
		return "";
	}

	// 处理竣工日期
	public String getEndTimeHandle() {
		if (this.getEndTime() != null) {
			return DateUtil.formatDate(endTime);
		}
		return "尚未竣工";
	}

	// 处理创建日期
	public String getCreateTimeHandle() {
		if (this.getCreateTime() != null) {
			return DateUtil.formatDateTime(createTime);
		}
		return "";
	}
	
	//支付状态
	public String getStatusVal(){
		if(this.getStatus()!=null){
			return status==1?"已结款":"未结款";
		}
		return "未结款";
	}
}
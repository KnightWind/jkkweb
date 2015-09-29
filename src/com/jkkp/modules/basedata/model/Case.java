package com.jkkp.modules.basedata.model;


import java.util.Date;

import javax.persistence.*;

@Table(name = "cases")
public class Case {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "sp_id")
	private Integer spId;
	@Column(name = "design_id")
	private Integer designId;
	@Column(name = "sjs_id")
	private Integer sjsId;
	@Column(name = "gcd_id")
	private Integer gcdId;
	@Column(name = "jl_id")
	private Integer jlId;
	@Column(name = "topic_id")
	private Integer topicId;
	@Column(name = "case_source")
	private Integer caseSource;
	@Column(name = "comment_count")
	private Integer commentCount;
	@Column(name = "browse_count")
	private Integer browseCount;
	private Integer aid;
	private Integer style;
	@Column(name = "house_type")
	private Integer houseType;
	private Float budget;
	
	public Float getBudget() {
		return budget;
	}
	public void setBudget(Float budget) {
		this.budget = budget;
	}
	public Integer getAid() {
		return aid;
	}
	public Integer getStyle() {
		return style;
	}
	public void setStyle(Integer style) {
		this.style = style;
	}
	public Integer getHouseType() {
		return houseType;
	}
	public void setHouseType(Integer houseType) {
		this.houseType = houseType;
	}
	public Integer getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}
	public Integer getBrowseCount() {
		return browseCount;
	}
	public void setBrowseCount(Integer browseCount) {
		this.browseCount = browseCount;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public Integer getCaseSource() {
		return caseSource;
	}
	public void setCaseSource(Integer caseSource) {
		this.caseSource = caseSource;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSpId() {
		return spId;
	}
	public void setSpId(Integer spId) {
		this.spId = spId;
	}
	public Integer getDesignId() {
		return designId;
	}
	public void setDesignId(Integer designId) {
		this.designId = designId;
	}
	public Integer getSjsId() {
		return sjsId;
	}
	public void setSjsId(Integer sjsId) {
		this.sjsId = sjsId;
	}

	public Integer getGcdId() {
		return gcdId;
	}
	public void setGcdId(Integer gcdId) {
		this.gcdId = gcdId;
	}
	public Integer getJlId() {
		return jlId;
	}
	public void setJlId(Integer jlId) {
		this.jlId = jlId;
	}
	public Integer getTopicId() {
		return topicId;
	}
	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name = "create_time")
	protected Date createTime;
	@Column(name = "create_user")
	private String createUser;
	private String remark;
	private Integer method;
	private Float space;
	private String community;
	public Integer getMethod() {
		return method;
	}
	public void setMethod(Integer method) {
		this.method = method;
	}
	
	public Float getSpace() {
		return space;
	}
	public void setSpace(Float space) {
		this.space = space;
	}
	public String getCommunity() {
		return community;
	}
	public void setCommunity(String community) {
		this.community = community;
	}
	public Integer getKgdFlag() {
		return kgdFlag;
	}
	public Integer getZxStage() {
		return zxStage;
	}
	public void setZxStage(Integer zxStage) {
		this.zxStage = zxStage;
	}
	public String getKgdLinktel() {
		return kgdLinktel;
	}
	public void setKgdLinktel(String kgdLinktel) {
		this.kgdLinktel = kgdLinktel;
	}
	@Column(name = "zx_fund")
	private Float zxFund;
	@Column(name = "kgd_flag")
	private  Integer kgdFlag;
	public Float getZxFund() {
		return zxFund;
	}
	public void setZxFund(Float zxFund) {
		this.zxFund = zxFund;
	}
	public void setKgdFlag(Integer kgdFlag) {
		this.kgdFlag = kgdFlag;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	@Column(name = "zx_stage")
	private  Integer zxStage;
	@Column(name = "kgd_linktel")
	private String kgdLinktel;
	private String uid;
	
}
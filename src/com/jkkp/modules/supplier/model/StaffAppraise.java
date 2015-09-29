package com.jkkp.modules.supplier.model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name = "staff_appraise")
public class StaffAppraise {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "sj_score")
	private Float sjScore;
	@Column(name = "sg_score")
	private Float sgScore;
	@Column(name = "wm_score")
	private Float wmScore;
	@Column(name = "yq_score")
	private Float yqScore;
	@Column(name = "fw_score")
	private Float fwScore;
	@Column(name = "gl_score")
	private Float glScore;
	@Column(name = "sy_score")
	private Float syScore;
	public Float getSyScore() {
		return syScore;
	}
	public void setSyScore(Float syScore) {
		this.syScore = syScore;
	}
	private Float money;
	public Float getMoney() {
		return money;
	}
	public void setMoney(Float money) {
		this.money = money;
	}
	public Float getGlScore() {
		return glScore;
	}
	public void setGlScore(Float glScore) {
		this.glScore = glScore;
	}
	private String explain;
	@Column(name = "create_time")
	private Date createTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Float getSjScore() {
		return sjScore;
	}
	public void setSjScore(Float sjScore) {
		this.sjScore = sjScore;
	}
	public Float getSgScore() {
		return sgScore;
	}
	public void setSgScore(Float sgScore) {
		this.sgScore = sgScore;
	}
	public Float getWmScore() {
		return wmScore;
	}
	public void setWmScore(Float wmScore) {
		this.wmScore = wmScore;
	}
	public Float getYqScore() {
		return yqScore;
	}
	public void setYqScore(Float yqScore) {
		this.yqScore = yqScore;
	}
	public Float getFwScore() {
		return fwScore;
	}
	public void setFwScore(Float fwScore) {
		this.fwScore = fwScore;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Integer getGcdId() {
		return gcdId;
	}
	public void setGcdId(Integer gcdId) {
		this.gcdId = gcdId;
	}
	private Integer sid;
	@Column(name = "gcd_id")
	private Integer gcdId;
	private Integer uid;
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
}

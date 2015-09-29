package com.jkkp.modules.supplier.model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name = "supplier_appraise")
public class SupplierAppraise {
	public Float getGtScore() {
		return gtScore;
	}
	public void setGtScore(Float gtScore) {
		this.gtScore = gtScore;
	}
	public Float getZyScore() {
		return zyScore;
	}
	public void setZyScore(Float zyScore) {
		this.zyScore = zyScore;
	}
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
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getSpId() {
		return spId;
	}
	public void setSpId(Integer spId) {
		this.spId = spId;
	}
	public Integer getGcdId() {
		return gcdId;
	}
	public void setGcdId(Integer gcdId) {
		this.gcdId = gcdId;
	}
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
	@Column(name = "sd_score")
	private Float sdScore;
	@Column(name = "gt_score")
	private Float gtScore;
	@Column(name = "zy_score")
	private Float zyScore;
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
	public Float getSdScore() {
		return sdScore;
	}
	public void setSdScore(Float sdScore) {
		this.sdScore = sdScore;
	}
	private String explain;
	@Column(name = "create_time")
	private Date createTime;
	private Integer uid;
	@Column(name = "sp_id")
	private Integer spId;
	@Column(name = "gcd_id")
	private Integer gcdId;
}

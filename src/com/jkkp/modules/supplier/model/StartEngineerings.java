package com.jkkp.modules.supplier.model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name = "start_engineerings")
public class StartEngineerings {
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGcdId() {
		return gcdId;
	}
	public void setGcdId(Integer gcdId) {
		this.gcdId = gcdId;
	}
	public Integer getBlueprintFlag() {
		return blueprintFlag;
	}
	public void setBlueprintFlag(Integer blueprintFlag) {
		this.blueprintFlag = blueprintFlag;
	}
	public Integer getBlueprintItem() {
		return blueprintItem;
	}
	public void setBlueprintItem(Integer blueprintItem) {
		this.blueprintItem = blueprintItem;
	}
	public Integer getQuotedFlag() {
		return quotedFlag;
	}
	public void setQuotedFlag(Integer quotedFlag) {
		this.quotedFlag = quotedFlag;
	}
	public Integer getOblivionItem() {
		return oblivionItem;
	}
	public void setOblivionItem(Integer oblivionItem) {
		this.oblivionItem = oblivionItem;
	}
	public String getOtherRequirement() {
		return otherRequirement;
	}
	public void setOtherRequirement(String otherRequirement) {
		this.otherRequirement = otherRequirement;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "gcd_id")
	private Integer gcdId;
	@Column(name = "blueprint_flag")
	private Integer blueprintFlag;
	@Column(name = "blueprint_item")
	private Integer blueprintItem;
	@Column(name = "quoted_flag")
	private Integer quotedFlag;
	@Column(name = "oblivion_item")
	private Integer oblivionItem;
	@Column(name = "other_requirement")
	private String otherRequirement;
	private String condition;
	private String remarks;
	@Column(name = "create_time")
	private Date createTime;
}

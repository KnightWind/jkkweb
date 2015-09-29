package com.jkkp.modules.supplier.model;

import java.util.Date;

import javax.persistence.*;


@Table(name = "jiakebao")
public class Jiakebao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "agreement_id")
	private Integer agreementId;
	@Column(name = "gcd_id")
	private Integer gcdId;
	@Column(name = "jl_id")
	private Integer jlId;
	private Float paymoney;
	@Column(name = "create_user")
	private String createUser;
	@Column(name = "create_time")
	private Date createTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAgreementId() {
		return agreementId;
	}
	public void setAgreementId(Integer agreementId) {
		this.agreementId = agreementId;
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
	public Float getPaymoney() {
		return paymoney;
	}
	public void setPaymoney(Float paymoney) {
		this.paymoney = paymoney;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
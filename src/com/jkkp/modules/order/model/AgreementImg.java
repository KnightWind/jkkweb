package com.jkkp.modules.order.model;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "agreement_img")
public class AgreementImg {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Integer id;
	private String picture;
	@Column(name = "agreement_id")
	 private Integer agreementId;
	public Integer getAgreementId() {
		return agreementId;
	}
	public void setAgreementId(Integer agreementId) {
		this.agreementId = agreementId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
}

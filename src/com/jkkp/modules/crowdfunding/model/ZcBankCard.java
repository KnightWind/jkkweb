package com.jkkp.modules.crowdfunding.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/***
 * 银行卡表及支付密码
 * @author Administrator
 *
 */
@Table(name = "zc_bank_card")
public class ZcBankCard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "card_no")
	private String cardNo;
	
	@Column(name = "bank_name")
	private String bankName;
	
	@Column(name = "region_id")
	private Integer regionId;
	
	@Column(name = "pay_pwd")
	private String payPwd;
	
	@Column(name = "create_time")
	protected Date createTime;
	
	@Column(name = "sp_id")
	private Integer spId;
	

	public ZcBankCard(String cardNo, String bankName, Integer regionId,
			String payPwd, Integer spId) {
		super();
		this.cardNo = cardNo;
		this.bankName = bankName;
		this.regionId = regionId;
		this.payPwd = payPwd;
		this.spId = spId;
	}

	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public ZcBankCard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public String getPayPwd() {
		return payPwd;
	}

	public void setPayPwd(String payPwd) {
		this.payPwd = payPwd;
	}
}

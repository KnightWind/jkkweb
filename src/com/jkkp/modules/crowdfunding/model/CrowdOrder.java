package com.jkkp.modules.crowdfunding.model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "zc_order")
public class CrowdOrder {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="member_id")
	private Integer memberId;
	
	@Column(name="order_price")
	private Float orderPrice;
	
	@Column(name="order_code")
	private String orderCode;
	
	@Column(name="money_state")
	protected Integer moneyState;
	
	@Column(name="express_state")
	private Integer expressState;
	
	@Column(name="create_date")
	protected Date createDate;
	
	@Column(name="express_id")
	private Integer expressId;
	
	@Column(name="supplier_id")
	private Integer supplierId;
	
	@Column(name="retainage")
	protected Float retainage;
	
	@Column(name="deposit")
	protected Float deposit;
	
	@Column(name="payment_model")
	private Integer paymentModel;
	
	@Column(name="serial_code")
	private String serialCode;
	
	@Column(name="remark")
	private String remark;

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Float getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(Float orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Integer getMoneyState() {
		return moneyState;
	}

	public void setMoneyState(Integer moneyState) {
		this.moneyState = moneyState;
	}

	public Integer getExpressState() {
		return expressState;
	}

	public void setExpressState(Integer expressState) {
		this.expressState = expressState;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getExpressId() {
		return expressId;
	}

	public void setExpressId(Integer expressId) {
		this.expressId = expressId;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public Float getRetainage() {
		return retainage;
	}

	public void setRetainage(Float retainage) {
		this.retainage = retainage;
	}

	public Float getDeposit() {
		return deposit;
	}

	public void setDeposit(Float deposit) {
		this.deposit = deposit;
	}

	public Integer getPaymentModel() {
		return paymentModel;
	}

	public void setPaymentModel(Integer paymentModel) {
		this.paymentModel = paymentModel;
	}

	public String getSerialCode() {
		return serialCode;
	}

	public void setSerialCode(String serialCode) {
		this.serialCode = serialCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	// 支付类型
	public String getPayTypeVal() {
		if (this.moneyState != null) {
			if (this.moneyState == 2) {
				return "定金+尾款";
			}
			if (this.moneyState == 1) {
				return "定金";
			}
		}
		return "尚未付款";
	}
	
}

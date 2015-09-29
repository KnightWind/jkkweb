package com.jkkp.modules.crowdfunding.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jkkp.utils.DateUtil;

/**
 * 众筹订单实体类
 * 
 * @author Administrator
 *
 */
@Table(name = "zc_order")
public class ActiivityOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "order_code")
	private String orderCode; // 订单编码

	@Column(name = "member_id")
	private Integer memberId; // 用户信息

	@Column(name = "order_price")
	private Double orderPrice; // 订单价格

	private Integer moneyState; // 付款状态 0未付款 1 付定金 2 付尾款

	private Integer expressState; // 快递状态 1 待收货 2 已收货

	private Double deposit; // 定金

	private Double retainage; // 尾款

	@Column(name = "create_date")
	private Date createDate; // 订单创建时间

	@Column(name = "express_id")
	private Integer expressId; // 订单物流id

	@Column(name = "supplier_id")
	private Integer supplierId; // 商家id

	@Column(name = "payment_model")
	private String paymentModel; // 支付方式 1 支付宝 2 易宝

	@Column(name = "pay_type")
	private Integer payType; // 1普通支付 2扫码支付

	@Column(name = "serial_code")
	private String serialCode; // 流水号

	@Column(name = "deposit_id")
	private String depositId; // 定金订单号

	@Column(name = "use_state")
	private Integer useState; // 定金使用状态 0未使用 1已使用

	private String remark; // 标记

	@Column(name = "express_type")
	private Integer expressType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
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

	public Double getDeposit() {
		return deposit;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}

	public Double getRetainage() {
		return retainage;
	}

	public void setRetainage(Double retainage) {
		this.retainage = retainage;
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

	public String getPaymentModel() {
		return paymentModel;
	}

	public void setPaymentModel(String paymentModel) {
		this.paymentModel = paymentModel;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public String getSerialCode() {
		return serialCode;
	}

	public void setSerialCode(String serialCode) {
		this.serialCode = serialCode;
	}

	public String getDepositId() {
		return depositId;
	}

	public void setDepositId(String depositId) {
		this.depositId = depositId;
	}

	public Integer getUseState() {
		return useState;
	}

	public void setUseState(Integer useState) {
		this.useState = useState;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getExpressType() {
		return expressType;
	}

	public void setExpressType(Integer expressType) {
		this.expressType = expressType;
	}

	// 定金+尾款
	public Double getMoneyCount() {
		double moneyCount = 0d;
		if(this.retainage != null) {
			moneyCount += this.retainage;
		}
		if(this.deposit != null) {
			moneyCount += this.deposit;
		}
		return moneyCount;
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
		return "";
	}

	// 创建时间
	public String getCreateTimeHandle() {
		if (this.getCreateDate() != null) {
			return DateUtil.formatDateTime(createDate);
		}
		return "";
	}

	// 是否有发货操作
	public Boolean getLogisticsHandle() {
		if (this.expressState != null  && this.expressState == 2) {
			return false;
		}
		if (this.expressState != null && this.expressState == 1) {
			return false;
		}
		if(this.moneyState!=2){
			return false;
		}	
		return true;
	}

	@Override
	public String toString() {
		return "ActiivityOrder [id=" + id + ", orderCode=" + orderCode + ", memberId=" + memberId + ", orderPrice="
				+ orderPrice + ", moneyState=" + moneyState + ", expressState=" + expressState + ", deposit=" + deposit
				+ ", retainage=" + retainage + ", createDate=" + createDate + ", expressId=" + expressId
				+ ", supplierId=" + supplierId + ", paymentModel=" + paymentModel + ", payType=" + payType
				+ ", serialCode=" + serialCode + ", depositId=" + depositId + ", useState=" + useState + ", remark="
				+ remark + ", expressType=" + expressType + "]";
	}
	
	public String getExpressStateVal(){
		if(this.getExpressState()==null&&moneyState!=null&&moneyState==2){
			return "待发货";
		}
		if(this.getExpressState()!=null){
			if(moneyState!=null&&moneyState==2&&expressState!=1&&expressState!=2){
				return "待发货";
			}
			if(expressState==1){
				return "待收货";
			}
			if(expressState==2){
				return "已收货";
			}
		}
		return "暂无物流状态";
	}
	
}

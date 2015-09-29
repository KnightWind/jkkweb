package com.jkkp.modules.order.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "payment_record")
public class PaymentRecord implements Serializable {
	private static final long serialVersionUID = 8865970102317878049L;
	public static final int TYPE_DEPOSIT = 1; // 订金
	public static final int TYPE_WALLET = 2; // 充值
	public static final int TYPE_JIANLI = 3; // 监理款
	public static final int TYPE_ACTIVITYCARD = 4; // 微信活动卡
	
	public static final int TYPE_ACTIVITY_DJ = 5;   //众筹活动定金
	public static final int TYPE_ACTIVITY_WK = 6;   //众筹活动尾款
	public static final int TYPE_ACTIVITY_DJWK = 7; //众筹活动定金和尾款
	public static final int TYPE_QR_DJ_CODE = 8; //QR支付 定金
	public static final int TYPE_QR_WK_CODE = 9; //QR支付 尾款
	public static final int TYPE_JL_SERVICE = 10; //监理服务

	public static final int PAY_TYPE_ALIPAY = 1; // 支付宝
	public static final int PAY_TYPE_YEEPAY = 2; // 易宝
	public static final int PAY_TYPE_WEIXIN = 3; // 微信支付

	public static final int LOG_TYPE_PAY = 1; // 支付
	public static final int LOG_TYPE_REFUND = 2; // 退款
	
	@Id
	@Column(name = "record_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer recordId;

	@Column(name = "serial_code")
	private String serialCode;

	@Column(name = "business_id")
	private Integer businessId;

	@Column(name = "type")
	private Integer type;

	@Column(name = "create_date")
	private Date createDate;

	private Float amount;

	/**
	 * 支付途径 1：微信端支付 2：Android支付 3：IOS支付 4：其它
	 */
	@Column(name = "pay_type")
	private Integer payType;

	private String title;

	@Column(name = "trade_no")
	private String tradeNo;

	private String status;

	@Column(name = "notify_time")
	private Date notifyTime;
	
	/**
	 * 日志类型 1：支付日志  2：退款日志
	 */
	@Column(name = "log_Type")
	private Integer logType;
	
	/**
	 * 批次号
	 *  // 
		// 如果是支付宝，则取 接口batch_no字段值
		 * 回调根据该值更新图款日志
	 */
	@Column(name = "batch_no")
	private String batchNo;
	//易宝退款退款请求号
	//回调根据该值来更新退款日志
	private String requestid;
	//易宝退款流水号
	//是易宝，取接口refundexternalid 易宝退款流水号易宝退款流水号，易宝系统中唯一；退款成功后更新
	private String refundexternalid;
	
	private String recommendName; //推荐人姓名
	
	private String recommendMobile;//推荐人手机号码
	
	private Integer dicountType; //折扣类型 1.扫码支付
	
	private Float dicountFee; //折扣金额
	
	public Float getDicountFee() {
		return dicountFee;
	}

	public void setDicountFee(Float dicountFee) {
		this.dicountFee = dicountFee;
	}

	public Integer getDicountType() {
		return dicountType;
	}

	public void setDicountType(Integer dicountType) {
		this.dicountType = dicountType;
	}
	
	public String getRecommendName() {
		return recommendName;
	}

	public void setRecommendName(String recommendName) {
		this.recommendName = recommendName;
	}

	public String getRecommendMobile() {
		return recommendMobile;
	}

	public void setRecommendMobile(String recommendMobile) {
		this.recommendMobile = recommendMobile;
	}

	public String getRequestid() {
		return requestid;
	}

	public void setRequestid(String requestid) {
		this.requestid = requestid;
	}

	public String getRefundexternalid() {
		return refundexternalid;
	}

	public void setRefundexternalid(String refundexternalid) {
		this.refundexternalid = refundexternalid;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public Integer getLogType() {
		return logType;
	}

	public void setLogType(Integer logType) {
		this.logType = logType;
	}

	public Date getNotifyTime() {
		return notifyTime;
	}

	public void setNotifyTime(Date notifyTime) {
		this.notifyTime = notifyTime;
	}

	/**
	 * 支付方式，1：支付宝，2：易宝 3其它
	 */
	private Integer payway;

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public String getSerialCode() {
		return serialCode;
	}

	public void setSerialCode(String serialCode) {
		this.serialCode = serialCode;
	}

	public Integer getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getPayway() {
		return payway;
	}

	public void setPayway(Integer payway) {
		this.payway = payway;
	}

	@Override
	public String toString() {
		return "PaymentRecord [recordId=" + recordId + ", serialCode=" + serialCode + ", businessId=" + businessId
				+ ", type=" + type + ", createDate=" + createDate + ", amount=" + amount + ", payType=" + payType
				+ ", title=" + title + ", tradeNo=" + tradeNo + ", status=" + status + ", notifyTime=" + notifyTime
				+ ", logType=" + logType + ", batchNo=" + batchNo + ", requestid=" + requestid + ", refundexternalid="
				+ refundexternalid + ", payway=" + payway + "]";
	}

}

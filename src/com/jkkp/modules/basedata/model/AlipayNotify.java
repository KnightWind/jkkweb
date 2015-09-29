package com.jkkp.modules.basedata.model;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "alipay_notify")
public class AlipayNotify {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "trade_no")
    private String tradeNo;

    private Integer discount;

    @Column(name = "payment_type")
    private String paymentType;

    private String subject;

    private String body;

    private BigDecimal price;

    private String quantity;

    @Column(name = "total_fee")
    private String totalFee;

    @Column(name = "trade_status")
    private String tradeStatus;

    @Column(name = "refund_status")
    private String refundStatus;

    @Column(name = "seller_email")
    private String sellerEmail;

    @Column(name = "seller_id")
    private String sellerId;

    @Column(name = "buyer_id")
    private String buyerId;

    @Column(name = "buyer_email")
    private String buyerEmail;

    @Column(name = "gmt_create")
    private String gmtCreate;

    @Column(name = "is_total_fee_adjust")
    private String isTotalFeeAdjust;

    @Column(name = "gmt_payment")
    private String gmtPayment;

    @Column(name = "gmt_close")
    private String gmtClose;

    @Column(name = "gmt_refund")
    private String gmtRefund;

    @Column(name = "notify_time")
    private String notifyTime;

    @Column(name = "notify_type")
    private String notifyType;

    @Column(name = "notify_id")
    private String notifyId;

    @Column(name = "sign_type")
    private String signType;

    private String sign;

    @Column(name = "extra_common_param")
    private String extraCommonParam;

    @Column(name = "bank_seq_no")
    private String bankSeqNo;

    @Column(name = "out_trade_no")
    private String outTradeNo;

    @Column(name = "use_coupon")
    private String useCoupon;

    private String verfy;

    /**
     * 交易类型：Q,券；T：商品；S:服务订单
     */
    private String type;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return trade_no
     */
    public String getTradeNo() {
        return tradeNo;
    }

    /**
     * @param tradeNo
     */
    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    /**
     * @return discount
     */
    public Integer getDiscount() {
        return discount;
    }

    /**
     * @param discount
     */
    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    /**
     * @return payment_type
     */
    public String getPaymentType() {
        return paymentType;
    }

    /**
     * @param paymentType
     */
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * @return subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return body
     */
    public String getBody() {
        return body;
    }

    /**
     * @param body
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * @return price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @param price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * @return quantity
     */
    public String getQuantity() {
        return quantity;
    }

    /**
     * @param quantity
     */
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    /**
     * @return total_fee
     */
    public String getTotalFee() {
        return totalFee;
    }

    /**
     * @param totalFee
     */
    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    /**
     * @return trade_status
     */
    public String getTradeStatus() {
        return tradeStatus;
    }

    /**
     * @param tradeStatus
     */
    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    /**
     * @return refund_status
     */
    public String getRefundStatus() {
        return refundStatus;
    }

    /**
     * @param refundStatus
     */
    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    /**
     * @return seller_email
     */
    public String getSellerEmail() {
        return sellerEmail;
    }

    /**
     * @param sellerEmail
     */
    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    /**
     * @return seller_id
     */
    public String getSellerId() {
        return sellerId;
    }

    /**
     * @param sellerId
     */
    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    /**
     * @return buyer_id
     */
    public String getBuyerId() {
        return buyerId;
    }

    /**
     * @param buyerId
     */
    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    /**
     * @return buyer_email
     */
    public String getBuyerEmail() {
        return buyerEmail;
    }

    /**
     * @param buyerEmail
     */
    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    /**
     * @return gmt_create
     */
    public String getGmtCreate() {
        return gmtCreate;
    }

    /**
     * @param gmtCreate
     */
    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * @return is_total_fee_adjust
     */
    public String getIsTotalFeeAdjust() {
        return isTotalFeeAdjust;
    }

    /**
     * @param isTotalFeeAdjust
     */
    public void setIsTotalFeeAdjust(String isTotalFeeAdjust) {
        this.isTotalFeeAdjust = isTotalFeeAdjust;
    }

    /**
     * @return gmt_payment
     */
    public String getGmtPayment() {
        return gmtPayment;
    }

    /**
     * @param gmtPayment
     */
    public void setGmtPayment(String gmtPayment) {
        this.gmtPayment = gmtPayment;
    }

    /**
     * @return gmt_close
     */
    public String getGmtClose() {
        return gmtClose;
    }

    /**
     * @param gmtClose
     */
    public void setGmtClose(String gmtClose) {
        this.gmtClose = gmtClose;
    }

    /**
     * @return gmt_refund
     */
    public String getGmtRefund() {
        return gmtRefund;
    }

    /**
     * @param gmtRefund
     */
    public void setGmtRefund(String gmtRefund) {
        this.gmtRefund = gmtRefund;
    }

    /**
     * @return notify_time
     */
    public String getNotifyTime() {
        return notifyTime;
    }

    /**
     * @param notifyTime
     */
    public void setNotifyTime(String notifyTime) {
        this.notifyTime = notifyTime;
    }

    /**
     * @return notify_type
     */
    public String getNotifyType() {
        return notifyType;
    }

    /**
     * @param notifyType
     */
    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    /**
     * @return notify_id
     */
    public String getNotifyId() {
        return notifyId;
    }

    /**
     * @param notifyId
     */
    public void setNotifyId(String notifyId) {
        this.notifyId = notifyId;
    }

    /**
     * @return sign_type
     */
    public String getSignType() {
        return signType;
    }

    /**
     * @param signType
     */
    public void setSignType(String signType) {
        this.signType = signType;
    }

    /**
     * @return sign
     */
    public String getSign() {
        return sign;
    }

    /**
     * @param sign
     */
    public void setSign(String sign) {
        this.sign = sign;
    }

    /**
     * @return extra_common_param
     */
    public String getExtraCommonParam() {
        return extraCommonParam;
    }

    /**
     * @param extraCommonParam
     */
    public void setExtraCommonParam(String extraCommonParam) {
        this.extraCommonParam = extraCommonParam;
    }

    /**
     * @return bank_seq_no
     */
    public String getBankSeqNo() {
        return bankSeqNo;
    }

    /**
     * @param bankSeqNo
     */
    public void setBankSeqNo(String bankSeqNo) {
        this.bankSeqNo = bankSeqNo;
    }

    /**
     * @return out_trade_no
     */
    public String getOutTradeNo() {
        return outTradeNo;
    }

    /**
     * @param outTradeNo
     */
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    /**
     * @return use_coupon
     */
    public String getUseCoupon() {
        return useCoupon;
    }

    /**
     * @param useCoupon
     */
    public void setUseCoupon(String useCoupon) {
        this.useCoupon = useCoupon;
    }

    /**
     * @return verfy
     */
    public String getVerfy() {
        return verfy;
    }

    /**
     * @param verfy
     */
    public void setVerfy(String verfy) {
        this.verfy = verfy;
    }

    /**
     * 获取交易类型：Q,券；T：商品；S:服务订单
     *
     * @return type - 交易类型：Q,券；T：商品；S:服务订单
     */
    public String getType() {
        return type;
    }

    /**
     * 设置交易类型：Q,券；T：商品；S:服务订单
     *
     * @param type 交易类型：Q,券；T：商品；S:服务订单
     */
    public void setType(String type) {
        this.type = type;
    }
}
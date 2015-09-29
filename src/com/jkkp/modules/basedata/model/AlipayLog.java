package com.jkkp.modules.basedata.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "alipay_log")
public class AlipayLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 商品信息
     */
    private String body;

    /**
     * 付款账号
     */
    @Column(name = "buyer_email")
    private String buyerEmail;

    /**
     * 支付宝购买者id
     */
    @Column(name = "buyer_id")
    private Long buyerId;

    /**
     * 支付宝接口类型
     */
    private String exterface;

    /**
     * 交易成功是否
     */
    @Column(name = "is_success")
    private String isSuccess;

    /**
     * 通知参数
     */
    @Column(name = "notify_id")
    private String notifyId;

    /**
     * 通知时间
     */
    @Column(name = "notify_time")
    private Date notifyTime;

    /**
     * 通知类型
     */
    @Column(name = "notify_type")
    private String notifyType;

    /**
     * 订单号
     */
    @Column(name = "out_trade_no")
    private Integer outTradeNo;

    /**
     * 会员id
     */
    private Integer uid;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 订单标号，多个用逗号分割
     */
    @Column(name = "order_id")
    private String orderId;

    /**
     * 验证是否成功
     */
    private String verify;

    /**
     * 订单类型:T：商品订单，Q：券订单，S：服务订单
     */
    @Column(name = "order_type")
    private String orderType;

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
     * 获取商品信息
     *
     * @return body - 商品信息
     */
    public String getBody() {
        return body;
    }

    /**
     * 设置商品信息
     *
     * @param body 商品信息
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * 获取付款账号
     *
     * @return buyer_email - 付款账号
     */
    public String getBuyerEmail() {
        return buyerEmail;
    }

    /**
     * 设置付款账号
     *
     * @param buyerEmail 付款账号
     */
    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    /**
     * 获取支付宝购买者id
     *
     * @return buyer_id - 支付宝购买者id
     */
    public Long getBuyerId() {
        return buyerId;
    }

    /**
     * 设置支付宝购买者id
     *
     * @param buyerId 支付宝购买者id
     */
    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    /**
     * 获取支付宝接口类型
     *
     * @return exterface - 支付宝接口类型
     */
    public String getExterface() {
        return exterface;
    }

    /**
     * 设置支付宝接口类型
     *
     * @param exterface 支付宝接口类型
     */
    public void setExterface(String exterface) {
        this.exterface = exterface;
    }

    /**
     * 获取交易成功是否
     *
     * @return is_success - 交易成功是否
     */
    public String getIsSuccess() {
        return isSuccess;
    }

    /**
     * 设置交易成功是否
     *
     * @param isSuccess 交易成功是否
     */
    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }

    /**
     * 获取通知参数
     *
     * @return notify_id - 通知参数
     */
    public String getNotifyId() {
        return notifyId;
    }

    /**
     * 设置通知参数
     *
     * @param notifyId 通知参数
     */
    public void setNotifyId(String notifyId) {
        this.notifyId = notifyId;
    }

    /**
     * 获取通知时间
     *
     * @return notify_time - 通知时间
     */
    public Date getNotifyTime() {
        return notifyTime;
    }

    /**
     * 设置通知时间
     *
     * @param notifyTime 通知时间
     */
    public void setNotifyTime(Date notifyTime) {
        this.notifyTime = notifyTime;
    }

    /**
     * 获取通知类型
     *
     * @return notify_type - 通知类型
     */
    public String getNotifyType() {
        return notifyType;
    }

    /**
     * 设置通知类型
     *
     * @param notifyType 通知类型
     */
    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    /**
     * 获取订单号
     *
     * @return out_trade_no - 订单号
     */
    public Integer getOutTradeNo() {
        return outTradeNo;
    }

    /**
     * 设置订单号
     *
     * @param outTradeNo 订单号
     */
    public void setOutTradeNo(Integer outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    /**
     * 获取会员id
     *
     * @return uid - 会员id
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 设置会员id
     *
     * @param uid 会员id
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取订单标号，多个用逗号分割
     *
     * @return order_id - 订单标号，多个用逗号分割
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 设置订单标号，多个用逗号分割
     *
     * @param orderId 订单标号，多个用逗号分割
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取验证是否成功
     *
     * @return verify - 验证是否成功
     */
    public String getVerify() {
        return verify;
    }

    /**
     * 设置验证是否成功
     *
     * @param verify 验证是否成功
     */
    public void setVerify(String verify) {
        this.verify = verify;
    }

    /**
     * 获取订单类型:T：商品订单，Q：券订单，S：服务订单
     *
     * @return order_type - 订单类型:T：商品订单，Q：券订单，S：服务订单
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * 设置订单类型:T：商品订单，Q：券订单，S：服务订单
     *
     * @param orderType 订单类型:T：商品订单，Q：券订单，S：服务订单
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}
package com.jkkp.modules.order.model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.*;

@Table(name = "refund")
public class Refund {
    /**
     * 服务编号（退货编号）
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 订单编号
     */
    @Column(name = "order_id")
    private Integer orderId;

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
     * 审核时间
     */
    @Column(name = "review_time")
    private Date reviewTime;

    /**
     * 退款状态：0待审核1审核通过（待退款）2退款成功-1退款失败（仲裁失败）-2审核失败-3取消退款
     */
    private Byte status;

    /**
     * 如果使用优惠券，返还给用户折扣抵现的金额
     */
    @Column(name = "refund_money_discount")
    private BigDecimal refundMoneyDiscount;

    /**
     * 返回给用户金额
     */
    @Column(name = "refund_money_buyer")
    private BigDecimal refundMoneyBuyer;

    /**
     * 返回给商家的金额
     */
    @Column(name = "refund_money_sp")
    private BigDecimal refundMoneySp;

    /**
     * 供货商id
     */
    @Column(name = "sp_id")
    private Integer spId;

    /**
     * 取货方式0上门自取1用户自邮
     */
    @Column(name = "back_method")
    private Byte backMethod;

    /**
     * 退款方式0返回原卡（目前只有一种）
     */
    @Column(name = "refund_method")
    private Byte refundMethod;

    /**
     * 退款描述
     */
    private String content;

    /**
     * 配送省
     */
    @Column(name = "ship_province")
    private Integer shipProvince;

    /**
     * 配送市
     */
    @Column(name = "ship_city")
    private Integer shipCity;

    /**
     * 配送区
     */
    @Column(name = "sihp_distruct")
    private Integer sihpDistruct;

    /**
     * 配送地址
     */
    @Column(name = "ship_address")
    private String shipAddress;

    /**
     * 联系人
     */
    private String contcat;

    /**
     * 联系电话
     */
    private String mobile;

    /**
     * 退货凭证
     */
    private String pid;

    /**
     * 仲裁状态：0未仲裁1申请仲裁2仲裁成功-1仲裁失败
     */
    @Column(name = "arbitration_status")
    private Byte arbitrationStatus;

    /**
     * 同意退款时间（包括仲裁）
     */
    @Column(name = "agree_time")
    private Date agreeTime;

    @Column(name = "bank_id")
    private Byte bankId;

    @Column(name = "bank_account")
    private String bankAccount;

    /**
     * 获取服务编号（退货编号）
     *
     * @return id - 服务编号（退货编号）
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置服务编号（退货编号）
     *
     * @param id 服务编号（退货编号）
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取订单编号
     *
     * @return order_id - 订单编号
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 设置订单编号
     *
     * @param orderId 订单编号
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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
     * 获取审核时间
     *
     * @return review_time - 审核时间
     */
    public Date getReviewTime() {
        return reviewTime;
    }

    /**
     * 设置审核时间
     *
     * @param reviewTime 审核时间
     */
    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    /**
     * 获取退款状态：0待审核1审核通过（待退款）2退款成功-1退款失败（仲裁失败）-2审核失败-3取消退款
     *
     * @return status - 退款状态：0待审核1审核通过（待退款）2退款成功-1退款失败（仲裁失败）-2审核失败-3取消退款
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置退款状态：0待审核1审核通过（待退款）2退款成功-1退款失败（仲裁失败）-2审核失败-3取消退款
     *
     * @param status 退款状态：0待审核1审核通过（待退款）2退款成功-1退款失败（仲裁失败）-2审核失败-3取消退款
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取如果使用优惠券，返还给用户折扣抵现的金额
     *
     * @return refund_money_discount - 如果使用优惠券，返还给用户折扣抵现的金额
     */
    public BigDecimal getRefundMoneyDiscount() {
        return refundMoneyDiscount;
    }

    /**
     * 设置如果使用优惠券，返还给用户折扣抵现的金额
     *
     * @param refundMoneyDiscount 如果使用优惠券，返还给用户折扣抵现的金额
     */
    public void setRefundMoneyDiscount(BigDecimal refundMoneyDiscount) {
        this.refundMoneyDiscount = refundMoneyDiscount;
    }

    /**
     * 获取返回给用户金额
     *
     * @return refund_money_buyer - 返回给用户金额
     */
    public BigDecimal getRefundMoneyBuyer() {
        return refundMoneyBuyer;
    }

    /**
     * 设置返回给用户金额
     *
     * @param refundMoneyBuyer 返回给用户金额
     */
    public void setRefundMoneyBuyer(BigDecimal refundMoneyBuyer) {
        this.refundMoneyBuyer = refundMoneyBuyer;
    }

    /**
     * 获取返回给商家的金额
     *
     * @return refund_money_sp - 返回给商家的金额
     */
    public BigDecimal getRefundMoneySp() {
        return refundMoneySp;
    }

    /**
     * 设置返回给商家的金额
     *
     * @param refundMoneySp 返回给商家的金额
     */
    public void setRefundMoneySp(BigDecimal refundMoneySp) {
        this.refundMoneySp = refundMoneySp;
    }

    /**
     * 获取供货商id
     *
     * @return sp_id - 供货商id
     */
    public Integer getSpId() {
        return spId;
    }

    /**
     * 设置供货商id
     *
     * @param spId 供货商id
     */
    public void setSpId(Integer spId) {
        this.spId = spId;
    }

    /**
     * 获取取货方式0上门自取1用户自邮
     *
     * @return back_method - 取货方式0上门自取1用户自邮
     */
    public Byte getBackMethod() {
        return backMethod;
    }

    /**
     * 设置取货方式0上门自取1用户自邮
     *
     * @param backMethod 取货方式0上门自取1用户自邮
     */
    public void setBackMethod(Byte backMethod) {
        this.backMethod = backMethod;
    }

    /**
     * 获取退款方式0返回原卡（目前只有一种）
     *
     * @return refund_method - 退款方式0返回原卡（目前只有一种）
     */
    public Byte getRefundMethod() {
        return refundMethod;
    }

    /**
     * 设置退款方式0返回原卡（目前只有一种）
     *
     * @param refundMethod 退款方式0返回原卡（目前只有一种）
     */
    public void setRefundMethod(Byte refundMethod) {
        this.refundMethod = refundMethod;
    }

    /**
     * 获取退款描述
     *
     * @return content - 退款描述
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置退款描述
     *
     * @param content 退款描述
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取配送省
     *
     * @return ship_province - 配送省
     */
    public Integer getShipProvince() {
        return shipProvince;
    }

    /**
     * 设置配送省
     *
     * @param shipProvince 配送省
     */
    public void setShipProvince(Integer shipProvince) {
        this.shipProvince = shipProvince;
    }

    /**
     * 获取配送市
     *
     * @return ship_city - 配送市
     */
    public Integer getShipCity() {
        return shipCity;
    }

    /**
     * 设置配送市
     *
     * @param shipCity 配送市
     */
    public void setShipCity(Integer shipCity) {
        this.shipCity = shipCity;
    }

    /**
     * 获取配送区
     *
     * @return sihp_distruct - 配送区
     */
    public Integer getSihpDistruct() {
        return sihpDistruct;
    }

    /**
     * 设置配送区
     *
     * @param sihpDistruct 配送区
     */
    public void setSihpDistruct(Integer sihpDistruct) {
        this.sihpDistruct = sihpDistruct;
    }

    /**
     * 获取配送地址
     *
     * @return ship_address - 配送地址
     */
    public String getShipAddress() {
        return shipAddress;
    }

    /**
     * 设置配送地址
     *
     * @param shipAddress 配送地址
     */
    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    /**
     * 获取联系人
     *
     * @return contcat - 联系人
     */
    public String getContcat() {
        return contcat;
    }

    /**
     * 设置联系人
     *
     * @param contcat 联系人
     */
    public void setContcat(String contcat) {
        this.contcat = contcat;
    }

    /**
     * 获取联系电话
     *
     * @return mobile - 联系电话
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置联系电话
     *
     * @param mobile 联系电话
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取退货凭证
     *
     * @return pid - 退货凭证
     */
    public String getPid() {
        return pid;
    }

    /**
     * 设置退货凭证
     *
     * @param pid 退货凭证
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * 获取仲裁状态：0未仲裁1申请仲裁2仲裁成功-1仲裁失败
     *
     * @return arbitration_status - 仲裁状态：0未仲裁1申请仲裁2仲裁成功-1仲裁失败
     */
    public Byte getArbitrationStatus() {
        return arbitrationStatus;
    }

    /**
     * 设置仲裁状态：0未仲裁1申请仲裁2仲裁成功-1仲裁失败
     *
     * @param arbitrationStatus 仲裁状态：0未仲裁1申请仲裁2仲裁成功-1仲裁失败
     */
    public void setArbitrationStatus(Byte arbitrationStatus) {
        this.arbitrationStatus = arbitrationStatus;
    }

    /**
     * 获取同意退款时间（包括仲裁）
     *
     * @return agree_time - 同意退款时间（包括仲裁）
     */
    public Date getAgreeTime() {
        return agreeTime;
    }

    /**
     * 设置同意退款时间（包括仲裁）
     *
     * @param agreeTime 同意退款时间（包括仲裁）
     */
    public void setAgreeTime(Date agreeTime) {
        this.agreeTime = agreeTime;
    }

    /**
     * @return bank_id
     */
    public Byte getBankId() {
        return bankId;
    }

    /**
     * @param bankId
     */
    public void setBankId(Byte bankId) {
        this.bankId = bankId;
    }

    /**
     * @return bank_account
     */
    public String getBankAccount() {
        return bankAccount;
    }

    /**
     * @param bankAccount
     */
    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }
    
	
	public String getStatusHandle(){
		if(status==0){
			return "待审核";
		}
		if(status==1){
			return "审核通过(待退款)";
		}
		if(status==2){
			return "退款成功";
		}
		if(status==-1){
			return "退款失败";
		}
		if(status==-2){
			return "审核失败";
		}
		if(status==-3){
			return "取消退款";
		}		
		return "";
	}
	
	public String getCreateTimeHandle(){
		if(createTime!=null){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:dd");
			return sdf.format(createTime);
		}
		return "";
	}
}
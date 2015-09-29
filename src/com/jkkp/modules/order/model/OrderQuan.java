package com.jkkp.modules.order.model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.*;

@Table(name = "order_quan")
public class OrderQuan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 券id
     */
    @Column(name = "card_id")
    private Integer cardId;

    /**
     * 订单创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 使用时间
     */
    @Column(name = "use_time")
    private Date useTime;

    /**
     * 订单状态：0未付款1已付款，未使用2已使用-1取消订单-2券已过期
     */
    private Byte status;

    /**
     * 过期时间
     */
    @Column(name = "expire_date")
    private Date expireDate;

    /**
     * 券id
     */
    @Column(name = "quan_id")
    private Integer quanId;

    /**
     * 登录手机号
     */
    private String mobile;

    /**
     * 下订单所在的城市
     */
    @Column(name = "city_domain")
    private String cityDomain;

    /**
     * 券属性城市
     */
    @Column(name = "quan_city")
    private String quanCity;

    /**
     * 商家id
     */
    @Column(name = "sp_id")
    private Integer spId;

    /**
     * 券属性店铺id，默认不填
     */
    @Column(name = "quan_shop")
    private Integer quanShop;

    /**
     * 代金券商品分类
     */
    @Column(name = "quan_cate")
    private Integer quanCate;

    /**
     * 实付金额
     */
    @Column(name = "pay_sum_price")
    private BigDecimal paySumPrice;

    /**
     * 淘宝交易编号
     */
    @Column(name = "trade_no")
    private String tradeNo;

    /**
     * 支付时间
     */
    @Column(name = "pay_time")
    private Date payTime;

    /**
     * 支付方式1易宝支付2支付宝
     */
    @Column(name = "pay_type")
    private Byte payType;

    /**
     * 网银接口：gs|zs|zs|ny|jt|gf|rm|ms|zfb
     */
    @Column(name = "bank_gateway")
    private String bankGateway;

    /**
     * 0未退款1退款中2退款完成-1退款失败
     */
    @Column(name = "refund_status")
    private Byte refundStatus;

    /**
     * 退券时间
     */
    @Column(name = "refund_time")
    private Date refundTime;

    /**
     * 退款时间
     */
    @Column(name = "refund_money_time")
    private Date refundMoneyTime;

    /**
     * 退款银行账号
     */
    @Column(name = "refund_bank_account")
    private String refundBankAccount;

    /**
     * 退款银行1招商2建设3工商4邮政5农行6交通7中国银行8浦发9民生10中信11光大12华夏
     */
    @Column(name = "refund_bank_id")
    private Byte refundBankId;

    private Byte source;

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
     * 获取用户id
     *
     * @return uid - 用户id
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 设置用户id
     *
     * @param uid 用户id
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 获取券id
     *
     * @return card_id - 券id
     */
    public Integer getCardId() {
        return cardId;
    }

    /**
     * 设置券id
     *
     * @param cardId 券id
     */
    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    /**
     * 获取订单创建时间
     *
     * @return create_time - 订单创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置订单创建时间
     *
     * @param createTime 订单创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取使用时间
     *
     * @return use_time - 使用时间
     */
    public Date getUseTime() {
        return useTime;
    }

    /**
     * 设置使用时间
     *
     * @param useTime 使用时间
     */
    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }

    /**
     * 获取订单状态：0未付款1已付款，未使用2已使用-1取消订单-2券已过期
     *
     * @return status - 订单状态：0未付款1已付款，未使用2已使用-1取消订单-2券已过期
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置订单状态：0未付款1已付款，未使用2已使用-1取消订单-2券已过期
     *
     * @param status 订单状态：0未付款1已付款，未使用2已使用-1取消订单-2券已过期
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取过期时间
     *
     * @return expire_date - 过期时间
     */
    public Date getExpireDate() {
        return expireDate;
    }

    /**
     * 设置过期时间
     *
     * @param expireDate 过期时间
     */
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * 获取券id
     *
     * @return quan_id - 券id
     */
    public Integer getQuanId() {
        return quanId;
    }

    /**
     * 设置券id
     *
     * @param quanId 券id
     */
    public void setQuanId(Integer quanId) {
        this.quanId = quanId;
    }

    /**
     * 获取登录手机号
     *
     * @return mobile - 登录手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置登录手机号
     *
     * @param mobile 登录手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取下订单所在的城市
     *
     * @return city_domain - 下订单所在的城市
     */
    public String getCityDomain() {
        return cityDomain;
    }

    /**
     * 设置下订单所在的城市
     *
     * @param cityDomain 下订单所在的城市
     */
    public void setCityDomain(String cityDomain) {
        this.cityDomain = cityDomain;
    }

    /**
     * 获取券属性城市
     *
     * @return quan_city - 券属性城市
     */
    public String getQuanCity() {
        return quanCity;
    }

    /**
     * 设置券属性城市
     *
     * @param quanCity 券属性城市
     */
    public void setQuanCity(String quanCity) {
        this.quanCity = quanCity;
    }

    /**
     * 获取商家id
     *
     * @return sp_id - 商家id
     */
    public Integer getSpId() {
        return spId;
    }

    /**
     * 设置商家id
     *
     * @param spId 商家id
     */
    public void setSpId(Integer spId) {
        this.spId = spId;
    }

    /**
     * 获取券属性店铺id，默认不填
     *
     * @return quan_shop - 券属性店铺id，默认不填
     */
    public Integer getQuanShop() {
        return quanShop;
    }

    /**
     * 设置券属性店铺id，默认不填
     *
     * @param quanShop 券属性店铺id，默认不填
     */
    public void setQuanShop(Integer quanShop) {
        this.quanShop = quanShop;
    }

    /**
     * 获取代金券商品分类
     *
     * @return quan_cate - 代金券商品分类
     */
    public Integer getQuanCate() {
        return quanCate;
    }

    /**
     * 设置代金券商品分类
     *
     * @param quanCate 代金券商品分类
     */
    public void setQuanCate(Integer quanCate) {
        this.quanCate = quanCate;
    }

    /**
     * 获取实付金额
     *
     * @return pay_sum_price - 实付金额
     */
    public BigDecimal getPaySumPrice() {
        return paySumPrice;
    }

    /**
     * 设置实付金额
     *
     * @param paySumPrice 实付金额
     */
    public void setPaySumPrice(BigDecimal paySumPrice) {
        this.paySumPrice = paySumPrice;
    }

    /**
     * 获取淘宝交易编号
     *
     * @return trade_no - 淘宝交易编号
     */
    public String getTradeNo() {
        return tradeNo;
    }

    /**
     * 设置淘宝交易编号
     *
     * @param tradeNo 淘宝交易编号
     */
    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    /**
     * 获取支付时间
     *
     * @return pay_time - 支付时间
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
     * 设置支付时间
     *
     * @param payTime 支付时间
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * 获取支付方式1易宝支付2支付宝
     *
     * @return pay_type - 支付方式1易宝支付2支付宝
     */
    public Byte getPayType() {
        return payType;
    }

    /**
     * 设置支付方式1易宝支付2支付宝
     *
     * @param payType 支付方式1易宝支付2支付宝
     */
    public void setPayType(Byte payType) {
        this.payType = payType;
    }

    /**
     * 获取网银接口：gs|zs|zs|ny|jt|gf|rm|ms|zfb
     *
     * @return bank_gateway - 网银接口：gs|zs|zs|ny|jt|gf|rm|ms|zfb
     */
    public String getBankGateway() {
        return bankGateway;
    }

    /**
     * 设置网银接口：gs|zs|zs|ny|jt|gf|rm|ms|zfb
     *
     * @param bankGateway 网银接口：gs|zs|zs|ny|jt|gf|rm|ms|zfb
     */
    public void setBankGateway(String bankGateway) {
        this.bankGateway = bankGateway;
    }

    /**
     * 获取0未退款1退款中2退款完成-1退款失败
     *
     * @return refund_status - 0未退款1退款中2退款完成-1退款失败
     */
    public Byte getRefundStatus() {
        return refundStatus;
    }

    /**
     * 设置0未退款1退款中2退款完成-1退款失败
     *
     * @param refundStatus 0未退款1退款中2退款完成-1退款失败
     */
    public void setRefundStatus(Byte refundStatus) {
        this.refundStatus = refundStatus;
    }

    /**
     * 获取退券时间
     *
     * @return refund_time - 退券时间
     */
    public Date getRefundTime() {
        return refundTime;
    }

    /**
     * 设置退券时间
     *
     * @param refundTime 退券时间
     */
    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    /**
     * 获取退款时间
     *
     * @return refund_money_time - 退款时间
     */
    public Date getRefundMoneyTime() {
        return refundMoneyTime;
    }

    /**
     * 设置退款时间
     *
     * @param refundMoneyTime 退款时间
     */
    public void setRefundMoneyTime(Date refundMoneyTime) {
        this.refundMoneyTime = refundMoneyTime;
    }

    /**
     * 获取退款银行账号
     *
     * @return refund_bank_account - 退款银行账号
     */
    public String getRefundBankAccount() {
        return refundBankAccount;
    }

    /**
     * 设置退款银行账号
     *
     * @param refundBankAccount 退款银行账号
     */
    public void setRefundBankAccount(String refundBankAccount) {
        this.refundBankAccount = refundBankAccount;
    }

    /**
     * 获取退款银行1招商2建设3工商4邮政5农行6交通7中国银行8浦发9民生10中信11光大12华夏
     *
     * @return refund_bank_id - 退款银行1招商2建设3工商4邮政5农行6交通7中国银行8浦发9民生10中信11光大12华夏
     */
    public Byte getRefundBankId() {
        return refundBankId;
    }

    /**
     * 设置退款银行1招商2建设3工商4邮政5农行6交通7中国银行8浦发9民生10中信11光大12华夏
     *
     * @param refundBankId 退款银行1招商2建设3工商4邮政5农行6交通7中国银行8浦发9民生10中信11光大12华夏
     */
    public void setRefundBankId(Byte refundBankId) {
        this.refundBankId = refundBankId;
    }

    /**
     * @return source
     */
    public Byte getSource() {
        return source;
    }

    /**
     * @param source
     */
    public void setSource(Byte source) {
        this.source = source;
    }
    
    public String getExpireDateHandle(){
		if(expireDate!=null){
			 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			 return sdf.format(this.expireDate);
		 }
		return "";
	}
	
	public String getRefundTimeHandle(){
		if(refundTime!=null){
			 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			 return sdf.format(this.refundTime);
		 }
		return "";
	}
	
	public String getRefundMoneyTimeHandle(){
		if(refundMoneyTime!=null){
			 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			 return sdf.format(this.refundMoneyTime);
		 }
		return "";
	}
	
	
	public String getCreateTimeHandle(){
		if(createTime!=null){
			 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			 return sdf.format(this.createTime);
		 }
		return "";
	}
	
	
	public String getUseTimeHandle(){
		if(useTime!=null){
			 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			 return sdf.format(this.useTime);
		 }
		return "";
	}
	
	public String getStatusValHandle(){
		if(status==0){
			return "未付款";
		}else if(status==1){
			return "已付款,未使用";
		}else if(status==2){
			return "已使用";
		}else if(status==-1){
			return "取消订单";
		}else if(status==2){
			return "已过期";
		}
		return "";
	}
}
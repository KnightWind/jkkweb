package com.jkkp.modules.order.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

import com.jkkp.utils.DateUtil;

@Table(name = "order_ship")
public class OrderShip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 商户id
     */
    @Column(name = "sp_id")
    private Integer spId;

    /**
     * 收货人姓名
     */
    @Column(name = "contact_user")
    private String contactUser;

    /**
     * 收货人电话
     */
    @Column(name = "contact_mobile")
    private String contactMobile;

    /**
     * 收货地址
     */
    private String address;

    /**
     * 商品总额
     */
    @Column(name = "item_sum_price")
    private BigDecimal itemSumPrice;

    /**
     * 下单时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 下单状态：0待付款1待发货2已发货3已收货4已完成-1已取消
     */
    private Byte status;

    /**
     * 会员id
     */
    private Integer uid;

    /**
     * 支付方式：1支付宝2易宝支付
     */
    @Column(name = "pay_type")
    private Byte payType;

    /**
     * 发票抬头类型：1个人2单位
     */
    @Column(name = "receipt_title_type")
    private Byte receiptTitleType;
    public String getFa(){
    	if(this.receiptTitleType!=null){
    		if(this.receiptTitleType==1){
    			return "个人";
    		}else {
				return "单位";
			}
    	}
    	return "";
    }
    /**
     * 发票抬头
     */
    @Column(name = "receipt_title")
    private String receiptTitle;

    /**
     * 实付总额
     */
    @Column(name = "pay_sum_price")
    private BigDecimal paySumPrice;

    /**
     * 发票类型1日常用品2办公用品3明细
     */
    @Column(name = "receipt_type")
    private Byte receiptType;
    public String getMing(){
    	if(this.receiptType!=null){
    		if(this.receiptType==1){
    			return "日常用品";
    		}else if (this.receiptType==2) {
				return "办公用品";
			}else {
				return "明细";
			}
    	}
    	return "";
    }
    /**
     * 发货时间
     */
    @Column(name = "ship_time")
    private Date shipTime;
    public String getShi(){
     	 return this.shipTime==null?"":DateUtil.formatDate(shipTime);
     }
    /**
     * 快递公司id
     */
    @Column(name = "express_id")
    private Integer expressId;

    /**
     * 快递单号
     */
    private String tracking;

    /**
     * 购买所在地区
     */
    @Column(name = "city_domain")
    private String cityDomain;

    /**
     * 配送所在省
     */
    @Column(name = "ship_province")
    private Integer shipProvince;

    /**
     * 配送所在市
     */
    @Column(name = "ship_city")
    private Integer shipCity;

    /**
     * 配送所在区
     */
    @Column(name = "ship_district")
    private Integer shipDistrict;

    /**
     * 主订单id
     */
    @Column(name = "main_id")
    private Integer mainId;

    /**
     * 付款时间
     */
    @Column(name = "pay_time")
    private Date payTime;

    /**
     * 网银接口：gs|zs|zs|ny|jt|gf|rm|ms|zfb
     */
    @Column(name = "bank_gateway")
    private String bankGateway;

    /**
     * 交易编号，淘宝接口返回
     */
    @Column(name = "trade_no")
    private String tradeNo;

    /**
     * 抵扣金额（如果使用优惠券）
     */
    private BigDecimal discount;

    /**
     * 收货时间
     */
    @Column(name = "receiving_time")
    private Date receivingTime;

    /**
     * 完成时间
     */
    @Column(name = "finish_time")
    private Date finishTime;

    /**
     * 0未退款1退款中2退款完成-1退款失败
     */
    @Column(name = "refund_status")
    private Byte refundStatus;

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
     * 获取商户id
     *
     * @return sp_id - 商户id
     */
    public Integer getSpId() {
        return spId;
    }

    /**
     * 设置商户id
     *
     * @param spId 商户id
     */
    public void setSpId(Integer spId) {
        this.spId = spId;
    }

    /**
     * 获取收货人姓名
     *
     * @return contact_user - 收货人姓名
     */
    public String getContactUser() {
        return contactUser;
    }

    /**
     * 设置收货人姓名
     *
     * @param contactUser 收货人姓名
     */
    public void setContactUser(String contactUser) {
        this.contactUser = contactUser;
    }

    /**
     * 获取收货人电话
     *
     * @return contact_mobile - 收货人电话
     */
    public String getContactMobile() {
        return contactMobile;
    }

    /**
     * 设置收货人电话
     *
     * @param contactMobile 收货人电话
     */
    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    /**
     * 获取收货地址
     *
     * @return address - 收货地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置收货地址
     *
     * @param address 收货地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取商品总额
     *
     * @return item_sum_price - 商品总额
     */
    public BigDecimal getItemSumPrice() {
        return itemSumPrice;
    }

    /**
     * 设置商品总额
     *
     * @param itemSumPrice 商品总额
     */
    public void setItemSumPrice(BigDecimal itemSumPrice) {
        this.itemSumPrice = itemSumPrice;
    }

    /**
     * 获取下单时间
     *
     * @return create_time - 下单时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置下单时间
     *
     * @param createTime 下单时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取下单状态：0待付款1待发货2已发货3已收货4已完成-1已取消
     *
     * @return status - 下单状态：0待付款1待发货2已发货3已收货4已完成-1已取消
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置下单状态：0待付款1待发货2已发货3已收货4已完成-1已取消
     *
     * @param status 下单状态：0待付款1待发货2已发货3已收货4已完成-1已取消
     */
    public void setStatus(Byte status) {
        this.status = status;
    }
    public String getZt(){
    	if(this.status!=null){
    		if(this.status==0){
    			return "待付款";
    		}else if (this.status==1) {
    			return "待发货";
			}else if (this.status==2) {
    			return "已发货";
			}else if (this.status==3) {
    			return "已收货";
			}else if (this.status==3) {
    			return "已完成";
			}else {
				return "取消";
			}
    	}
    	return "";
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
     * 获取支付方式：1支付宝2易宝支付
     *
     * @return pay_type - 支付方式：1支付宝2易宝支付
     */
    public Byte getPayType() {
        return payType;
    }

    /**
     * 设置支付方式：1支付宝2易宝支付
     *
     * @param payType 支付方式：1支付宝2易宝支付
     */
    public void setPayType(Byte payType) {
        this.payType = payType;
    }

    /**
     * 获取发票抬头类型：1个人2单位
     *
     * @return receipt_title_type - 发票抬头类型：1个人2单位
     */
    public Byte getReceiptTitleType() {
        return receiptTitleType;
    }

    /**
     * 设置发票抬头类型：1个人2单位
     *
     * @param receiptTitleType 发票抬头类型：1个人2单位
     */
    public void setReceiptTitleType(Byte receiptTitleType) {
        this.receiptTitleType = receiptTitleType;
    }

    /**
     * 获取发票抬头
     *
     * @return receipt_title - 发票抬头
     */
    public String getReceiptTitle() {
        return receiptTitle;
    }

    /**
     * 设置发票抬头
     *
     * @param receiptTitle 发票抬头
     */
    public void setReceiptTitle(String receiptTitle) {
        this.receiptTitle = receiptTitle;
    }

    /**
     * 获取实付总额
     *
     * @return pay_sum_price - 实付总额
     */
    public BigDecimal getPaySumPrice() {
        return paySumPrice;
    }

    /**
     * 设置实付总额
     *
     * @param paySumPrice 实付总额
     */
    public void setPaySumPrice(BigDecimal paySumPrice) {
        this.paySumPrice = paySumPrice;
    }

    /**
     * 获取发票类型1日常用品2办公用品3明细
     *
     * @return receipt_type - 发票类型1日常用品2办公用品3明细
     */
    public Byte getReceiptType() {
        return receiptType;
    }

    /**
     * 设置发票类型1日常用品2办公用品3明细
     *
     * @param receiptType 发票类型1日常用品2办公用品3明细
     */
    public void setReceiptType(Byte receiptType) {
        this.receiptType = receiptType;
    }

    /**
     * 获取发货时间
     *
     * @return ship_time - 发货时间
     */
    public Date getShipTime() {
        return shipTime;
    }

    /**
     * 设置发货时间
     *
     * @param shipTime 发货时间
     */
    public void setShipTime(Date shipTime) {
        this.shipTime = shipTime;
    }

    /**
     * 获取快递公司id
     *
     * @return express_id - 快递公司id
     */
    public Integer getExpressId() {
        return expressId;
    }

    /**
     * 设置快递公司id
     *
     * @param expressId 快递公司id
     */
    public void setExpressId(Integer expressId) {
        this.expressId = expressId;
    }

    /**
     * 获取快递单号
     *
     * @return tracking - 快递单号
     */
    public String getTracking() {
        return tracking;
    }

    /**
     * 设置快递单号
     *
     * @param tracking 快递单号
     */
    public void setTracking(String tracking) {
        this.tracking = tracking;
    }

    /**
     * 获取购买所在地区
     *
     * @return city_domain - 购买所在地区
     */
    public String getCityDomain() {
        return cityDomain;
    }

    /**
     * 设置购买所在地区
     *
     * @param cityDomain 购买所在地区
     */
    public void setCityDomain(String cityDomain) {
        this.cityDomain = cityDomain;
    }

    /**
     * 获取配送所在省
     *
     * @return ship_province - 配送所在省
     */
    public Integer getShipProvince() {
        return shipProvince;
    }

    /**
     * 设置配送所在省
     *
     * @param shipProvince 配送所在省
     */
    public void setShipProvince(Integer shipProvince) {
        this.shipProvince = shipProvince;
    }

    /**
     * 获取配送所在市
     *
     * @return ship_city - 配送所在市
     */
    public Integer getShipCity() {
        return shipCity;
    }

    /**
     * 设置配送所在市
     *
     * @param shipCity 配送所在市
     */
    public void setShipCity(Integer shipCity) {
        this.shipCity = shipCity;
    }

    /**
     * 获取配送所在区
     *
     * @return ship_district - 配送所在区
     */
    public Integer getShipDistrict() {
        return shipDistrict;
    }

    /**
     * 设置配送所在区
     *
     * @param shipDistrict 配送所在区
     */
    public void setShipDistrict(Integer shipDistrict) {
        this.shipDistrict = shipDistrict;
    }

    /**
     * 获取主订单id
     *
     * @return main_id - 主订单id
     */
    public Integer getMainId() {
        return mainId;
    }

    /**
     * 设置主订单id
     *
     * @param mainId 主订单id
     */
    public void setMainId(Integer mainId) {
        this.mainId = mainId;
    }

    /**
     * 获取付款时间
     *
     * @return pay_time - 付款时间
     */
    public Date getPayTime() {
        return payTime;
    }
    public String getDg(){
   	 return this.payTime==null?"":DateUtil.formatDate(payTime);
   }
    public String getCre(){
      	 return this.createTime==null?"":DateUtil.formatDate(createTime);
      }
    /**
     * 设置付款时间
     *
     * @param payTime 付款时间
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
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
     * 获取交易编号，淘宝接口返回
     *
     * @return trade_no - 交易编号，淘宝接口返回
     */
    public String getTradeNo() {
        return tradeNo;
    }

    /**
     * 设置交易编号，淘宝接口返回
     *
     * @param tradeNo 交易编号，淘宝接口返回
     */
    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    /**
     * 获取抵扣金额（如果使用优惠券）
     *
     * @return discount - 抵扣金额（如果使用优惠券）
     */
    public BigDecimal getDiscount() {
        return discount;
    }

    /**
     * 设置抵扣金额（如果使用优惠券）
     *
     * @param discount 抵扣金额（如果使用优惠券）
     */
    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    /**
     * 获取收货时间
     *
     * @return receiving_time - 收货时间
     */
    public Date getReceivingTime() {
        return receivingTime;
    }

    /**
     * 设置收货时间
     *
     * @param receivingTime 收货时间
     */
    public void setReceivingTime(Date receivingTime) {
        this.receivingTime = receivingTime;
    }

    /**
     * 获取完成时间
     *
     * @return finish_time - 完成时间
     */
    public Date getFinishTime() {
        return finishTime;
    }

    /**
     * 设置完成时间
     *
     * @param finishTime 完成时间
     */
    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
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
}
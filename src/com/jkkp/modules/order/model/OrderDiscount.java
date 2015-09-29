package com.jkkp.modules.order.model;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "order_discount")
public class OrderDiscount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 主订单id
     */
    @Column(name = "main_id")
    private Integer mainId;

    /**
     * 子订单id
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 1全品券2店铺券
     */
    @Column(name = "discount_type")
    private Byte discountType;

    /**
     * 优惠金额（优惠产生的满减金额）
     */
    @Column(name = "discount_price")
    private BigDecimal discountPrice;

    /**
     * 券id
     */
    @Column(name = "quan_id")
    private Integer quanId;

    /**
     * 优惠券id
     */
    @Column(name = "card_id")
    private Integer cardId;

    /**
     * 券的订单id
     */
    @Column(name = "quan_order_id")
    private Integer quanOrderId;

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
     * 获取子订单id
     *
     * @return order_id - 子订单id
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 设置子订单id
     *
     * @param orderId 子订单id
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取1全品券2店铺券
     *
     * @return discount_type - 1全品券2店铺券
     */
    public Byte getDiscountType() {
        return discountType;
    }

    /**
     * 设置1全品券2店铺券
     *
     * @param discountType 1全品券2店铺券
     */
    public void setDiscountType(Byte discountType) {
        this.discountType = discountType;
    }

    /**
     * 获取优惠金额（优惠产生的满减金额）
     *
     * @return discount_price - 优惠金额（优惠产生的满减金额）
     */
    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    /**
     * 设置优惠金额（优惠产生的满减金额）
     *
     * @param discountPrice 优惠金额（优惠产生的满减金额）
     */
    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
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
     * 获取优惠券id
     *
     * @return card_id - 优惠券id
     */
    public Integer getCardId() {
        return cardId;
    }

    /**
     * 设置优惠券id
     *
     * @param cardId 优惠券id
     */
    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    /**
     * 获取券的订单id
     *
     * @return quan_order_id - 券的订单id
     */
    public Integer getQuanOrderId() {
        return quanOrderId;
    }

    /**
     * 设置券的订单id
     *
     * @param quanOrderId 券的订单id
     */
    public void setQuanOrderId(Integer quanOrderId) {
        this.quanOrderId = quanOrderId;
    }
}
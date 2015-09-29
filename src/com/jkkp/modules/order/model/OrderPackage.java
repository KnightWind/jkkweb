package com.jkkp.modules.order.model;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "order_package")
public class OrderPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 商品id
     */
    @Column(name = "package_id")
    private Integer packageId;

    /**
     * 套餐单价
     */
    @Column(name = "package_price")
    private BigDecimal packagePrice;

    /**
     * 延米价格
     */
    @Column(name = "yanmi_price")
    private BigDecimal yanmiPrice;

    /**
     * 购买延米数
     */
    private Integer yanmi;

    /**
     * 是否退货0否1是
     */
    @Column(name = "is_return")
    private Byte isReturn;

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
     * 获取订单id
     *
     * @return order_id - 订单id
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 设置订单id
     *
     * @param orderId 订单id
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取商品id
     *
     * @return package_id - 商品id
     */
    public Integer getPackageId() {
        return packageId;
    }

    /**
     * 设置商品id
     *
     * @param packageId 商品id
     */
    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    /**
     * 获取套餐单价
     *
     * @return package_price - 套餐单价
     */
    public BigDecimal getPackagePrice() {
        return packagePrice;
    }

    /**
     * 设置套餐单价
     *
     * @param packagePrice 套餐单价
     */
    public void setPackagePrice(BigDecimal packagePrice) {
        this.packagePrice = packagePrice;
    }

    /**
     * 获取延米价格
     *
     * @return yanmi_price - 延米价格
     */
    public BigDecimal getYanmiPrice() {
        return yanmiPrice;
    }

    /**
     * 设置延米价格
     *
     * @param yanmiPrice 延米价格
     */
    public void setYanmiPrice(BigDecimal yanmiPrice) {
        this.yanmiPrice = yanmiPrice;
    }

    /**
     * 获取购买延米数
     *
     * @return yanmi - 购买延米数
     */
    public Integer getYanmi() {
        return yanmi;
    }

    /**
     * 设置购买延米数
     *
     * @param yanmi 购买延米数
     */
    public void setYanmi(Integer yanmi) {
        this.yanmi = yanmi;
    }

    /**
     * 获取是否退货0否1是
     *
     * @return is_return - 是否退货0否1是
     */
    public Byte getIsReturn() {
        return isReturn;
    }

    /**
     * 设置是否退货0否1是
     *
     * @param isReturn 是否退货0否1是
     */
    public void setIsReturn(Byte isReturn) {
        this.isReturn = isReturn;
    }
}
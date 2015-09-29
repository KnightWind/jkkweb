package com.jkkp.modules.order.model;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "order_package_item")
public class OrderPackageItem {
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
     * 商品id
     */
    @Column(name = "item_id")
    private Integer itemId;

    /**
     * 商品价格
     */
    @Column(name = "item_price")
    private BigDecimal itemPrice;

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
     * 获取商品id
     *
     * @return item_id - 商品id
     */
    public Integer getItemId() {
        return itemId;
    }

    /**
     * 设置商品id
     *
     * @param itemId 商品id
     */
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    /**
     * 获取商品价格
     *
     * @return item_price - 商品价格
     */
    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    /**
     * 设置商品价格
     *
     * @param itemPrice 商品价格
     */
    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }
}
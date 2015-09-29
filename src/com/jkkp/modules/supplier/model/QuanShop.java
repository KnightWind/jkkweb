package com.jkkp.modules.supplier.model;

import javax.persistence.*;

@Table(name = "quan_shop")
public class QuanShop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 券id
     */
    @Column(name = "quan_id")
    private Integer quanId;

    /**
     * 店铺id
     */
    @Column(name = "shop_id")
    private Integer shopId;

    /**
     * 发行数量
     */
    private Integer num;

    /**
     * 店铺所在城市
     */
    @Column(name = "city_domain")
    private String cityDomain;

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
     * 获取店铺id
     *
     * @return shop_id - 店铺id
     */
    public Integer getShopId() {
        return shopId;
    }

    /**
     * 设置店铺id
     *
     * @param shopId 店铺id
     */
    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    /**
     * 获取发行数量
     *
     * @return num - 发行数量
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 设置发行数量
     *
     * @param num 发行数量
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 获取店铺所在城市
     *
     * @return city_domain - 店铺所在城市
     */
    public String getCityDomain() {
        return cityDomain;
    }

    /**
     * 设置店铺所在城市
     *
     * @param cityDomain 店铺所在城市
     */
    public void setCityDomain(String cityDomain) {
        this.cityDomain = cityDomain;
    }
}
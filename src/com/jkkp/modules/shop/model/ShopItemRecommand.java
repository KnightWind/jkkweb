package com.jkkp.modules.shop.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "shop_item_recommand")
public class ShopItemRecommand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 商品id
     */
    @Column(name = "item_id")
    private Integer itemId;

    /**
     * 城市所属域名
     */
    @Column(name = "city_domain")
    private String cityDomain;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 显示状态：0显示-1隐藏
     */
    private Byte status;

    /**
     * 店铺id
     */
    @Column(name = "shop_id")
    private Integer shopId;

    /**
     * 排列序号
     */
    private Integer seq;

    /**
     * 商品显示位置：top|bottom
     */
    private String label;

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
     * 获取城市所属域名
     *
     * @return city_domain - 城市所属域名
     */
    public String getCityDomain() {
        return cityDomain;
    }

    /**
     * 设置城市所属域名
     *
     * @param cityDomain 城市所属域名
     */
    public void setCityDomain(String cityDomain) {
        this.cityDomain = cityDomain;
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
     * 获取显示状态：0显示-1隐藏
     *
     * @return status - 显示状态：0显示-1隐藏
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置显示状态：0显示-1隐藏
     *
     * @param status 显示状态：0显示-1隐藏
     */
    public void setStatus(Byte status) {
        this.status = status;
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
     * 获取排列序号
     *
     * @return seq - 排列序号
     */
    public Integer getSeq() {
        return seq;
    }

    /**
     * 设置排列序号
     *
     * @param seq 排列序号
     */
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    /**
     * 获取商品显示位置：top|bottom
     *
     * @return label - 商品显示位置：top|bottom
     */
    public String getLabel() {
        return label;
    }

    /**
     * 设置商品显示位置：top|bottom
     *
     * @param label 商品显示位置：top|bottom
     */
    public void setLabel(String label) {
        this.label = label;
    }
}
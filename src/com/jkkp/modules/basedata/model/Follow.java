package com.jkkp.modules.basedata.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "follow")
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 会员id
     */
    private Integer uid;

    /**
     * 商品id
     */
    @Column(name = "item_id")
    private Integer itemId;

    /**
     * 店铺id
     */
    @Column(name = "shop_id")
    private Integer shopId;

    /**
     * 券id
     */
    @Column(name = "quan_id")
    private Integer quanId;

    /**
     * 套装id
     */
    @Column(name = "package_id")
    private Integer packageId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

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
     * 获取套装id
     *
     * @return package_id - 套装id
     */
    public Integer getPackageId() {
        return packageId;
    }

    /**
     * 设置套装id
     *
     * @param packageId 套装id
     */
    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
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
}
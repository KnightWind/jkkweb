package com.jkkp.modules.supplier.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "quan_card")
public class QuanCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 发行券id
     */
    @Column(name = "quan_id")
    private Integer quanId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 状态：0未使用1已使用-1取消（退款）-2注销
     */
    private Byte status;

    /**
     * 店铺id
     */
    @Column(name = "shop_id")
    private Integer shopId;

    /**
     * 发行城市
     */
    @Column(name = "city_domain")
    private String cityDomain;

    /**
     * 是否被下单0否1是
     */
    @Column(name = "is_lock")
    private Byte isLock;

    /**
     * 商家id
     */
    @Column(name = "sp_id")
    private Integer spId;

    /**
     * 是否上架0是，-1否
     */
    @Column(name = "is_public")
    private Byte isPublic;

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
     * 获取发行券id
     *
     * @return quan_id - 发行券id
     */
    public Integer getQuanId() {
        return quanId;
    }

    /**
     * 设置发行券id
     *
     * @param quanId 发行券id
     */
    public void setQuanId(Integer quanId) {
        this.quanId = quanId;
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
     * 获取状态：0未使用1已使用-1取消（退款）-2注销
     *
     * @return status - 状态：0未使用1已使用-1取消（退款）-2注销
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置状态：0未使用1已使用-1取消（退款）-2注销
     *
     * @param status 状态：0未使用1已使用-1取消（退款）-2注销
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
     * 获取发行城市
     *
     * @return city_domain - 发行城市
     */
    public String getCityDomain() {
        return cityDomain;
    }

    /**
     * 设置发行城市
     *
     * @param cityDomain 发行城市
     */
    public void setCityDomain(String cityDomain) {
        this.cityDomain = cityDomain;
    }

    /**
     * 获取是否被下单0否1是
     *
     * @return is_lock - 是否被下单0否1是
     */
    public Byte getIsLock() {
        return isLock;
    }

    /**
     * 设置是否被下单0否1是
     *
     * @param isLock 是否被下单0否1是
     */
    public void setIsLock(Byte isLock) {
        this.isLock = isLock;
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
     * 获取是否上架0是，-1否
     *
     * @return is_public - 是否上架0是，-1否
     */
    public Byte getIsPublic() {
        return isPublic;
    }

    /**
     * 设置是否上架0是，-1否
     *
     * @param isPublic 是否上架0是，-1否
     */
    public void setIsPublic(Byte isPublic) {
        this.isPublic = isPublic;
    }
}
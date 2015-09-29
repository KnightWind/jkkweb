package com.jkkp.modules.supplier.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "quan_recommand")
public class QuanRecommand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 券id
     */
    @Column(name = "quan_id")
    private Integer quanId;

    /**
     * 放置位置，index:首页，list:列表页
     */
    private String label;

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

    private String pid;

    @Column(name = "sp_id")
    private Integer spId;

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
     * 获取放置位置，index:首页，list:列表页
     *
     * @return label - 放置位置，index:首页，list:列表页
     */
    public String getLabel() {
        return label;
    }

    /**
     * 设置放置位置，index:首页，list:列表页
     *
     * @param label 放置位置，index:首页，list:列表页
     */
    public void setLabel(String label) {
        this.label = label;
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
     * @return pid
     */
    public String getPid() {
        return pid;
    }

    /**
     * @param pid
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * @return sp_id
     */
    public Integer getSpId() {
        return spId;
    }

    /**
     * @param spId
     */
    public void setSpId(Integer spId) {
        this.spId = spId;
    }
}
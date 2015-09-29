package com.jkkp.modules.shop.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "shop_recommand")
public class ShopRecommand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 所在域名
     */
    @Column(name = "city_domain")
    private String cityDomain;

    /**
     * 商家id
     */
    @Column(name = "sp_id")
    private Integer spId;

    /**
     * 显示序号1-8
     */
    private Integer seq;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 首页图片
     */
    private String pid;

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
     * 获取所在域名
     *
     * @return city_domain - 所在域名
     */
    public String getCityDomain() {
        return cityDomain;
    }

    /**
     * 设置所在域名
     *
     * @param cityDomain 所在域名
     */
    public void setCityDomain(String cityDomain) {
        this.cityDomain = cityDomain;
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
     * 获取显示序号1-8
     *
     * @return seq - 显示序号1-8
     */
    public Integer getSeq() {
        return seq;
    }

    /**
     * 设置显示序号1-8
     *
     * @param seq 显示序号1-8
     */
    public void setSeq(Integer seq) {
        this.seq = seq;
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
     * 获取首页图片
     *
     * @return pid - 首页图片
     */
    public String getPid() {
        return pid;
    }

    /**
     * 设置首页图片
     *
     * @param pid 首页图片
     */
    public void setPid(String pid) {
        this.pid = pid;
    }
}
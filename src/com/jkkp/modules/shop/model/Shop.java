package com.jkkp.modules.shop.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "shop")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 横幅图片
     */
    @Column(name = "top_banner")
    private String topBanner;

    /**
     * logo图片id
     */
    @Column(name = "logo_pid")
    private String logoPid;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 商家id
     */
    @Column(name = "sp_id")
    private Integer spId;

    /**
     * 模板id
     */
    @Column(name = "template_id")
    private Integer templateId;

    /**
     * 域名绑定时间
     */
    @Column(name = "bind_time")
    private Date bindTime;

    /**
     * 下线时间
     */
    @Column(name = "off_time")
    private Date offTime;

    /**
     * 店铺状态0开启-1关闭
     */
    private Byte status;

    /**
     * 所在城市地址
     */
    @Column(name = "city_domain")
    private String cityDomain;

    /**
     * 品牌id
     */
    @Column(name = "brand_id")
    private Integer brandId;

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
     * 获取横幅图片
     *
     * @return top_banner - 横幅图片
     */
    public String getTopBanner() {
        return topBanner;
    }

    /**
     * 设置横幅图片
     *
     * @param topBanner 横幅图片
     */
    public void setTopBanner(String topBanner) {
        this.topBanner = topBanner;
    }

    /**
     * 获取logo图片id
     *
     * @return logo_pid - logo图片id
     */
    public String getLogoPid() {
        return logoPid;
    }

    /**
     * 设置logo图片id
     *
     * @param logoPid logo图片id
     */
    public void setLogoPid(String logoPid) {
        this.logoPid = logoPid;
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
     * 获取模板id
     *
     * @return template_id - 模板id
     */
    public Integer getTemplateId() {
        return templateId;
    }

    /**
     * 设置模板id
     *
     * @param templateId 模板id
     */
    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    /**
     * 获取域名绑定时间
     *
     * @return bind_time - 域名绑定时间
     */
    public Date getBindTime() {
        return bindTime;
    }

    /**
     * 设置域名绑定时间
     *
     * @param bindTime 域名绑定时间
     */
    public void setBindTime(Date bindTime) {
        this.bindTime = bindTime;
    }

    /**
     * 获取下线时间
     *
     * @return off_time - 下线时间
     */
    public Date getOffTime() {
        return offTime;
    }

    /**
     * 设置下线时间
     *
     * @param offTime 下线时间
     */
    public void setOffTime(Date offTime) {
        this.offTime = offTime;
    }

    /**
     * 获取店铺状态0开启-1关闭
     *
     * @return status - 店铺状态0开启-1关闭
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置店铺状态0开启-1关闭
     *
     * @param status 店铺状态0开启-1关闭
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取所在城市地址
     *
     * @return city_domain - 所在城市地址
     */
    public String getCityDomain() {
        return cityDomain;
    }

    /**
     * 设置所在城市地址
     *
     * @param cityDomain 所在城市地址
     */
    public void setCityDomain(String cityDomain) {
        this.cityDomain = cityDomain;
    }

    /**
     * 获取品牌id
     *
     * @return brand_id - 品牌id
     */
    public Integer getBrandId() {
        return brandId;
    }

    /**
     * 设置品牌id
     *
     * @param brandId 品牌id
     */
    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }
}
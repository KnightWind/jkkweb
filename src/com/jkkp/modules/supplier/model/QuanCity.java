package com.jkkp.modules.supplier.model;

import javax.persistence.*;

@Table(name = "quan_city")
public class QuanCity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 券id
     */
    @Column(name = "quan_id")
    private Integer quanId;

    /**
     * 城市所在域名
     */
    @Column(name = "city_domain")
    private String cityDomain;

    /**
     * 发行数量
     */
    private Integer num;

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
     * 获取城市所在域名
     *
     * @return city_domain - 城市所在域名
     */
    public String getCityDomain() {
        return cityDomain;
    }

    /**
     * 设置城市所在域名
     *
     * @param cityDomain 城市所在域名
     */
    public void setCityDomain(String cityDomain) {
        this.cityDomain = cityDomain;
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
}
package com.jkkp.modules.supplier.model;

import javax.persistence.*;

@Table(name = "quan_o2o")
public class QuanO2o {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 券id
     */
    @Column(name = "quan_id")
    private Integer quanId;

    /**
     * 店铺所属城市
     */
    @Column(name = "city_domain")
    private String cityDomain;

    /**
     * 店铺id
     */
    @Column(name = "sp_id")
    private Integer spId;

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
     * 获取店铺所属城市
     *
     * @return city_domain - 店铺所属城市
     */
    public String getCityDomain() {
        return cityDomain;
    }

    /**
     * 设置店铺所属城市
     *
     * @param cityDomain 店铺所属城市
     */
    public void setCityDomain(String cityDomain) {
        this.cityDomain = cityDomain;
    }

    /**
     * 获取店铺id
     *
     * @return sp_id - 店铺id
     */
    public Integer getSpId() {
        return spId;
    }

    /**
     * 设置店铺id
     *
     * @param spId 店铺id
     */
    public void setSpId(Integer spId) {
        this.spId = spId;
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
package com.jkkp.modules.basedata.model;

import javax.persistence.*;

@Table(name = "ad_search_tag")
public class AdSearchTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 关键词
     */
    private String tag;

    /**
     * 广告id
     */
    @Column(name = "ad_id")
    private Integer adId;

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
     * 获取关键词
     *
     * @return tag - 关键词
     */
    public String getTag() {
        return tag;
    }

    /**
     * 设置关键词
     *
     * @param tag 关键词
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * 获取广告id
     *
     * @return ad_id - 广告id
     */
    public Integer getAdId() {
        return adId;
    }

    /**
     * 设置广告id
     *
     * @param adId 广告id
     */
    public void setAdId(Integer adId) {
        this.adId = adId;
    }
}
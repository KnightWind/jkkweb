package com.jkkp.modules.design.model;

import javax.persistence.*;

@Table(name = "brand_cate_rel")
public class BrandCateRel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 品牌id
     */
    @Column(name = "brand_id")
    private Integer brandId;

    /**
     * 商品分类id
     */
    @Column(name = "cate_id")
    private Integer cateId;

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

    /**
     * 获取商品分类id
     *
     * @return cate_id - 商品分类id
     */
    public Integer getCateId() {
        return cateId;
    }

    /**
     * 设置商品分类id
     *
     * @param cateId 商品分类id
     */
    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }
}
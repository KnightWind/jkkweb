package com.jkkp.modules.design.model;

import javax.persistence.*;

@Table(name = "package_item")
public class PackageItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 商品id
     */
    @Column(name = "item_id")
    private Integer itemId;

    /**
     * 套餐包id
     */
    @Column(name = "package_id")
    private Integer packageId;

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
     * 获取套餐包id
     *
     * @return package_id - 套餐包id
     */
    public Integer getPackageId() {
        return packageId;
    }

    /**
     * 设置套餐包id
     *
     * @param packageId 套餐包id
     */
    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }
}
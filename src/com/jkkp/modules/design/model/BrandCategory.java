package com.jkkp.modules.design.model;

import javax.persistence.*;

@Table(name = "brand_category")
public class BrandCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 品牌分类名称
     */
    private String name;

    /**
     * 父类id,如果为0，说明是一级分类
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 状态：0正常，-1删去
     */
    private Byte status;

    /**
     * 备注
     */
    private String comments;

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
     * 获取品牌分类名称
     *
     * @return name - 品牌分类名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置品牌分类名称
     *
     * @param name 品牌分类名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取父类id,如果为0，说明是一级分类
     *
     * @return parent_id - 父类id,如果为0，说明是一级分类
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置父类id,如果为0，说明是一级分类
     *
     * @param parentId 父类id,如果为0，说明是一级分类
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取状态：0正常，-1删去
     *
     * @return status - 状态：0正常，-1删去
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置状态：0正常，-1删去
     *
     * @param status 状态：0正常，-1删去
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取备注
     *
     * @return comments - 备注
     */
    public String getComments() {
        return comments;
    }

    /**
     * 设置备注
     *
     * @param comments 备注
     */
    public void setComments(String comments) {
        this.comments = comments;
    }
}
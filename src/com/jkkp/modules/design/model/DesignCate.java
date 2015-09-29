package com.jkkp.modules.design.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "design_cate")
public class DesignCate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 分类名称
     */
    @Column(name = "cate_name")
    private String cateName;

    /**
     * 分类标签：huxing|kongjian|fengge
     */
    private String label;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 管理员id
     */
    @Column(name = "admin_id")
    private Integer adminId;

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
     * 获取分类名称
     *
     * @return cate_name - 分类名称
     */
    public String getCateName() {
        return cateName;
    }

    /**
     * 设置分类名称
     *
     * @param cateName 分类名称
     */
    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    /**
     * 获取分类标签：huxing|kongjian|fengge
     *
     * @return label - 分类标签：huxing|kongjian|fengge
     */
    public String getLabel() {
        return label;
    }

    /**
     * 设置分类标签：huxing|kongjian|fengge
     *
     * @param label 分类标签：huxing|kongjian|fengge
     */
    public void setLabel(String label) {
        this.label = label;
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
     * 获取管理员id
     *
     * @return admin_id - 管理员id
     */
    public Integer getAdminId() {
        return adminId;
    }

    /**
     * 设置管理员id
     *
     * @param adminId 管理员id
     */
    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }
}
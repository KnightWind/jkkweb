package com.jkkp.modules.product.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "promotion_tag")
public class PromotionTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 关键词
     */
    private String tag;

    /**
     * 商家id
     */
    @Column(name = "sp_id")
    private Integer spId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 计划id
     */
    @Column(name = "project_id")
    private Integer projectId;

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
     * 获取计划id
     *
     * @return project_id - 计划id
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * 设置计划id
     *
     * @param projectId 计划id
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}
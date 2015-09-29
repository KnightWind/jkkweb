package com.jkkp.modules.basedata.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "search_word")
public class SearchWord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 热门关键词
     */
    private String word;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 0正常-1删除
     */
    private Byte status;

    /**
     * 管理员id
     */
    @Column(name = "admin_id")
    private Integer adminId;

    /**
     * 搜索关键词所在位置：首页顶部：index-top，左1：index-huxing，左2：index-fengge
     */
    private String label;

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
     * 获取热门关键词
     *
     * @return word - 热门关键词
     */
    public String getWord() {
        return word;
    }

    /**
     * 设置热门关键词
     *
     * @param word 热门关键词
     */
    public void setWord(String word) {
        this.word = word;
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
     * 获取0正常-1删除
     *
     * @return status - 0正常-1删除
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置0正常-1删除
     *
     * @param status 0正常-1删除
     */
    public void setStatus(Byte status) {
        this.status = status;
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

    /**
     * 获取搜索关键词所在位置：首页顶部：index-top，左1：index-huxing，左2：index-fengge
     *
     * @return label - 搜索关键词所在位置：首页顶部：index-top，左1：index-huxing，左2：index-fengge
     */
    public String getLabel() {
        return label;
    }

    /**
     * 设置搜索关键词所在位置：首页顶部：index-top，左1：index-huxing，左2：index-fengge
     *
     * @param label 搜索关键词所在位置：首页顶部：index-top，左1：index-huxing，左2：index-fengge
     */
    public void setLabel(String label) {
        this.label = label;
    }
}
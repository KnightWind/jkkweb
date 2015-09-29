package com.jkkp.modules.design.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "ad")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 广告图id
     */
    private String pid;

    /**
     * 广告链接
     */
    private String url;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 0显示-1隐藏
     */
    private Byte status;

    /**
     * 首页中间：index-middle
     */
    private String place;

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
     * 获取广告图id
     *
     * @return pid - 广告图id
     */
    public String getPid() {
        return pid;
    }

    /**
     * 设置广告图id
     *
     * @param pid 广告图id
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * 获取广告链接
     *
     * @return url - 广告链接
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置广告链接
     *
     * @param url 广告链接
     */
    public void setUrl(String url) {
        this.url = url;
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
     * 获取0显示-1隐藏
     *
     * @return status - 0显示-1隐藏
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置0显示-1隐藏
     *
     * @param status 0显示-1隐藏
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取首页中间：index-middle
     *
     * @return place - 首页中间：index-middle
     */
    public String getPlace() {
        return place;
    }

    /**
     * 设置首页中间：index-middle
     *
     * @param place 首页中间：index-middle
     */
    public void setPlace(String place) {
        this.place = place;
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
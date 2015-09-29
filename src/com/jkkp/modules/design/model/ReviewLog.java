package com.jkkp.modules.design.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "review_log")
public class ReviewLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 商品id
     */
    @Column(name = "item_id")
    private Integer itemId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 会员id
     */
    private Integer uid;

    /**
     * 用户ip
     */
    private String ip;

    /**
     * 访问的session_id
     */
    @Column(name = "session_id")
    private String sessionId;

    /**
     * 券id
     */
    @Column(name = "quan_id")
    private Integer quanId;

    /**
     * 套装id
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
     * 获取会员id
     *
     * @return uid - 会员id
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 设置会员id
     *
     * @param uid 会员id
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 获取用户ip
     *
     * @return ip - 用户ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置用户ip
     *
     * @param ip 用户ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 获取访问的session_id
     *
     * @return session_id - 访问的session_id
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * 设置访问的session_id
     *
     * @param sessionId 访问的session_id
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
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
     * 获取套装id
     *
     * @return package_id - 套装id
     */
    public Integer getPackageId() {
        return packageId;
    }

    /**
     * 设置套装id
     *
     * @param packageId 套装id
     */
    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }
}
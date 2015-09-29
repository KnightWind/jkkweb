package com.jkkp.modules.appointment.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "appointment_operator")
public class AppointmentOperator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 订单id
     */
    private Integer aid;

    /**
     * -1关闭-2暂停4开始
     */
    private Byte status;

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
     * 原因描述
     */
    private String reason;

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
     * 获取订单id
     *
     * @return aid - 订单id
     */
    public Integer getAid() {
        return aid;
    }

    /**
     * 设置订单id
     *
     * @param aid 订单id
     */
    public void setAid(Integer aid) {
        this.aid = aid;
    }

    /**
     * 获取-1关闭-2暂停4开始
     *
     * @return status - -1关闭-2暂停4开始
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置-1关闭-2暂停4开始
     *
     * @param status -1关闭-2暂停4开始
     */
    public void setStatus(Byte status) {
        this.status = status;
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

    /**
     * 获取原因描述
     *
     * @return reason - 原因描述
     */
    public String getReason() {
        return reason;
    }

    /**
     * 设置原因描述
     *
     * @param reason 原因描述
     */
    public void setReason(String reason) {
        this.reason = reason;
    }
}
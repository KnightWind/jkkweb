package com.jkkp.modules.order.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "order_change_log")
public class OrderChangeLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 新的状态
     */
    @Column(name = "new_status")
    private Integer newStatus;

    /**
     * 原始状态
     */
    @Column(name = "old_status")
    private Integer oldStatus;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 数据类型：1.商品订单、2零元购、3套装、4券，5服务订单
     */
    private Integer type;

    /**
     * 操作者类型，前台：user，系统：system，后台：admin，供货商：supplier
     */
    private String operator;

    /**
     * 操作用户id,系统默认是0
     */
    private Integer uid;

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
     * 获取新的状态
     *
     * @return new_status - 新的状态
     */
    public Integer getNewStatus() {
        return newStatus;
    }

    /**
     * 设置新的状态
     *
     * @param newStatus 新的状态
     */
    public void setNewStatus(Integer newStatus) {
        this.newStatus = newStatus;
    }

    /**
     * 获取原始状态
     *
     * @return old_status - 原始状态
     */
    public Integer getOldStatus() {
        return oldStatus;
    }

    /**
     * 设置原始状态
     *
     * @param oldStatus 原始状态
     */
    public void setOldStatus(Integer oldStatus) {
        this.oldStatus = oldStatus;
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
     * 获取订单id
     *
     * @return order_id - 订单id
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 设置订单id
     *
     * @param orderId 订单id
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取数据类型：1.商品订单、2零元购、3套装、4券，5服务订单
     *
     * @return type - 数据类型：1.商品订单、2零元购、3套装、4券，5服务订单
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置数据类型：1.商品订单、2零元购、3套装、4券，5服务订单
     *
     * @param type 数据类型：1.商品订单、2零元购、3套装、4券，5服务订单
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取操作者类型，前台：user，系统：system，后台：admin，供货商：supplier
     *
     * @return operator - 操作者类型，前台：user，系统：system，后台：admin，供货商：supplier
     */
    public String getOperator() {
        return operator;
    }

    /**
     * 设置操作者类型，前台：user，系统：system，后台：admin，供货商：supplier
     *
     * @param operator 操作者类型，前台：user，系统：system，后台：admin，供货商：supplier
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * 获取操作用户id,系统默认是0
     *
     * @return uid - 操作用户id,系统默认是0
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 设置操作用户id,系统默认是0
     *
     * @param uid 操作用户id,系统默认是0
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }
}
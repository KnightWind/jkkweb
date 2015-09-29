package com.jkkp.modules.product.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "promotion_project")
public class PromotionProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	/**
     * 计划组名称
     */
    @Column(name = "project_name")
    private String projectName;

    /**
     * 每日费用
     */
    private BigDecimal fee;

    /**
     * 推广开始时间
     */
    @Column(name = "start_date")
    private Date startDate;

    /**
     * 推广结束时间
     */
    @Column(name = "end_date")
    private Date endDate;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 商户id
     */
    @Column(name = "sp_id")
    private Integer spId;

    /**
     * 计划状态：0初始1审核中2推广中-1关闭3已到期-3审核失败
     */
    private Byte status;

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
     * 获取计划组名称
     *
     * @return project_name - 计划组名称
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * 设置计划组名称
     *
     * @param projectName 计划组名称
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * 获取每日费用
     *
     * @return fee - 每日费用
     */
    public BigDecimal getFee() {
        return fee;
    }

    /**
     * 设置每日费用
     *
     * @param fee 每日费用
     */
    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    /**
     * 获取推广开始时间
     *
     * @return start_date - 推广开始时间
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 设置推广开始时间
     *
     * @param startDate 推广开始时间
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 获取推广结束时间
     *
     * @return end_date - 推广结束时间
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置推广结束时间
     *
     * @param endDate 推广结束时间
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
     * 获取商户id
     *
     * @return sp_id - 商户id
     */
    public Integer getSpId() {
        return spId;
    }

    /**
     * 设置商户id
     *
     * @param spId 商户id
     */
    public void setSpId(Integer spId) {
        this.spId = spId;
    }

    /**
     * 获取计划状态：0初始1审核中2推广中-1关闭3已到期-3审核失败
     *
     * @return status - 计划状态：0初始1审核中2推广中-1关闭3已到期-3审核失败
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置计划状态：0初始1审核中2推广中-1关闭3已到期-3审核失败
     *
     * @param status 计划状态：0初始1审核中2推广中-1关闭3已到期-3审核失败
     */
    public void setStatus(Byte status) {
        this.status = status;
    }
}
package com.jkkp.modules.appointment.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "appointment_comment")
public class AppointmentComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * uid
     */
    private Integer uid;

    /**
     * 评论时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 服务评价1-5
     */
    @Column(name = "estimate_service")
    private Byte estimateService;

    /**
     * 评价内容
     */
    private String content;

    /**
     * 描述评分0-5
     */
    @Column(name = "estimate_desc")
    private Byte estimateDesc;

    /**
     * 效率评价1-5
     */
    @Column(name = "estimate_efficiency")
    private Byte estimateEfficiency;

    /**
     * 服务订单id
     */
    @Column(name = "service_id")
    private Integer serviceId;

    /**
     * 商家id
     */
    @Column(name = "sp_id")
    private Integer spId;

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
     * 获取uid
     *
     * @return uid - uid
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 设置uid
     *
     * @param uid uid
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 获取评论时间
     *
     * @return create_time - 评论时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置评论时间
     *
     * @param createTime 评论时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取服务评价1-5
     *
     * @return estimate_service - 服务评价1-5
     */
    public Byte getEstimateService() {
        return estimateService;
    }

    /**
     * 设置服务评价1-5
     *
     * @param estimateService 服务评价1-5
     */
    public void setEstimateService(Byte estimateService) {
        this.estimateService = estimateService;
    }

    /**
     * 获取评价内容
     *
     * @return content - 评价内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置评价内容
     *
     * @param content 评价内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取描述评分0-5
     *
     * @return estimate_desc - 描述评分0-5
     */
    public Byte getEstimateDesc() {
        return estimateDesc;
    }

    /**
     * 设置描述评分0-5
     *
     * @param estimateDesc 描述评分0-5
     */
    public void setEstimateDesc(Byte estimateDesc) {
        this.estimateDesc = estimateDesc;
    }

    /**
     * 获取效率评价1-5
     *
     * @return estimate_efficiency - 效率评价1-5
     */
    public Byte getEstimateEfficiency() {
        return estimateEfficiency;
    }

    /**
     * 设置效率评价1-5
     *
     * @param estimateEfficiency 效率评价1-5
     */
    public void setEstimateEfficiency(Byte estimateEfficiency) {
        this.estimateEfficiency = estimateEfficiency;
    }

    /**
     * 获取服务订单id
     *
     * @return service_id - 服务订单id
     */
    public Integer getServiceId() {
        return serviceId;
    }

    /**
     * 设置服务订单id
     *
     * @param serviceId 服务订单id
     */
    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
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
}
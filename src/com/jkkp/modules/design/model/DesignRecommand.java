package com.jkkp.modules.design.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "design_recommand")
public class DesignRecommand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 效果图id
     */
    @Column(name = "design_id")
    private Integer designId;

    /**
     * 所在城市域名
     */
    @Column(name = "city_domain")
    private String cityDomain;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 显示位置，style-m：首页中间,style-r:风格右侧，list：列表页，jushi-m：户型推广中,jushi-r:户型推广右侧
     */
    private String label;

    /**
     * 显示状态：0显示-1隐藏
     */
    private Byte status;

    /**
     * 图片id
     */
    private String pid;

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
     * 获取效果图id
     *
     * @return design_id - 效果图id
     */
    public Integer getDesignId() {
        return designId;
    }

    /**
     * 设置效果图id
     *
     * @param designId 效果图id
     */
    public void setDesignId(Integer designId) {
        this.designId = designId;
    }

    /**
     * 获取所在城市域名
     *
     * @return city_domain - 所在城市域名
     */
    public String getCityDomain() {
        return cityDomain;
    }

    /**
     * 设置所在城市域名
     *
     * @param cityDomain 所在城市域名
     */
    public void setCityDomain(String cityDomain) {
        this.cityDomain = cityDomain;
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
     * 获取显示位置，style-m：首页中间,style-r:风格右侧，list：列表页，jushi-m：户型推广中,jushi-r:户型推广右侧
     *
     * @return label - 显示位置，style-m：首页中间,style-r:风格右侧，list：列表页，jushi-m：户型推广中,jushi-r:户型推广右侧
     */
    public String getLabel() {
        return label;
    }

    /**
     * 设置显示位置，style-m：首页中间,style-r:风格右侧，list：列表页，jushi-m：户型推广中,jushi-r:户型推广右侧
     *
     * @param label 显示位置，style-m：首页中间,style-r:风格右侧，list：列表页，jushi-m：户型推广中,jushi-r:户型推广右侧
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * 获取显示状态：0显示-1隐藏
     *
     * @return status - 显示状态：0显示-1隐藏
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置显示状态：0显示-1隐藏
     *
     * @param status 显示状态：0显示-1隐藏
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取图片id
     *
     * @return pid - 图片id
     */
    public String getPid() {
        return pid;
    }

    /**
     * 设置图片id
     *
     * @param pid 图片id
     */
    public void setPid(String pid) {
        this.pid = pid;
    }
}
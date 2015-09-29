package com.jkkp.modules.basedata.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "new_cate_recommand")
public class NewCateRecommand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 新闻分类（二级）
     */
    private Integer cid;

    /**
     * 分类配图
     */
    private String pid;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 所在城市域名
     */
    @Column(name = "city_domain")
    private String cityDomain;

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
     * 获取新闻分类（二级）
     *
     * @return cid - 新闻分类（二级）
     */
    public Integer getCid() {
        return cid;
    }

    /**
     * 设置新闻分类（二级）
     *
     * @param cid 新闻分类（二级）
     */
    public void setCid(Integer cid) {
        this.cid = cid;
    }

    /**
     * 获取分类配图
     *
     * @return pid - 分类配图
     */
    public String getPid() {
        return pid;
    }

    /**
     * 设置分类配图
     *
     * @param pid 分类配图
     */
    public void setPid(String pid) {
        this.pid = pid;
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
}
package com.jkkp.modules.product.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "item_recommand")
public class ItemRecommand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 商品id
     */
    @Column(name = "item_id")
    private Integer itemId;

    /**
     * 城市所属域名
     */
    @Column(name = "city_domain")
    private String cityDomain;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 显示状态：0显示-1隐藏
     */
    private Byte status;

    /**
     * 商品位置标识：gonggong|special
     */
    private String label;

    /**
     * 序列号
     */
    private Integer seq;

    /**
     * 封面图片
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
     * 获取城市所属域名
     *
     * @return city_domain - 城市所属域名
     */
    public String getCityDomain() {
        return cityDomain;
    }

    /**
     * 设置城市所属域名
     *
     * @param cityDomain 城市所属域名
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
     * 获取商品位置标识：gonggong|special
     *
     * @return label - 商品位置标识：gonggong|special
     */
    public String getLabel() {
        return label;
    }

    /**
     * 设置商品位置标识：gonggong|special
     *
     * @param label 商品位置标识：gonggong|special
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * 获取序列号
     *
     * @return seq - 序列号
     */
    public Integer getSeq() {
        return seq;
    }

    /**
     * 设置序列号
     *
     * @param seq 序列号
     */
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    /**
     * 获取封面图片
     *
     * @return pid - 封面图片
     */
    public String getPid() {
        return pid;
    }

    /**
     * 设置封面图片
     *
     * @param pid 封面图片
     */
    public void setPid(String pid) {
        this.pid = pid;
    }
}
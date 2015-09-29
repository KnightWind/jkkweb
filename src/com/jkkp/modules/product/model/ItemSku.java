package com.jkkp.modules.product.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "item_sku")
public class ItemSku {
    /**
     * 库存编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 规格名
     */
    private String name;

    /**
     * 规格值
     */
    private String value;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 0正常-1删除
     */
    private Boolean status;

    /**
     * 规格顺序:1,2
     */
    private Boolean seq;

    /**
     * 规格组ID
     */
    @Column(name = "sku_group_id")
    private Integer skuGroupId;

    /**
     * 获取库存编号
     *
     * @return id - 库存编号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置库存编号
     *
     * @param id 库存编号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取规格名
     *
     * @return name - 规格名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置规格名
     *
     * @param name 规格名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取规格值
     *
     * @return value - 规格值
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置规格值
     *
     * @param value 规格值
     */
    public void setValue(String value) {
        this.value = value;
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
    public Boolean getStatus() {
        return status;
    }

    /**
     * 设置0正常-1删除
     *
     * @param status 0正常-1删除
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * 获取规格顺序:1,2
     *
     * @return seq - 规格顺序:1,2
     */
    public Boolean getSeq() {
        return seq;
    }

    /**
     * 设置规格顺序:1,2
     *
     * @param seq 规格顺序:1,2
     */
    public void setSeq(Boolean seq) {
        this.seq = seq;
    }

    /**
     * 获取规格组ID
     *
     * @return sku_group_id - 规格组ID
     */
    public Integer getSkuGroupId() {
        return skuGroupId;
    }

    /**
     * 设置规格组ID
     *
     * @param skuGroupId 规格组ID
     */
    public void setSkuGroupId(Integer skuGroupId) {
        this.skuGroupId = skuGroupId;
    }
}
package com.jkkp.modules.product.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "item_sku_group")
public class ItemSkuGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 商家ID
     */
    @Column(name = "sp_id")
    private Integer spId;

    /**
     * 规格组名
     */
    private String name;

    /**
     * 状态：0正常,-1删去
     */
    private Boolean status;

    @Column(name = "create_time")
    private Date createTime;

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
     * 获取商家ID
     *
     * @return sp_id - 商家ID
     */
    public Integer getSpId() {
        return spId;
    }

    /**
     * 设置商家ID
     *
     * @param spId 商家ID
     */
    public void setSpId(Integer spId) {
        this.spId = spId;
    }

    /**
     * 获取规格组名
     *
     * @return name - 规格组名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置规格组名
     *
     * @param name 规格组名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取状态：0正常,-1删去
     *
     * @return status - 状态：0正常,-1删去
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * 设置状态：0正常,-1删去
     *
     * @param status 状态：0正常,-1删去
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
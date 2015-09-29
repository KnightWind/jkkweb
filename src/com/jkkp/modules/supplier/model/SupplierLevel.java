package com.jkkp.modules.supplier.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "supplier_level")
public class SupplierLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 等级名称
     */
    @Column(name = "level_name")
    private String levelName;

    /**
     * 等级金额
     */
    @Column(name = "level_money")
    private Integer levelMoney;

    /**
     * 创建时间
     */
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
     * 获取等级名称
     *
     * @return level_name - 等级名称
     */
    public String getLevelName() {
        return levelName;
    }

    /**
     * 设置等级名称
     *
     * @param levelName 等级名称
     */
    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    /**
     * 获取等级金额
     *
     * @return level_money - 等级金额
     */
    public Integer getLevelMoney() {
        return levelMoney;
    }

    /**
     * 设置等级金额
     *
     * @param levelMoney 等级金额
     */
    public void setLevelMoney(Integer levelMoney) {
        this.levelMoney = levelMoney;
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
}
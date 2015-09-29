package com.jkkp.modules.supplier.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "supplier_acl")
public class SupplierAcl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 访问url
     */
    private String url;

    /**
     * 是否需要权限检查:1需要，0不需要
     */
    private Boolean check;

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
     * 获取访问url
     *
     * @return url - 访问url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置访问url
     *
     * @param url 访问url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取是否需要权限检查:1需要，0不需要
     *
     * @return check - 是否需要权限检查:1需要，0不需要
     */
    public Boolean getCheck() {
        return check;
    }

    /**
     * 设置是否需要权限检查:1需要，0不需要
     *
     * @param check 是否需要权限检查:1需要，0不需要
     */
    public void setCheck(Boolean check) {
        this.check = check;
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
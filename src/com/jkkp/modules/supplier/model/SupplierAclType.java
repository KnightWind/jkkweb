package com.jkkp.modules.supplier.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "supplier_acl_type")
public class SupplierAclType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "acl_id")
    private Byte aclId;

    /**
     * 类型：1建材商 2装修公司 3工长 4实体店 5监理 6设计师
     */
    private Boolean type;

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
     * @return acl_id
     */
    public Byte getAclId() {
        return aclId;
    }

    /**
     * @param aclId
     */
    public void setAclId(Byte aclId) {
        this.aclId = aclId;
    }

    /**
     * 获取类型：1建材商 2装修公司 3工长 4实体店 5监理 6设计师
     *
     * @return type - 类型：1建材商 2装修公司 3工长 4实体店 5监理 6设计师
     */
    public Boolean getType() {
        return type;
    }

    /**
     * 设置类型：1建材商 2装修公司 3工长 4实体店 5监理 6设计师
     *
     * @param type 类型：1建材商 2装修公司 3工长 4实体店 5监理 6设计师
     */
    public void setType(Boolean type) {
        this.type = type;
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
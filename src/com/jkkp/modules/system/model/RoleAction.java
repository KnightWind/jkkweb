package com.jkkp.modules.system.model;

import javax.persistence.Table;

@Table(name = "role_action")
public class RoleAction {
    /**
     * 角色id
     */
    private Integer rid;

    /**
     * 权限id
     */
    private Integer aid;

    /**
     * 获取角色id
     *
     * @return rid - 角色id
     */
    public Integer getRid() {
        return rid;
    }

    /**
     * 设置角色id
     *
     * @param rid 角色id
     */
    public void setRid(Integer rid) {
        this.rid = rid;
    }

    /**
     * 获取权限id
     *
     * @return aid - 权限id
     */
    public Integer getAid() {
        return aid;
    }

    /**
     * 设置权限id
     *
     * @param aid 权限id
     */
    public void setAid(Integer aid) {
        this.aid = aid;
    }
}
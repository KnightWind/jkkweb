package com.jkkp.modules.basedata.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "help")
public class Help {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 帮助标题
     */
    private String title;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 分类id
     */
    @Column(name = "cate_id")
    private Integer cateId;

    /**
     * 显示状态：0显示-1取消
     */
    private Byte status;

    private String content;

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
     * 获取帮助标题
     *
     * @return title - 帮助标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置帮助标题
     *
     * @param title 帮助标题
     */
    public void setTitle(String title) {
        this.title = title;
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
     * 获取分类id
     *
     * @return cate_id - 分类id
     */
    public Integer getCateId() {
        return cateId;
    }

    /**
     * 设置分类id
     *
     * @param cateId 分类id
     */
    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }

    /**
     * 获取显示状态：0显示-1取消
     *
     * @return status - 显示状态：0显示-1取消
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置显示状态：0显示-1取消
     *
     * @param status 显示状态：0显示-1取消
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }
}
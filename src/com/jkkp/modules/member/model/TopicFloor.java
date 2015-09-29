package com.jkkp.modules.member.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "topic_floor")
public class TopicFloor {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 主题id
     */
    @Column(name = "topic_id")
    private Integer topicId;

    /**
     * 楼层标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 图片id-1
     */
    private String pid1;

    /**
     * 图片id-2
     */
    private String pid2;

    /**
     * 图片id-3
     */
    private String pid3;

    /**
     * 图片id-4
     */
    private String pid4;

    /**
     * 图片id-5
     */
    private String pid5;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 显示状态：0显示-1不显示
     */
    private Byte status;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

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
     * 获取主题id
     *
     * @return topic_id - 主题id
     */
    public Integer getTopicId() {
        return topicId;
    }

    /**
     * 设置主题id
     *
     * @param topicId 主题id
     */
    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    /**
     * 获取楼层标题
     *
     * @return title - 楼层标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置楼层标题
     *
     * @param title 楼层标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取内容
     *
     * @return content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取图片id-1
     *
     * @return pid1 - 图片id-1
     */
    public String getPid1() {
        return pid1;
    }

    /**
     * 设置图片id-1
     *
     * @param pid1 图片id-1
     */
    public void setPid1(String pid1) {
        this.pid1 = pid1;
    }

    /**
     * 获取图片id-2
     *
     * @return pid2 - 图片id-2
     */
    public String getPid2() {
        return pid2;
    }

    /**
     * 设置图片id-2
     *
     * @param pid2 图片id-2
     */
    public void setPid2(String pid2) {
        this.pid2 = pid2;
    }

    /**
     * 获取图片id-3
     *
     * @return pid3 - 图片id-3
     */
    public String getPid3() {
        return pid3;
    }

    /**
     * 设置图片id-3
     *
     * @param pid3 图片id-3
     */
    public void setPid3(String pid3) {
        this.pid3 = pid3;
    }

    /**
     * 获取图片id-4
     *
     * @return pid4 - 图片id-4
     */
    public String getPid4() {
        return pid4;
    }

    /**
     * 设置图片id-4
     *
     * @param pid4 图片id-4
     */
    public void setPid4(String pid4) {
        this.pid4 = pid4;
    }

    /**
     * 获取图片id-5
     *
     * @return pid5 - 图片id-5
     */
    public String getPid5() {
        return pid5;
    }

    /**
     * 设置图片id-5
     *
     * @param pid5 图片id-5
     */
    public void setPid5(String pid5) {
        this.pid5 = pid5;
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
     * 获取显示状态：0显示-1不显示
     *
     * @return status - 显示状态：0显示-1不显示
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置显示状态：0显示-1不显示
     *
     * @param status 显示状态：0显示-1不显示
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
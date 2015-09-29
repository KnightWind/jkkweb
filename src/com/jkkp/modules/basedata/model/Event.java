package com.jkkp.modules.basedata.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.*;

@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户称呼
     */
    private String name;

    /**
     * 用户电话
     */
    private String mobile;

    /**
     * 事件内容
     */
    private String content;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 事件类型
     */
    private Integer type;

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
     * 获取用户称呼
     *
     * @return name - 用户称呼
     */
    public String getName() {
        return name;
    }

    /**
     * 设置用户称呼
     *
     * @param name 用户称呼
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取用户电话
     *
     * @return mobile - 用户电话
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置用户电话
     *
     * @param mobile 用户电话
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取事件内容
     *
     * @return content - 事件内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置事件内容
     *
     * @param content 事件内容
     */
    public void setContent(String content) {
        this.content = content;
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
     * 获取事件类型
     *
     * @return type - 事件类型
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置事件类型
     *
     * @param type 事件类型
     */
    public void setType(Integer type) {
        this.type = type;
    }
    
    public String getCreateTimeHandle(){
    	if(createTime!=null){
    		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    		return sdf.format(createTime);
    	}
    	return "";
    }
}
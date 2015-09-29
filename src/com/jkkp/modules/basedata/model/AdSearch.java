package com.jkkp.modules.basedata.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "ad_search")
public class AdSearch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 广告名称
     */
    private String name;

    /**
     * 0正常-1下架
     */
    private Byte status;

    /**
     * 广告url
     */
    private String url;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 广告图片
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
     * 获取广告名称
     *
     * @return name - 广告名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置广告名称
     *
     * @param name 广告名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取0正常-1下架
     *
     * @return status - 0正常-1下架
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置0正常-1下架
     *
     * @param status 0正常-1下架
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取广告url
     *
     * @return url - 广告url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置广告url
     *
     * @param url 广告url
     */
    public void setUrl(String url) {
        this.url = url;
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
     * 获取广告图片
     *
     * @return pid - 广告图片
     */
    public String getPid() {
        return pid;
    }

    /**
     * 设置广告图片
     *
     * @param pid 广告图片
     */
    public void setPid(String pid) {
        this.pid = pid;
    }
    public String getIson(){
    	if(this.status!=null){
    		if(this.status==0){
    			return "正常";
    		}else {
				return "下架";
			}
    	}
    	return "";
    }
}
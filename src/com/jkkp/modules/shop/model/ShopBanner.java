package com.jkkp.modules.shop.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "shop_banner")
public class ShopBanner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 广告名称
     */
    private String title;

    /**
     * 图片地址
     */
    private String pid;

    /**
     * 广告打开地址
     */
    private String url;

    /**
     * 顺序
     */
    private Integer seq;

    /**
     * 放置位置：top|middle
     */
    private String place;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 操作管理员id
     */
    @Column(name = "admin_id")
    private Integer adminId;

    /**
     * 0启用-1未启用
     */
    private Byte status;

    /**
     * 城市所属域名
     */
    @Column(name = "city_domain")
    private String cityDomain;

    /**
     * 店铺id
     */
    @Column(name = "shop_id")
    private Integer shopId;

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
     * @return title - 广告名称
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置广告名称
     *
     * @param title 广告名称
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取图片地址
     *
     * @return pid - 图片地址
     */
    public String getPid() {
        return pid;
    }

    /**
     * 设置图片地址
     *
     * @param pid 图片地址
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * 获取广告打开地址
     *
     * @return url - 广告打开地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置广告打开地址
     *
     * @param url 广告打开地址
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取顺序
     *
     * @return seq - 顺序
     */
    public Integer getSeq() {
        return seq;
    }

    /**
     * 设置顺序
     *
     * @param seq 顺序
     */
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    /**
     * 获取放置位置：top|middle
     *
     * @return place - 放置位置：top|middle
     */
    public String getPlace() {
        return place;
    }

    /**
     * 设置放置位置：top|middle
     *
     * @param place 放置位置：top|middle
     */
    public void setPlace(String place) {
        this.place = place;
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
     * 获取操作管理员id
     *
     * @return admin_id - 操作管理员id
     */
    public Integer getAdminId() {
        return adminId;
    }

    /**
     * 设置操作管理员id
     *
     * @param adminId 操作管理员id
     */
    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    /**
     * 获取0启用-1未启用
     *
     * @return status - 0启用-1未启用
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置0启用-1未启用
     *
     * @param status 0启用-1未启用
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取城市所属域名
     *
     * @return city_domain - 城市所属域名
     */
    public String getCityDomain() {
        return cityDomain;
    }

    /**
     * 设置城市所属域名
     *
     * @param cityDomain 城市所属域名
     */
    public void setCityDomain(String cityDomain) {
        this.cityDomain = cityDomain;
    }

    /**
     * 获取店铺id
     *
     * @return shop_id - 店铺id
     */
    public Integer getShopId() {
        return shopId;
    }

    /**
     * 设置店铺id
     *
     * @param shopId 店铺id
     */
    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }
    public String getIson(){
    	if(this.status!=null){
    		if(this.status==0){
    			return "启用";
    		}else {
				return "未启用";
			}
    	}
    	return "";
    }
}
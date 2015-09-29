package com.jkkp.modules.order.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.*;

@Table(name = "order_free")
public class OrderFree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户uid
     */
    private Integer uid;

    /**
     * 商品id
     */
    @Column(name = "item_id")
    private Integer itemId;

    /**
     * 商家地址
     */
    @Column(name = "sp_address")
    private String spAddress;

    /**
     * 商家电话
     */
    @Column(name = "sp_mobile")
    private String spMobile;

    /**
     * 领取时间
     */
    @Column(name = "get_time")
    private Date getTime;

    /**
     * 0未用，1已用
     */
    private Byte status;

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
     * 获取用户uid
     *
     * @return uid - 用户uid
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 设置用户uid
     *
     * @param uid 用户uid
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 获取商品id
     *
     * @return item_id - 商品id
     */
    public Integer getItemId() {
        return itemId;
    }

    /**
     * 设置商品id
     *
     * @param itemId 商品id
     */
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    /**
     * 获取商家地址
     *
     * @return sp_address - 商家地址
     */
    public String getSpAddress() {
        return spAddress;
    }

    /**
     * 设置商家地址
     *
     * @param spAddress 商家地址
     */
    public void setSpAddress(String spAddress) {
        this.spAddress = spAddress;
    }

    /**
     * 获取商家电话
     *
     * @return sp_mobile - 商家电话
     */
    public String getSpMobile() {
        return spMobile;
    }

    /**
     * 设置商家电话
     *
     * @param spMobile 商家电话
     */
    public void setSpMobile(String spMobile) {
        this.spMobile = spMobile;
    }

    /**
     * 获取领取时间
     *
     * @return get_time - 领取时间
     */
    public Date getGetTime() {
        return getTime;
    }

    /**
     * 设置领取时间
     *
     * @param getTime 领取时间
     */
    public void setGetTime(Date getTime) {
        this.getTime = getTime;
    }

    /**
     * 获取0未用，1已用
     *
     * @return status - 0未用，1已用
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置0未用，1已用
     *
     * @param status 0未用，1已用
     */
    public void setStatus(Byte status) {
        this.status = status;
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
    
	
    public String getCreateTimeHandle(){
    	if(createTime!=null){
    		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    		return sdf.format(createTime);
    	}
    	return "";
    }
    
    public String getGetTimeHandle(){
    	if(getTime!=null){
    		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    		return sdf.format(getTime);
    	}
    	return "";
    }
    
    
	public String getStatusHandle(){
		if(status==1){
			return "已用";
		}else if(status==0){
			return "未用";
		}
		return "";
	}
}
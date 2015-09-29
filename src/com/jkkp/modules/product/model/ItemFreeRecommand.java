package com.jkkp.modules.product.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "item_free_recommand")
public class ItemFreeRecommand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 商品id
     */
    @Column(name = "item_id")
    private Integer itemId;

    /**
     * -1隐藏，0显示
     */
    private Byte status;

    /**
     * 所在城市域名
     */
    @Column(name = "city_domain")
    private String cityDomain;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 显示位置：index：首页，list：列表页，yugao:下期预告商品
     */
    private String label;

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
     * 获取-1隐藏，0显示
     *
     * @return status - -1隐藏，0显示
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置-1隐藏，0显示
     *
     * @param status -1隐藏，0显示
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取所在城市域名
     *
     * @return city_domain - 所在城市域名
     */
    public String getCityDomain() {
        return cityDomain;
    }

    /**
     * 设置所在城市域名
     *
     * @param cityDomain 所在城市域名
     */
    public void setCityDomain(String cityDomain) {
        this.cityDomain = cityDomain;
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
     * 获取显示位置：index：首页，list：列表页，yugao:下期预告商品
     *
     * @return label - 显示位置：index：首页，list：列表页，yugao:下期预告商品
     */
    public String getLabel() {
        return label;
    }

    /**
     * 设置显示位置：index：首页，list：列表页，yugao:下期预告商品
     *
     * @param label 显示位置：index：首页，list：列表页，yugao:下期预告商品
     */
    public void setLabel(String label) {
        this.label = label;
    }
    public String getIson(){
    	if(this.status!=null){
    		if(this.status==0){
    			return "显示";
    		}else {
				return "隐藏";
			}
    	}
    	return "";
    }
}
package com.jkkp.modules.product.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "item_free_rule")
public class ItemFreeRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 级别
     */
    private Integer level;

    /**
     * 有无资格：0无1有
     */
    private Byte qualification;

    /**
     * 可领取多少个商家的数量，0为不限制
     */
    @Column(name = "sp_num")
    private Integer spNum;

    /**
     * 每个商家可领取多少个商品，0为不限制
     */
    @Column(name = "item_num")
    private Integer itemNum;

    /**
     * 分类id，多个用半角逗号隔开
     */
    @Column(name = "cate_id")
    private String cateId;

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
     * 获取级别
     *
     * @return level - 级别
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置级别
     *
     * @param level 级别
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取有无资格：0无1有
     *
     * @return qualification - 有无资格：0无1有
     */
    public Byte getQualification() {
        return qualification;
    }

    /**
     * 设置有无资格：0无1有
     *
     * @param qualification 有无资格：0无1有
     */
    public void setQualification(Byte qualification) {
        this.qualification = qualification;
    }

    /**
     * 获取可领取多少个商家的数量，0为不限制
     *
     * @return sp_num - 可领取多少个商家的数量，0为不限制
     */
    public Integer getSpNum() {
        return spNum;
    }

    /**
     * 设置可领取多少个商家的数量，0为不限制
     *
     * @param spNum 可领取多少个商家的数量，0为不限制
     */
    public void setSpNum(Integer spNum) {
        this.spNum = spNum;
    }

    /**
     * 获取每个商家可领取多少个商品，0为不限制
     *
     * @return item_num - 每个商家可领取多少个商品，0为不限制
     */
    public Integer getItemNum() {
        return itemNum;
    }

    /**
     * 设置每个商家可领取多少个商品，0为不限制
     *
     * @param itemNum 每个商家可领取多少个商品，0为不限制
     */
    public void setItemNum(Integer itemNum) {
        this.itemNum = itemNum;
    }

    /**
     * 获取分类id，多个用半角逗号隔开
     *
     * @return cate_id - 分类id，多个用半角逗号隔开
     */
    public String getCateId() {
        return cateId;
    }

    /**
     * 设置分类id，多个用半角逗号隔开
     *
     * @param cateId 分类id，多个用半角逗号隔开
     */
    public void setCateId(String cateId) {
        this.cateId = cateId;
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
    
    public String getLevelHandle(){
    	if(level==1){
    		return "一";
    	}
    	if(level==2){
    		return "二";
    	}
    	if(level==3){
    		return "三";
    	}
    	if(level==4){
    		return "四";
    	}
    	if(level==5){
    		return "五";
    	}
    	if(level==6){
    		return "六";
    	}
    	if(level==7){
    		return "七";
    	}
    	if(level==8){
    		return "八";
    	}
    	if(level==9){
    		return "九";
    	}
    	return "";
    }
    
    public boolean getCheckHandle(){
    	if(qualification!=null){
    		if(qualification==1){
    			return true;
    		}
    	}
    	return false;
    }
}
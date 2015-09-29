package com.jkkp.modules.member.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "member_level_rule")
public class MemberLevelRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer level;

    /**
     * 规则描述
     */
    private String rule;

    /**
     * 消费满多少元
     */
    private BigDecimal price;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 需要评论数量
     */
    @Column(name = "need_comment")
    private Integer needComment;

    /**
     * 需要完成订单数
     */
    @Column(name = "need_buy_num")
    private Integer needBuyNum;

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
     * @return level
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * @param level
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取规则描述
     *
     * @return rule - 规则描述
     */
    public String getRule() {
        return rule;
    }

    /**
     * 设置规则描述
     *
     * @param rule 规则描述
     */
    public void setRule(String rule) {
        this.rule = rule;
    }

    /**
     * 获取消费满多少元
     *
     * @return price - 消费满多少元
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置消费满多少元
     *
     * @param price 消费满多少元
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
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
     * 获取需要评论数量
     *
     * @return need_comment - 需要评论数量
     */
    public Integer getNeedComment() {
        return needComment;
    }

    /**
     * 设置需要评论数量
     *
     * @param needComment 需要评论数量
     */
    public void setNeedComment(Integer needComment) {
        this.needComment = needComment;
    }

    /**
     * 获取需要完成订单数
     *
     * @return need_buy_num - 需要完成订单数
     */
    public Integer getNeedBuyNum() {
        return needBuyNum;
    }

    /**
     * 设置需要完成订单数
     *
     * @param needBuyNum 需要完成订单数
     */
    public void setNeedBuyNum(Integer needBuyNum) {
        this.needBuyNum = needBuyNum;
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
}
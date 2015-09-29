package com.jkkp.modules.supplier.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.*;

@Table(name = "supplier_pay")
public class SupplierPay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 商家id
     */
    @Column(name = "sp_id")
    private Integer spId;

    /**
     * 充值次数
     */
    private Integer num;

    /**
     * 剩余次数
     */
    @Column(name = "left_num")
    private Integer leftNum;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 首次充值
     */
    @Column(name = "first_pay_time")
    private Date firstPayTime;

    /**
     * 最后一次充值时间
     */
    @Column(name = "last_pay_time")
    private Date lastPayTime;

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
     * 获取商家id
     *
     * @return sp_id - 商家id
     */
    public Integer getSpId() {
        return spId;
    }

    /**
     * 设置商家id
     *
     * @param spId 商家id
     */
    public void setSpId(Integer spId) {
        this.spId = spId;
    }

    /**
     * 获取充值次数
     *
     * @return num - 充值次数
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 设置充值次数
     *
     * @param num 充值次数
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 获取剩余次数
     *
     * @return left_num - 剩余次数
     */
    public Integer getLeftNum() {
        return leftNum;
    }

    /**
     * 设置剩余次数
     *
     * @param leftNum 剩余次数
     */
    public void setLeftNum(Integer leftNum) {
        this.leftNum = leftNum;
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
     * 获取首次充值
     *
     * @return first_pay_time - 首次充值
     */
    public Date getFirstPayTime() {
        return firstPayTime;
    }

    /**
     * 设置首次充值
     *
     * @param firstPayTime 首次充值
     */
    public void setFirstPayTime(Date firstPayTime) {
        this.firstPayTime = firstPayTime;
    }

    /**
     * 获取最后一次充值时间
     *
     * @return last_pay_time - 最后一次充值时间
     */
    public Date getLastPayTime() {
        return lastPayTime;
    }

    /**
     * 设置最后一次充值时间
     *
     * @param lastPayTime 最后一次充值时间
     */
    public void setLastPayTime(Date lastPayTime) {
        this.lastPayTime = lastPayTime;
    }
    
    public String getFirstPayTimeHandle(){
    	if(firstPayTime!=null){
    		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    		return sdf.format(firstPayTime);
    	}
    	return "";
    }
    
    public String getLastPayTimeHandle(){
    	if(lastPayTime!=null){
    		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    		return sdf.format(lastPayTime);
    	}
    	return "";
    }
    
}
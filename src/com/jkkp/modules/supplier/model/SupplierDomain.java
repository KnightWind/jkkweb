package com.jkkp.modules.supplier.model;

import java.util.Date;
import javax.persistence.*;

import com.jkkp.utils.DateUtil;

@Table(name = "supplier_domain")
public class SupplierDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 开启时间
     */
    @Column(name = "enable_time")
    private Date enableTime;

    /**
     * 停用时间
     */
    @Column(name = "stop_time")
    private Date stopTime;

    /**
     * 绑定状态：0未绑定1绑定中-1解除绑定
     */
    @Column(name = "bind_status")
    private Byte bindStatus;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 域名
     */
    private String domain;

    /**
     * 商家id
     */
    @Column(name = "sp_id")
    private Integer spId;

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
     * 获取开启时间
     *
     * @return enable_time - 开启时间
     */
    public Date getEnableTime() {
        return enableTime;
    }

    /**
     * 设置开启时间
     *
     * @param enableTime 开启时间
     */
    public void setEnableTime(Date enableTime) {
        this.enableTime = enableTime;
    }
    public String getEnd() {
		return enableTime == null ? "" : DateUtil.formatDateTime(enableTime);
	}
    public String getSto() {
		return stopTime == null ? "" : DateUtil.formatDateTime(stopTime);
	}
    /**
     * 获取停用时间
     *
     * @return stop_time - 停用时间
     */
    public Date getStopTime() {
        return stopTime;
    }

    /**
     * 设置停用时间
     *
     * @param stopTime 停用时间
     */
    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    /**
     * 获取绑定状态：0未绑定1绑定中-1解除绑定
     *
     * @return bind_status - 绑定状态：0未绑定1绑定中-1解除绑定
     */
    public String getBi(){
    	if(this.bindStatus!=null){
    		if(this.bindStatus==0){
    			return "未绑定";
    		}else if (this.bindStatus==1) {
				return "绑定中";
			}
    		else {
				return "解除绑定";
			}
    	}
    		return "";
    }
    public Byte getBindStatus() {
        return bindStatus;
    }

    /**
     * 设置绑定状态：0未绑定1绑定中-1解除绑定
     *
     * @param bindStatus 绑定状态：0未绑定1绑定中-1解除绑定
     */
    public void setBindStatus(Byte bindStatus) {
        this.bindStatus = bindStatus;
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
     * 获取域名
     *
     * @return domain - 域名
     */
    public String getDomain() {
        return domain;
    }

    /**
     * 设置域名
     *
     * @param domain 域名
     */
    public void setDomain(String domain) {
        this.domain = domain;
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
}
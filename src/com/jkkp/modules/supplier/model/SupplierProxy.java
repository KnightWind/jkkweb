package com.jkkp.modules.supplier.model;

import java.util.Date;
import javax.persistence.*;

import com.jkkp.utils.DateUtil;

@Table(name = "supplier_proxy")
public class SupplierProxy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 代管开始时间
     */
    @Column(name = "proxy_start_date")
    private Date proxyStartDate;

    /**
     * 代管结束时间
     */
    @Column(name = "proxy_end_date")
    private Date proxyEndDate;

    /**
     * 审核状态：0申请中1已通过-1未通过2已到期
     */
    @Column(name = "check_status")
    private Byte checkStatus;

    /**
     * 商家id
     */
    @Column(name = "sp_id")
    private Integer spId;

    /**
     * 申请时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 审核时间
     */
    @Column(name = "check_time")
    private Date checkTime;

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
     * 获取代管开始时间
     *
     * @return proxy_start_date - 代管开始时间
     */
    public Date getProxyStartDate() {
        return proxyStartDate;
    }

    /**
     * 设置代管开始时间
     *
     * @param proxyStartDate 代管开始时间
     */
    public void setProxyStartDate(Date proxyStartDate) {
        this.proxyStartDate = proxyStartDate;
    }
    public String getOp() {
		return proxyStartDate == null ? "" : DateUtil.formatDateTime(proxyStartDate);
	}
    /**
     * 获取代管结束时间
     *
     * @return proxy_end_date - 代管结束时间
     */
    public Date getProxyEndDate() {
        return proxyEndDate;
    }

    /**
     * 设置代管结束时间
     *
     * @param proxyEndDate 代管结束时间
     */
    public void setProxyEndDate(Date proxyEndDate) {
        this.proxyEndDate = proxyEndDate;
    }
    public String getOpend() {
		return proxyEndDate == null ? "" : DateUtil.formatDateTime(proxyEndDate);
	}
    /**
     * 获取审核状态：0申请中1已通过-1未通过2已到期
     *
     * @return check_status - 审核状态：0申请中1已通过-1未通过2已到期
     */
    public Byte getCheckStatus() {
        return checkStatus;
    }

    /**
     * 设置审核状态：0申请中1已通过-1未通过2已到期
     *
     * @param checkStatus 审核状态：0申请中1已通过-1未通过2已到期
     */
    public void setCheckStatus(Byte checkStatus) {
        this.checkStatus = checkStatus;
    }
    public String getDong(){
    	if(this.checkStatus!=null){
    		if(this.checkStatus==0){
    			return "申请中";
    		}else if (this.checkStatus==1) {
				return "已通过";
			}else if (this.checkStatus==-1) {
				return "未通过";
			}else {
				return "已到期";
			}
    	}
    	return "";
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
     * 获取申请时间
     *
     * @return create_time - 申请时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置申请时间
     *
     * @param createTime 申请时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getCr() {
  		return createTime == null ? "" : DateUtil.formatDateTime(createTime);
  	}
    /**
     * 获取审核时间
     *
     * @return check_time - 审核时间
     */
    public Date getCheckTime() {
        return checkTime;
    }

    /**
     * 设置审核时间
     *
     * @param checkTime 审核时间
     */
    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }
    public String getCk() {
  		return checkTime== null ? "" : DateUtil.formatDateTime(checkTime);
  	}
}
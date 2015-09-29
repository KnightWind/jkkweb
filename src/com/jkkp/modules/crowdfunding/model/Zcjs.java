package com.jkkp.modules.crowdfunding.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jkkp.utils.DateUtil;

/***
 * 众凑结算表
 * @author Administrator
 *
 */
@Table(name = "zc_js")
public class Zcjs {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "order_id")
	private Integer orderId;
	
	private Double price;
	
	@Column(name = "sp_id")
	private Integer spId;
	
	@Column(name = "create_time")
	private Date createTime;
	
	@Column(name = "admin_name")
	private String adminName;
	
    private String remark;
    
    private Integer status;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getStatusVal(){
		if(this.getStatus()!=null){
			return status==0?"未付款":"已付款";
		}
		return "";
	}
	
	public String getCreateTimeHandle(){
		return DateUtil.formatDateTime(createTime);
	}
}

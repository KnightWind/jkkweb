package com.jkkp.modules.crowdfunding.model;

import java.util.Date;

public class ActivityRefundOrder {
	
	private Integer id;
	
	private Integer OrderId; //订单id
	
	private Integer memberId; //用户id
	
	private String remark; //备注
	
	private Date createTime; //申请退款时间

	public Integer getId() {
		return id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getOrderId() {
		return OrderId;
	}

	public void setOrderId(Integer orderId) {
		OrderId = orderId;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

}

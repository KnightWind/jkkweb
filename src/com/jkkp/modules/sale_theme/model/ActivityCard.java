package com.jkkp.modules.sale_theme.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/***
 * 参加活动购物卡
 * @author ccn
 *
 */

@Table(name = "activity_card")
public class ActivityCard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "activity_id")
	private Integer activityId; //活动ID 引用ActivityTheme
	
	private Float price;
	
	private String name;
	
	protected String descr;
	
	@Column(name = "admin_id")
	private String adminId;
	
//	@Column(name = "supplier_id")
//	private Integer supplierId;
	
	@Column(name = "update_time")
	protected Date updateTime;
	
	
	
	
	
//	public Integer getSupplierId() {
//		return supplierId;
//	}
//
//	public void setSupplierId(Integer supplierId) {
//		this.supplierId = supplierId;
//	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}

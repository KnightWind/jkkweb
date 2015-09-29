package com.jkkp.modules.sale_theme.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/***
 * 参加活动相关商品记录表
 * 
 * @author ccn
 * 
 */

@Table(name = "activity_product")
public class ActivityProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "activity_id")
	private Integer activityId; // 活动ID 引用ActivityTheme
	
	private String title; //新的商品标题, 如果为空, 则使用原商品的标题
	
	@Column(name = "item_id")
	private Integer itemId; //商品ID
	
	private Double price; // 促销价格

	@Column(name = "orders")
	private Integer orders; //排序位置

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

	public String getTitle() {
		//this.title = title.trim().replace(" ", "").replace("　", "");
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getOrders() {
		return orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

	
}

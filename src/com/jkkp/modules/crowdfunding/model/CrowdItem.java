package com.jkkp.modules.crowdfunding.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "zc_item")
public class CrowdItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "flag")
	private Boolean flag; // 是否有效
	
	@Column(name = "top_price")
	private Double topPrice; // 原始价

	@Column(name = "low_price")
	private Double lowPrice; // 最低价
	
	@Column(name = "plat_price")
	private Double platPrice; // 平台低价
	
	@Column(name = "privilege")
	private Double privilege; // 优惠技术
	
	@Column(name = "price_grad")
	private Double priceGrad; // 价格梯度
	
	@Column(name = "activity_id")
	private Integer activityId; // 活动id
	
	@Column(name = "num_people")
	private int numPeople; // 众筹人数

	@Column(name = "create_date")
	private Date createDate; // 活动创建日期
	
	@Column(name = "item_id")
	private Integer itemId; // 商品id
	
	@Column(name = "is_refund")
	private Integer refund; // 商品id
	
	@Column(name = "refund_proportion")
	private Float proportion; // 商品id

	@Column(name = "status")
	protected Integer status;
	
	
	
	public Double getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Double privilege) {
		this.privilege = privilege;
	}

	public Double getPlatPrice() {
		return platPrice;
	}

	public void setPlatPrice(Double platPrice) {
		this.platPrice = platPrice;
	}

	public Double getPriceGrad() {
		return priceGrad;
	}

	public void setPriceGrad(Double priceGrad) {
		this.priceGrad = priceGrad;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getRefund() {
		return refund;
	}

	public void setRefund(Integer refund) {
		this.refund = refund;
	}

	public Float getProportion() {
		return proportion;
	}

	public void setProportion(Float proportion) {
		this.proportion = proportion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public Double getTopPrice() {
		return topPrice;
	}

	public void setTopPrice(Double topPrice) {
		this.topPrice = topPrice;
	}

	public Double getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(Double lowPrice) {
		this.lowPrice = lowPrice;
	}


	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public int getNumPeople() {
		return numPeople;
	}

	public void setNumPeople(int numPeople) {
		this.numPeople = numPeople;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	
	
	
}

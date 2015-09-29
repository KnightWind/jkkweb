package com.jkkp.modules.product.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jkkp.utils.DateUtil;

@Table(name = "item_special")
public class ItemSpecial {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 商品id
	 */
	@Column(name = "item_id")
	private Integer itemId;

	/**
	 * 限时折扣价格
	 */
	private Double discount;

	/**
	 * 折扣开始时间
	 */
	@Column(name = "start_date")
	private Date startDate;

	/**
	 * 折扣结束日期
	 */
	@Column(name = "end_date")
	private Date endDate;

	/**
	 * 人均限购：0说明不限购
	 */
	@Column(name = "limit_num")
	private Integer limitNum;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;

	/**
	 * 状态：0上架-1下架
	 */
	private Integer status;

	/**
	 * 所在城市域名
	 */
	@Column(name = "city_domain")
	private String cityDomain;

	/**
	 * 已售数量
	 */
	@Column(name = "sale_num")
	private Integer saleNum;

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
	 * 获取商品id
	 *
	 * @return item_id - 商品id
	 */
	public Integer getItemId() {
		return itemId;
	}

	/**
	 * 设置商品id
	 *
	 * @param itemId
	 *            商品id
	 */
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	/**
	 * 获取限时折扣价格
	 *
	 * @return discount - 限时折扣价格
	 */
	public Double getDiscount() {
		return discount;
	}

	/**
	 * 设置限时折扣价格
	 *
	 * @param discount
	 *            限时折扣价格
	 */
	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	/**
	 * 获取折扣开始时间
	 *
	 * @return start_date - 折扣开始时间
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * 设置折扣开始时间
	 *
	 * @param startDate
	 *            折扣开始时间
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * 获取折扣结束日期
	 *
	 * @return end_date - 折扣结束日期
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * 设置折扣结束日期
	 *
	 * @param endDate
	 *            折扣结束日期
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * 获取人均限购：0说明不限购
	 *
	 * @return limit_num - 人均限购：0说明不限购
	 */
	public Integer getLimitNum() {
		return limitNum;
	}

	/**
	 * 设置人均限购：0说明不限购
	 *
	 * @param limitNum
	 *            人均限购：0说明不限购
	 */
	public void setLimitNum(Integer limitNum) {
		this.limitNum = limitNum;
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
	 * @param createTime
	 *            创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取状态：0上架-1下架
	 *
	 * @return status - 状态：0上架-1下架
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 设置状态：0上架-1下架
	 *
	 * @param status
	 *            状态：0上架-1下架
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 获取所在城市域名
	 *
	 * @return city_domain - 所在城市域名
	 */
	public String getCityDomain() {
		return cityDomain;
	}

	/**
	 * 设置所在城市域名
	 *
	 * @param cityDomain
	 *            所在城市域名
	 */
	public void setCityDomain(String cityDomain) {
		this.cityDomain = cityDomain;
	}

	/**
	 * 获取已售数量
	 *
	 * @return sale_num - 已售数量
	 */
	public Integer getSaleNum() {
		return saleNum;
	}

	/**
	 * 设置已售数量
	 *
	 * @param saleNum
	 *            已售数量
	 */
	public void setSaleNum(Integer saleNum) {
		this.saleNum = saleNum;
	}

	public String getStatusName() {
		if (status == null || status == 0) {
			return "上架";
		} else if (status == -1) {
			return "下架";
		}
		return "";
	}

	public String getExpireName() {
		if (endDate != null) {
			return endDate.after(new Date()) ? "否" : "是";
		}
		return "";
	}
	
	public String getStartDateStr() {
		return DateUtil.formatDate(startDate);
	}
	
	public String getEndDateStr() {
		return DateUtil.formatDate(endDate);
	}
	
	public String getCreateTimeStr() {
		return DateUtil.formatDate(createTime);
	}
}
package com.jkkp.modules.design.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jkkp.utils.DateUtil;

@Table(name = "package")
public class Package {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 套餐包名称
	 */
	@Column(name = "package_name")
	private String packageName;

	/**
	 * 套餐包类型1主材包2如软装包
	 */
	private Integer type;

	/**
	 * 封面图片
	 */
	private String logo;

	/**
	 * 套装价格
	 */
	private Double price;

	/**
	 * 延米价格
	 */
	private Integer yanmi;

	/**
	 * 套餐包状态：0正常-1下架
	 */
	private Integer status;

	/**
	 * 所在城市域名
	 */
	@Column(name = "city_domain")
	private String cityDomain;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;

	/**
	 * 套数
	 */
	private Integer stock;

	/**
	 * 已售数量
	 */
	@Column(name = "sale_num")
	private Integer saleNum;

	/**
	 * 商户id
	 */
	@Column(name = "sp_id")
	private Integer spId;

	/**
	 * 关注数
	 */
	@Column(name = "follow_num")
	private Integer followNum;

	/**
	 * 详细介绍
	 */
	private String detail;

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
	 * 获取套餐包名称
	 *
	 * @return package_name - 套餐包名称
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * 设置套餐包名称
	 *
	 * @param packageName
	 *            套餐包名称
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	/**
	 * 获取套餐包类型1主材包2如软装包
	 *
	 * @return type - 套餐包类型1主材包2如软装包
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 设置套餐包类型1主材包2如软装包
	 *
	 * @param type
	 *            套餐包类型1主材包2如软装包
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 获取封面图片
	 *
	 * @return logo - 封面图片
	 */
	public String getLogo() {
		return logo;
	}

	/**
	 * 设置封面图片
	 *
	 * @param logo
	 *            封面图片
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}

	/**
	 * 获取套装价格
	 *
	 * @return price - 套装价格
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * 设置套装价格
	 *
	 * @param price
	 *            套装价格
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * 获取延米价格
	 *
	 * @return yanmi - 延米价格
	 */
	public Integer getYanmi() {
		return yanmi;
	}

	/**
	 * 设置延米价格
	 *
	 * @param yanmi
	 *            延米价格
	 */
	public void setYanmi(Integer yanmi) {
		this.yanmi = yanmi;
	}

	/**
	 * 获取套餐包状态：0正常-1下架
	 *
	 * @return status - 套餐包状态：0正常-1下架
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 设置套餐包状态：0正常-1下架
	 *
	 * @param status
	 *            套餐包状态：0正常-1下架
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
	 * 获取套数
	 *
	 * @return stock - 套数
	 */
	public Integer getStock() {
		return stock;
	}

	/**
	 * 设置套数
	 *
	 * @param stock
	 *            套数
	 */
	public void setStock(Integer stock) {
		this.stock = stock;
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

	/**
	 * 获取商户id
	 *
	 * @return sp_id - 商户id
	 */
	public Integer getSpId() {
		return spId;
	}

	/**
	 * 设置商户id
	 *
	 * @param spId
	 *            商户id
	 */
	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	/**
	 * 获取关注数
	 *
	 * @return follow_num - 关注数
	 */
	public Integer getFollowNum() {
		return followNum;
	}

	/**
	 * 设置关注数
	 *
	 * @param followNum
	 *            关注数
	 */
	public void setFollowNum(Integer followNum) {
		this.followNum = followNum;
	}

	/**
	 * 获取详细介绍
	 *
	 * @return detail - 详细介绍
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * 设置详细介绍
	 *
	 * @param detail
	 *            详细介绍
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getStatusName() {
		return status == null || status == 0 ? "上架" : (status == -1 ? "下架" : "");
	}

	public String getTypeName() {
		return type != null && type == 1 ? "主材包" : (type == 2 ? "软装包" : "");
	}
	
	public String getCreateTimeStr() {
		return DateUtil.formatDate(createTime);
	}
}
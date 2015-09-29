package com.jkkp.modules.supplier.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jkkp.utils.DateUtil;

@Table(name = "quan")
public class Quan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 券封面
	 */
	private String pid;

	/**
	 * 面值
	 */
	@Column(name = "par_value")
	private Double parValue;

	/**
	 * 券名称
	 */
	private String name;

	/**
	 * 销售价格
	 */
	private Double price;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;

	/**
	 * 上架状态：0上架-1下架
	 */
	private Integer saleing;

	/**
	 * 库存数量
	 */
	private Integer stock;

	/**
	 * 券类型：1全品券2店铺圈3oto券
	 */
	private Integer type;

	/**
	 * 批次
	 */
	private Integer no;

	/**
	 * 有效期开始日期
	 */
	@Column(name = "expire_start")
	private Date expireStart;

	/**
	 * 有效期介绍日期
	 */
	@Column(name = "expire_end")
	private Date expireEnd;

	/**
	 * 发布状态：0未发布1已发布-1注销
	 */
	private Integer status;

	/**
	 * 满额
	 */
	@Column(name = "full_quota")
	private Double fullQuota;

	/**
	 * 券的商品分类id（一级）
	 */
	private Integer cate;

	/**
	 * 关注数
	 */
	@Column(name = "follow_num")
	private Integer followNum;

	/**
	 * 销售数量
	 */
	@Column(name = "sales_num")
	private Integer salesNum;

	/**
	 * 是否推送0否，1是
	 */
	@Column(name = "is_push")
	private Integer isPush;

	/**
	 * 券描述
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
	 * 获取券封面
	 * 
	 * @return pid - 券封面
	 */
	public String getPid() {
		return pid;
	}

	/**
	 * 设置券封面
	 * 
	 * @param pid
	 *            券封面
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}

	/**
	 * 获取面值
	 * 
	 * @return par_value - 面值
	 */
	public Double getParValue() {
		return parValue;
	}

	/**
	 * 设置面值
	 * 
	 * @param parValue
	 *            面值
	 */
	public void setParValue(Double parValue) {
		this.parValue = parValue;
	}

	/**
	 * 获取券名称
	 * 
	 * @return name - 券名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置券名称
	 * 
	 * @param name
	 *            券名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取销售价格
	 * 
	 * @return price - 销售价格
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * 设置销售价格
	 * 
	 * @param price
	 *            销售价格
	 */
	public void setPrice(Double price) {
		this.price = price;
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
	 * 获取上架状态：0上架-1下架
	 * 
	 * @return saleing - 上架状态：0上架-1下架
	 */
	public Integer getSaleing() {
		return saleing;
	}

	/**
	 * 设置上架状态：0上架-1下架
	 * 
	 * @param saleing
	 *            上架状态：0上架-1下架
	 */
	public void setSaleing(Integer saleing) {
		this.saleing = saleing;
	}

	/**
	 * 获取库存数量
	 * 
	 * @return stock - 库存数量
	 */
	public Integer getStock() {
		return stock;
	}

	/**
	 * 设置库存数量
	 * 
	 * @param stock
	 *            库存数量
	 */
	public void setStock(Integer stock) {
		this.stock = stock;
	}

	/**
	 * 获取券类型：1全品券2店铺圈3oto券
	 * 
	 * @return type - 券类型：1全品券2店铺圈3oto券
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 设置券类型：1全品券2店铺圈3oto券
	 * 
	 * @param type
	 *            券类型：1全品券2店铺圈3oto券
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 获取批次
	 * 
	 * @return no - 批次
	 */
	public Integer getNo() {
		return no;
	}

	/**
	 * 设置批次
	 * 
	 * @param no
	 *            批次
	 */
	public void setNo(Integer no) {
		this.no = no;
	}

	/**
	 * 获取有效期开始日期
	 * 
	 * @return expire_start - 有效期开始日期
	 */
	public Date getExpireStart() {
		return expireStart;
	}

	/**
	 * 设置有效期开始日期
	 * 
	 * @param expireStart
	 *            有效期开始日期
	 */
	public void setExpireStart(Date expireStart) {
		this.expireStart = expireStart;
	}

	/**
	 * 获取有效期介绍日期
	 * 
	 * @return expire_end - 有效期介绍日期
	 */
	public Date getExpireEnd() {
		return expireEnd;
	}

	/**
	 * 设置有效期介绍日期
	 * 
	 * @param expireEnd
	 *            有效期介绍日期
	 */
	public void setExpireEnd(Date expireEnd) {
		this.expireEnd = expireEnd;
	}

	/**
	 * 获取发布状态：0未发布1已发布-1注销
	 * 
	 * @return status - 发布状态：0未发布1已发布-1注销
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 设置发布状态：0未发布1已发布-1注销
	 * 
	 * @param status
	 *            发布状态：0未发布1已发布-1注销
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 获取满额
	 * 
	 * @return full_quota - 满额
	 */
	public Double getFullQuota() {
		return fullQuota;
	}

	/**
	 * 设置满额
	 * 
	 * @param fullQuota
	 *            满额
	 */
	public void setFullQuota(Double fullQuota) {
		this.fullQuota = fullQuota;
	}

	/**
	 * 获取券的商品分类id（一级）
	 * 
	 * @return cate - 券的商品分类id（一级）
	 */
	public Integer getCate() {
		return cate;
	}

	/**
	 * 设置券的商品分类id（一级）
	 * 
	 * @param cate
	 *            券的商品分类id（一级）
	 */
	public void setCate(Integer cate) {
		this.cate = cate;
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
	 * 获取销售数量
	 * 
	 * @return sales_num - 销售数量
	 */
	public Integer getSalesNum() {
		return salesNum;
	}

	/**
	 * 设置销售数量
	 * 
	 * @param salesNum
	 *            销售数量
	 */
	public void setSalesNum(Integer salesNum) {
		this.salesNum = salesNum;
	}

	/**
	 * 获取是否推送0否，1是
	 * 
	 * @return is_push - 是否推送0否，1是
	 */
	public Integer getIsPush() {
		return isPush;
	}

	/**
	 * 设置是否推送0否，1是
	 * 
	 * @param isPush
	 *            是否推送0否，1是
	 */
	public void setIsPush(Integer isPush) {
		this.isPush = isPush;
	}

	/**
	 * 获取券描述
	 * 
	 * @return detail - 券描述
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * 设置券描述
	 * 
	 * @param detail
	 *            券描述
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getIson() {
		if (this.type != null) {
			if (this.type == 1) {
				return "全品券";
			} else if (this.type == 2) {
				return "店铺圈";
			} else {
				return "oto券";
			}
		}
		return "";
	}

	public String getDateTime() {
		Date date = new Date();
		if (expireEnd != null && expireStart != null) {
			if (expireStart.before(date) && expireEnd.after(date)) {
				return "否";
			} else {
				return "是";
			}
		} else {
			return "";
		}
	}

	public String getIs() {
		if (this.saleing != null) {
			if (this.saleing == 0) {
				return "上架";
			} else {
				return "下架";
			}
		}
		return "";
	}

	public String getExpireStartStr() {
		return DateUtil.formatDate(expireStart);
	}

	public String getExpireEndStr() {
		return DateUtil.formatDate(expireEnd);
	}

	public String getCreateTimeStr() {
		return DateUtil.formatDate(createTime);
	}

	public String getStatusName() {
		if (status == 0) {
			return "未发布";
		} else if (status == 1) {
			return "已发布";
		} else {
			return "注销";
		}
	}

	public String getSaleingName() {
		return saleing == 0 ? "上架" : "下架";
	}

	public String getTypeName() {
		if (type == 1) {
			return "全品券";
		} else if (type == 2) {
			return "店铺圈";
		} else {
			return "oto券";
		}
	}
}
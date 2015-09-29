package com.jkkp.modules.design.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "brand")
public class Brand {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 品牌名称
	 */
	private String name;

	/**
	 * 品牌关键词
	 */
	private String keyword;

	/**
	 * logo图片id
	 */
	@Column(name = "logo_pid")
	private String logoPid;

	/**
	 * 显示状态：0上架-1下架
	 */
	private Integer status;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;

	/**
	 * 描述评价1-5星
	 */
	@Column(name = "estimate_desc")
	private BigDecimal estimateDesc;

	/**
	 * 服务综合评价1-5
	 */
	@Column(name = "estimate_service")
	private BigDecimal estimateService;

	/**
	 * 效率评价1-5星
	 */
	@Column(name = "estimate_efficiency")
	private BigDecimal estimateEfficiency;

	/**
	 * 效率综合评分
	 */
	@Column(name = "estimate_average")
	private BigDecimal estimateAverage;

	private String content;

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
	 * 获取品牌名称
	 *
	 * @return name - 品牌名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置品牌名称
	 *
	 * @param name
	 *            品牌名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取品牌关键词
	 *
	 * @return keyword - 品牌关键词
	 */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * 设置品牌关键词
	 *
	 * @param keyword
	 *            品牌关键词
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * 获取logo图片id
	 *
	 * @return logo_pid - logo图片id
	 */
	public String getLogoPid() {
		return logoPid;
	}

	/**
	 * 设置logo图片id
	 *
	 * @param logoPid
	 *            logo图片id
	 */
	public void setLogoPid(String logoPid) {
		this.logoPid = logoPid;
	}

	/**
	 * 获取显示状态：0上架-1下架
	 *
	 * @return status - 显示状态：0上架-1下架
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 设置显示状态：0上架-1下架
	 *
	 * @param status
	 *            显示状态：0上架-1下架
	 */
	public void setStatus(Integer status) {
		this.status = status;
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
	 * 获取描述评价1-5星
	 *
	 * @return estimate_desc - 描述评价1-5星
	 */
	public BigDecimal getEstimateDesc() {
		return estimateDesc;
	}

	/**
	 * 设置描述评价1-5星
	 *
	 * @param estimateDesc
	 *            描述评价1-5星
	 */
	public void setEstimateDesc(BigDecimal estimateDesc) {
		this.estimateDesc = estimateDesc;
	}

	/**
	 * 获取服务综合评价1-5
	 *
	 * @return estimate_service - 服务综合评价1-5
	 */
	public BigDecimal getEstimateService() {
		return estimateService;
	}

	/**
	 * 设置服务综合评价1-5
	 *
	 * @param estimateService
	 *            服务综合评价1-5
	 */
	public void setEstimateService(BigDecimal estimateService) {
		this.estimateService = estimateService;
	}

	/**
	 * 获取效率评价1-5星
	 *
	 * @return estimate_efficiency - 效率评价1-5星
	 */
	public BigDecimal getEstimateEfficiency() {
		return estimateEfficiency;
	}

	/**
	 * 设置效率评价1-5星
	 *
	 * @param estimateEfficiency
	 *            效率评价1-5星
	 */
	public void setEstimateEfficiency(BigDecimal estimateEfficiency) {
		this.estimateEfficiency = estimateEfficiency;
	}

	/**
	 * 获取效率综合评分
	 *
	 * @return estimate_average - 效率综合评分
	 */
	public BigDecimal getEstimateAverage() {
		return estimateAverage;
	}

	/**
	 * 设置效率综合评分
	 *
	 * @param estimateAverage
	 *            效率综合评分
	 */
	public void setEstimateAverage(BigDecimal estimateAverage) {
		this.estimateAverage = estimateAverage;
	}

	/**
	 * @return content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	public String getStatusName() {
		if (status == null || status == 0) {
			return "上架";
		} else if (status == 1) {
			return "下架";
		}
		return "";
	}
	
	public String getStatusNameHandle(){
		if (status == null || status == 0) {
			return "下架";
		} else if (status == 1) {
			return "上架";
		}
		return "";
	}
}
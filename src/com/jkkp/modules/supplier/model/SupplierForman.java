package com.jkkp.modules.supplier.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "supplier_forman")
public class SupplierForman {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 商家id
	 */
	@Column(name = "sp_id")
	private Integer spId;

	/**
	 * 工长照片
	 */
	private String avatar;

	/**
	 * 团队人数
	 */
	@Column(name = "people_num")
	private Integer peopleNum;

	/**
	 * 工长介绍
	 */
	private String intro;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;

	/**
	 * 服务区域
	 */
	@Column(name = "service_area")
	private String serviceArea;

	/**
	 * 模板id
	 */
	@Column(name = "template_id")
	private Integer templateId;

	/**
	 * 工长籍贯
	 */
	@Column(name = "native")
	private Integer nativeValue;

	/**
	 * 效果图数量
	 */
	@Column(name = "design_num")
	private Integer designNum;

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
	 * @param spId
	 *            商家id
	 */
	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	/**
	 * 获取工长照片
	 *
	 * @return avatar - 工长照片
	 */
	public String getAvatar() {
		return avatar;
	}

	/**
	 * 设置工长照片
	 *
	 * @param avatar
	 *            工长照片
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	/**
	 * 获取团队人数
	 *
	 * @return people_num - 团队人数
	 */
	public Integer getPeopleNum() {
		return peopleNum;
	}

	/**
	 * 设置团队人数
	 *
	 * @param peopleNum
	 *            团队人数
	 */
	public void setPeopleNum(Integer peopleNum) {
		this.peopleNum = peopleNum;
	}

	/**
	 * 获取工长介绍
	 *
	 * @return intro - 工长介绍
	 */
	public String getIntro() {
		return intro;
	}

	/**
	 * 设置工长介绍
	 *
	 * @param intro
	 *            工长介绍
	 */
	public void setIntro(String intro) {
		this.intro = intro;
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
	 * 获取服务区域
	 *
	 * @return service_area - 服务区域
	 */
	public String getServiceArea() {
		return serviceArea;
	}

	/**
	 * 设置服务区域
	 *
	 * @param serviceArea
	 *            服务区域
	 */
	public void setServiceArea(String serviceArea) {
		this.serviceArea = serviceArea;
	}

	/**
	 * 获取模板id
	 *
	 * @return template_id - 模板id
	 */
	public Integer getTemplateId() {
		return templateId;
	}

	/**
	 * 设置模板id
	 *
	 * @param templateId
	 *            模板id
	 */
	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	/**
	 * 获取工长籍贯
	 *
	 * @return native - 工长籍贯
	 */
	public Integer getNativeValue() {
		return nativeValue;
	}

	/**
	 * 设置工长籍贯
	 *
	 * @param native 工长籍贯
	 */
	public void setNativeValue(Integer nativeValue) {
		this.nativeValue = nativeValue;
	}

	/**
	 * 获取效果图数量
	 *
	 * @return design_num - 效果图数量
	 */
	public Integer getDesignNum() {
		return designNum;
	}

	/**
	 * 设置效果图数量
	 *
	 * @param designNum
	 *            效果图数量
	 */
	public void setDesignNum(Integer designNum) {
		this.designNum = designNum;
	}
}
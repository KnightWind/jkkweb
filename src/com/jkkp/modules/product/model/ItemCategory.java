package com.jkkp.modules.product.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "item_category")
public class ItemCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	/**
	 * 父类id,如果为0，说明是一级分类
	 */
	@Column(name = "parent_id")
	private Integer parentId;

	/**
	 * 状态：0正常，-1删去
	 */
	private Integer status;

	/**
	 * 备注
	 */
	private String comments;

	/**
	 * 管理员id
	 */
	@Column(name = "admin_id")
	private Integer adminId;
	
	private Integer seq;

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
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取父类id,如果为0，说明是一级分类
	 *
	 * @return parent_id - 父类id,如果为0，说明是一级分类
	 */
	public Integer getParentId() {
		return parentId;
	}

	/**
	 * 设置父类id,如果为0，说明是一级分类
	 *
	 * @param parentId
	 *            父类id,如果为0，说明是一级分类
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	/**
	 * 获取状态：0正常，-1删去
	 *
	 * @return status - 状态：0正常，-1删去
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 设置状态：0正常，-1删去
	 *
	 * @param status
	 *            状态：0正常，-1删去
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 获取备注
	 *
	 * @return comments - 备注
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * 设置备注
	 *
	 * @param comments
	 *            备注
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * 获取管理员id
	 *
	 * @return admin_id - 管理员id
	 */
	public Integer getAdminId() {
		return adminId;
	}

	/**
	 * 设置管理员id
	 *
	 * @param adminId
	 *            管理员id
	 */
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	/**
	 * @return seq
	 */
	public Integer getSeq() {
		return seq;
	}

	/**
	 * @param seq
	 */
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
}
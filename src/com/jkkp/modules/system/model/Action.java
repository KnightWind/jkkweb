package com.jkkp.modules.system.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "action")
public class Action {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 权限名称
	 */
	private String name;

	/**
	 * 权限操作地址
	 */
	private String link;

	/**
	 * 父id
	 */
	private Integer pid;

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
	 * 获取权限名称
	 *
	 * @return name - 权限名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置权限名称
	 *
	 * @param name
	 *            权限名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取权限操作地址
	 *
	 * @return link - 权限操作地址
	 */
	public String getLink() {
		return link;
	}

	/**
	 * 设置权限操作地址
	 *
	 * @param link
	 *            权限操作地址
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * 获取父id
	 *
	 * @return pid - 父id
	 */
	public Integer getPid() {
		return pid;
	}

	/**
	 * 设置父id
	 *
	 * @param pid
	 *            父id
	 */
	public void setPid(Integer pid) {
		this.pid = pid;
	}
}
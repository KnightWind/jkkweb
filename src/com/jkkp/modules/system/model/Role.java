package com.jkkp.modules.system.model;

import javax.persistence.*;

@Table(name = "role")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 角色名称
	 */
	private String name;

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
	 * 获取角色名称
	 *
	 * @return name - 角色名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置角色名称
	 *
	 * @param name
	 *            角色名称
	 */
	public void setName(String name) {
		this.name = name;
	}
}
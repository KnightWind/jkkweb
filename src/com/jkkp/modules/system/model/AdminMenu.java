package com.jkkp.modules.system.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "admin_menu")
public class AdminMenu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 权限父级id
	 */
	private Integer pid;

	/**
	 * 权限名称
	 */
	private String name;

	/**
	 * 权限操作地址
	 */
	private String link;

	/**
	 * 排序
	 */
	private Integer orderby;

	/**
	 * 菜单小图标
	 */
	private String icon;

	@Column(name = "menu_type")
	private Integer menuType;

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
	 * 获取权限父级id
	 *
	 * @return pid - 权限父级id
	 */
	public Integer getPid() {
		return pid;
	}

	/**
	 * 设置权限父级id
	 *
	 * @param pid
	 *            权限父级id
	 */
	public void setPid(Integer pid) {
		this.pid = pid;
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
	 * 获取排序
	 *
	 * @return orderby - 排序
	 */
	public Integer getOrderby() {
		return orderby;
	}

	/**
	 * 设置排序
	 *
	 * @param orderby
	 *            排序
	 */
	public void setOrderby(Integer orderby) {
		this.orderby = orderby;
	}

	/**
	 * 获取菜单小图标
	 *
	 * @return icon - 菜单小图标
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * 设置菜单小图标
	 *
	 * @param icon
	 *            菜单小图标
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getMenuType() {
		return menuType;
	}

	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}

}

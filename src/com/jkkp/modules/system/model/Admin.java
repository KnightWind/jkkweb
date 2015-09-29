package com.jkkp.modules.system.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "admin")
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String pass;

	@Column(name = "create_time")
	private Date createTime;

	/**
	 * 更新时间
	 */
	@Column(name = "update_time")
	private Date updateTime;

	/**
	 * 0正常-1无效
	 */
	private Integer status;

	/**
	 * 角色id编号
	 */
	private Integer rid;

	/**
	 * 手机号
	 */
	private String mobile;

	/**
	 * email
	 */
	private String email;

	/**
	 * 所属区域
	 */
	@Column(name = "city_domain")
	private String cityDomain;

	/**
	 * 管理员姓名
	 */
	private String nickname;

	@Column(name = "login_time")
	private Date loginTime;

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
	 * 获取用户名
	 *
	 * @return username - 用户名
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 设置用户名
	 *
	 * @param username
	 *            用户名
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 获取密码
	 *
	 * @return pass - 密码
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * 设置密码
	 *
	 * @param pass
	 *            密码
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	/**
	 * @return create_time
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取更新时间
	 *
	 * @return update_time - 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置更新时间
	 *
	 * @param updateTime
	 *            更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 获取0正常-1无效
	 *
	 * @return status - 0正常-1无效
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 设置0正常-1无效
	 *
	 * @param status
	 *            0正常-1无效
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 获取角色id编号
	 *
	 * @return rid - 角色id编号
	 */
	public Integer getRid() {
		return rid;
	}

	/**
	 * 设置角色id编号
	 *
	 * @param rid
	 *            角色id编号
	 */
	public void setRid(Integer rid) {
		this.rid = rid;
	}

	/**
	 * 获取手机号
	 *
	 * @return mobile - 手机号
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * 设置手机号
	 *
	 * @param mobile
	 *            手机号
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 获取email
	 *
	 * @return email - email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 设置email
	 *
	 * @param email
	 *            email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 获取所属区域
	 *
	 * @return city_domain - 所属区域
	 */
	public String getCityDomain() {
		return cityDomain;
	}

	/**
	 * 设置所属区域
	 *
	 * @param cityDomain
	 *            所属区域
	 */
	public void setCityDomain(String cityDomain) {
		this.cityDomain = cityDomain;
	}

	/**
	 * 获取管理员姓名
	 *
	 * @return nickname - 管理员姓名
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * 设置管理员姓名
	 *
	 * @param nickname
	 *            管理员姓名
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return login_time
	 */
	public Date getLoginTime() {
		return loginTime;
	}

	/**
	 * @param loginTime
	 */
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
}
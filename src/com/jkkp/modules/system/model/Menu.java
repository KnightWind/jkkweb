package com.jkkp.modules.system.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "menu")
public class Menu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 导航名称
	 */
	private String name;

	/**
	 * 链接地址
	 */
	private String url;

	/**
	 * 管理员操作id
	 */
	@Column(name = "admin_id")
	private Integer adminId;

	/**
	 * 0正常-1删除
	 */
	private Byte status;

	@Column(name = "create_time")
	private Date createTime;

	@Column(name = "update_time")
	private Date updateTime;

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
	 * 获取导航名称
	 *
	 * @return name - 导航名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置导航名称
	 *
	 * @param name
	 *            导航名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取链接地址
	 *
	 * @return url - 链接地址
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 设置链接地址
	 *
	 * @param url
	 *            链接地址
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 获取管理员操作id
	 *
	 * @return admin_id - 管理员操作id
	 */
	public Integer getAdminId() {
		return adminId;
	}

	/**
	 * 设置管理员操作id
	 *
	 * @param adminId
	 *            管理员操作id
	 */
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	/**
	 * 获取0正常-1删除
	 *
	 * @return status - 0正常-1删除
	 */
	public Byte getStatus() {
		return status;
	}

	/**
	 * 设置0正常-1删除
	 *
	 * @param status
	 *            0正常-1删除
	 */
	public void setStatus(Byte status) {
		this.status = status;
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
	 * @return update_time
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	  public String getIson(){
	    	if(this.status!=null){
	    		if(this.status==0){
	    			return "正常";
	    		}else {
					return "删除";
				}
	    	}
	    	return "";
	    }
}
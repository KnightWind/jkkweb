package com.jkkp.modules.crowdfunding.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jkkp.utils.DateUtil;

/**
 * 众筹活动首页banner
 * 
 * @author xiaozhenyu
 * 
 */
@Table(name = "zc_itemcategory_info")
public class ItemCategoryInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String title;

	private String url;

	private Integer seq;

	private String place;

	private Integer status;

	@Column(name = "city_domain")
	private String cityDomain;

	@Column(name = "create_time")
	private Date createTime;

	@Column(name = "admin_id")
	private Integer adminId;
	
	
	@Column(name = "category_id")
	private Integer categoryId;
	
	

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCityDomain() {
		return cityDomain;
	}

	public void setCityDomain(String cityDomain) {
		this.cityDomain = cityDomain;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	//创建时间处理
	public String getCreateTimeHandle(){
		if(this.getCreateTime()!=null){
			return DateUtil.formatDateTime(createTime);
		}
		return "";
	}
}

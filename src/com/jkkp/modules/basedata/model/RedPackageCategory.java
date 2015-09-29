package com.jkkp.modules.basedata.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="red_package_category")
public class RedPackageCategory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="red_package_id")
	private Integer rpId;
	@Column(name="item_category_id")
	private Integer icId;
	@Column(name="category_name")
	private String categoryName;
	
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRpId() {
		return rpId;
	}
	public void setRpId(Integer rpId) {
		this.rpId = rpId;
	}
	public Integer getIcId() {
		return icId;
	}
	public void setIcId(Integer icId) {
		this.icId = icId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
	
	
}

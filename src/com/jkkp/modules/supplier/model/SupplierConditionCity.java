package com.jkkp.modules.supplier.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="supplier_condition_city")
public class SupplierConditionCity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**
	 * 商家条件id
	 */
	@Column(name="sc_id")
	private Integer scId;
	/**
	 * region地区id
	 */
	@Column(name="region_id")
	private Integer regionId;
	@Column(name="create_time")
	private Date createTime;
	/**
	 * 商家id
	 */
	@Column(name="sp_id")
	private Integer spId;
	
	public SupplierConditionCity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SupplierConditionCity(Integer id, Integer scId, Integer regionId,
			Date createTime, Integer spId) {
		super();
		this.id = id;
		this.scId = scId;
		this.regionId = regionId;
		this.createTime = createTime;
		this.spId = spId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getScId() {
		return scId;
	}
	public void setScId(Integer scId) {
		this.scId = scId;
	}
	public Integer getRegionId() {
		return regionId;
	}
	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getSpId() {
		return spId;
	}
	public void setSpId(Integer spId) {
		this.spId = spId;
	}
	
}

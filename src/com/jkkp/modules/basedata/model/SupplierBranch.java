package com.jkkp.modules.basedata.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "supplier_branch")
public class SupplierBranch {

	public SupplierBranch(Integer id, Integer spId, String name, String tel,
			String address, Double latitude, Double longitude) {
		super();
		this.id = id;
		this.spId = spId;
		this.name = name;
		this.tel = tel;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public SupplierBranch() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 所属商家ID
	 */
	@Column(name = "sp_id")
	private Integer spId;

	/**
	 * 分店名称
	 */
	@Column(name = "name")
	private String name;

	/**
	 * 分店客服电话
	 */
	@Column(name = "tel")
	private String tel;

	/**
	 *
	 */
	@Column(name = "address")
	private String address;

	/**
	 * 纬度
	 */
	@Column(name = "latitude")
	private Double latitude;

	/**
	 * 经度
	 */
	@Column(name = "longitude")
	private Double longitude;
	
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;

}